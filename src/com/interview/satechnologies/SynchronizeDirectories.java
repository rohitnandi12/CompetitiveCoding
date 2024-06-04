package com.interview.satechnologies;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SynchronizeDirectories {

    public static void main(String[] args) throws IOException {
        String primaryDirectory = "src/com/interview/satechnologies/directory1";
        String backupDirectory = "src/com/interview/satechnologies/directory2";
        Set<String> setA = loadFiles(primaryDirectory);
        Set<String> setB = loadFiles(backupDirectory);
        System.out.println("----------------------");
        System.out.println(setA);
        System.out.println(setB);
        System.out.println("----------------------");

        Set<String> filesInserted = new HashSet<>(setA);
        filesInserted.removeAll(setB);
        System.out.println("Files inserted: " + filesInserted);
        insertFiles(filesInserted, primaryDirectory, backupDirectory);

        Set<String> filesDeleted = new HashSet<>(setB);
        filesDeleted.removeAll(setA);
        System.out.println("Files deleted: " + filesDeleted);
        deleteFiles(filesDeleted, backupDirectory);

        Set<String> filesUpdated = new HashSet<>(setA);
        filesUpdated.retainAll(setB);
        System.out.println("Files updated: " + filesUpdated);

        for (String file : filesUpdated) {
            if (hasFileChanged(file, primaryDirectory, backupDirectory)) { // Method to check if file has changed
                updateFile(file, primaryDirectory, backupDirectory); // Method to update file if needed
            }
        }
    }

    private static void deleteFiles(Set<String> filesDeleted, String destination) {
        for (String fileName : filesDeleted) {
            File fileToDelete = new File(destination, fileName); // Construct the file path in the destination directory

            // Check if the file exists
            if (!fileToDelete.exists()) {
                System.out.println("File not found for deletion: " + fileToDelete.getAbsolutePath());
                continue;
            }

            // Attempt to delete the file
            if (fileToDelete.delete()) {
                System.out.println("Deleted file: " + fileName);

                // Check if the parent folder is empty
                File parentFolder = fileToDelete.getParentFile();
                if (parentFolder.exists() && parentFolder.isDirectory() && parentFolder.list().length == 0) {
                    if (parentFolder.delete()) {
                        System.out.println("Deleted empty folder: " + parentFolder.getAbsolutePath());
                    } else {
                        System.out.println("Failed to delete empty folder: " + parentFolder.getAbsolutePath());
                    }
                }
            } else {
                System.out.println("Failed to delete file: " + fileName);
            }
        }
    }


    private static void insertFiles(Set<String> filesInserted, String source, String destination) {
        for (String fileName : filesInserted) {
            File sourceFile = new File(source, fileName); // Source file path
            File destinationFile = new File(destination, fileName); // Destination file path

            // Ensure the source file exists
            if (!sourceFile.exists()) {
                System.out.println("Source file does not exist: " + sourceFile.getAbsolutePath());
                continue;
            }

            try {
                // Create directories in the destination path if they don't exist
                File parentDir = destinationFile.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }

                // Copy the file
                try (InputStream in = new FileInputStream(sourceFile);
                     OutputStream out = new FileOutputStream(destinationFile)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }

                    System.out.println("Inserted file: " + fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to insert file: " + fileName);
            }
        }
    }


    private static Set<String> loadFiles(String directoryPath) throws IOException {
        Set<String> fileNames = new HashSet<>();
        try {
            File directory = new File(directoryPath);
            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            fileNames.add(file.getName());
                        } else if (file.isDirectory()) {
                            Set<String> subDirectoryFiles = loadFiles(file.getPath());
                            subDirectoryFiles = subDirectoryFiles.stream()
                                    .map(f -> file.getName() + "\\" + f)
                                    .collect(Collectors.toSet());
                            fileNames.addAll(subDirectoryFiles);
                        }
                    }
                }
            } else {
                throw new RuntimeException("Invalid Directory: " + directoryPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return fileNames;
    }

    private static boolean hasFileChanged(String fileName, String source, String destination) {
        File file1 = new File(source, fileName); // Path to the file in dir1
        File file2 = new File(destination, fileName); // Path to the file in dir2

        if (!file1.exists() || !file2.exists()) {
            return true; // Consider changed if one of the files doesn't exist
        }

        // Compare file lengths
        if (file1.length() != file2.length()) {
            return true; // File sizes differ
        }

        // Compare last modified timestamps (optional, can be removed if only contents matter)
//        if (file1.lastModified() != file2.lastModified()) {
//            return true; // Files have different modification times
//        }

        // Compare file contents
        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {

            String line1, line2;
            while ((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null) {
                if (!line1.equals(line2)) {
                    return true; // Lines are different
                }
            }
            return (reader1.readLine() != null || reader2.readLine() != null); // Check for leftover lines
        } catch (IOException e) {
            e.printStackTrace();
            return true; // Assume changed if any exception occurs
        }
    }

    private static void updateFile(String fileName, String sourcePath, String destinationPath) {
        File source = new File(sourcePath, fileName);
        File destination = new File(destinationPath,fileName);

        if(!source.exists() || !destination.exists()) {
            throw new RuntimeException("source or destination does not exist");
        }

        try (InputStream in = new FileInputStream(source);
             OutputStream out = new FileOutputStream(destination)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            // Copy file content
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            if (destination.setLastModified(source.lastModified())) {
                System.out.println("Updated file and synchronized lastModified timestamp: " + fileName);
            } else {
                System.out.println("Failed to update lastModified timestamp for: " + fileName);
            }

            System.out.println("Updated file: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to update file: " + fileName);
        }
    }


}
