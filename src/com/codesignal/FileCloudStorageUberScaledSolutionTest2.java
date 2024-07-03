package com.codesignal;

import java.util.*;

public class FileCloudStorageUberScaledSolutionTest2 {

    public static void main(String[] args) {
        CloudStorageImpl storage = new CloudStorageImpl();

        storage.addUser("user", 40);
        storage.addFileBy("user", "/file1.txt", 20);
        storage.backupUser("user");
        storage.deleteFile("/file1.txt");
        assert Objects.equals(storage.restoreUser("user"), Optional.of(1));
    }
}
class CloudStorageImpl {


    static class File {
        String name;
        int size;
        File(String name, int size) {
            this.name = name;
            this.size = size;
        }

        public File copy() {
            return new File(this.name, this.size);
        }
    }

    static class Folder {
        String userId;
        int MAX_CAPACITY;
        int remainingCapacity;
        Map<String, File> files;
        Folder(String userId, int capacity) {
            this.userId = userId;
            this.MAX_CAPACITY = capacity;
            this.remainingCapacity = capacity;
            this.files = new HashMap<>();
        }

        public Folder copy() {
            Folder copyFolder = new Folder(this.userId, this.MAX_CAPACITY);
            copyFolder.remainingCapacity = this.remainingCapacity;
            Map<String, File> copyFiles = new HashMap<>();
            for(Map.Entry<String, File> file: this.files.entrySet()) {
                copyFiles.put(file.getKey(), file.getValue().copy());
            }
            copyFolder.files = copyFiles;
            return copyFolder;
        }
    }

    Map<String, Folder> db;
    Map<String, Folder> backupDb;

    public CloudStorageImpl() {
        this.db = new HashMap<>();
        Folder adminFolder = new Folder("admin", Integer.MAX_VALUE);
        db.put("admin", adminFolder);
        this.backupDb = new HashMap<>();
    }

    public boolean addFile(String name, int size) {
        // Folder folder = db.get("admin");
        // if(!folder.files.containsKey(name)) {
        //   folder.files.put(name, new File(name, size));
        //   return true;
        // }

        // return false;
        return addFileBy("admin", name, size).isPresent();
    }

    public Optional<Integer> addFileBy(String userId, String name, int size) {

        for(Map.Entry<String, Folder> entry : db.entrySet()) {
            Map<String, File> files = entry.getValue().files;
            if(files.containsKey(name)) {
                return Optional.empty();
            }
        }

        Folder folder = db.getOrDefault(userId, null);
        if(folder!=null && !folder.files.containsKey(name)) {
            if(size <= folder.remainingCapacity) {
                folder.files.put(name, new File(name, size));
                if(folder.userId != "admin")
                    folder.remainingCapacity = folder.remainingCapacity - size;
                return Optional.of(folder.remainingCapacity);
            }
        }

        return Optional.empty();
    }

    public Optional<Integer> getFileSize(String name) {
        for(Map.Entry<String, Folder> entry : db.entrySet()) {
            Map<String, File> files = entry.getValue().files;
            if(files.containsKey(name)) {
                return Optional.of(files.get(name).size);
            }
        }

        return Optional.empty();
    }

    public Optional<Integer> deleteFile(String name) {

        for(Map.Entry<String, Folder> entry : db.entrySet()) {
            Map<String, File> files = entry.getValue().files;
            if(files.containsKey(name)) {
                int size = files.get(name).size;
                files.remove(name);
                if(!entry.getKey().equals("admin")) {
                    entry.getValue().remainingCapacity += size;
                }
                return Optional.of(size);
            }
        }

        return Optional.empty();
    }

    public List<String> getNLargest(String prefix, int n) {
        List<String> output = new ArrayList<>();
        PriorityQueue<File> pq = new PriorityQueue<>(
                (f, s) -> f.size == s.size ? f.name.compareTo(s.name) : (s.size - f.size)
        );

        for(Map.Entry<String, Folder> entry : db.entrySet()) {
            Map<String, File> files = entry.getValue().files;


            for(Map.Entry<String, File> file: files.entrySet()) {
                if(file.getKey().startsWith(prefix)) {
                    pq.add(file.getValue());
                }
            }
        }

        while(n > 0 && !pq.isEmpty()) {
            File f = pq.remove();
            output.add(String.format("%s(%d)", f.name, f.size));
            n -= 1;
        }

        return output;
    }

    public boolean addUser(String userId, int capacity) {
        if(!db.containsKey(userId)) {
            Folder folder = new Folder(userId, capacity);
            db.put(userId, folder);
            return true;
        }

        return false;
    }

    public Optional<Integer> mergeUser(String userId1, String userId2) {

        if(userId1 == null || userId2 == null) {
            return Optional.empty();
        }

        Folder folder1 = db.getOrDefault(userId1, null);
        Folder folder2 = db.getOrDefault(userId2, null);

        if(userId1.equals(userId2) || folder1 == null || folder2 == null) {
            return Optional.empty();
        }

        folder1.files.putAll(folder2.files);
        folder1.MAX_CAPACITY += folder2.MAX_CAPACITY;
        folder1.remainingCapacity += folder2.remainingCapacity;

        db.remove(userId2);
        return Optional.of(folder1.remainingCapacity);
    }

    public Optional<Integer> backupUser(String userId) {
        Folder folder = db.getOrDefault(userId, null);

        if(folder == null) {
            return Optional.empty();
        }

        backupDb.put(userId, folder.copy());
        return Optional.of(folder.files.size());
    }

    public Optional<Integer> restoreUser(String userId) {
        Folder folder = db.getOrDefault(userId, null);

        if(folder == null) {
            return Optional.empty();
        }

        Folder buckupFolder = backupDb.getOrDefault(userId, null);
        if(buckupFolder == null) {
            db.put(userId, new Folder(folder.userId, folder.MAX_CAPACITY));
            return Optional.empty();
        }
        Map<String, File> finalFiles = new HashMap<>(buckupFolder.files);
        int deleteFileSize = 0;

        for(Map.Entry<String, Folder> entry : db.entrySet()) {
            if(entry.getKey().equals(userId))
                continue;
            // Map<String, File> files = entry.getValue().files;
            for(Map.Entry<String, File> fileEntry : entry.getValue().files.entrySet()) {
                if(finalFiles.containsKey(fileEntry.getKey())) {
                    finalFiles.remove(fileEntry.getKey());
                    deleteFileSize += fileEntry.getValue().size;
                }
            }
        }

        Folder finalFolder = buckupFolder.copy();
        finalFolder.files = finalFiles;
        finalFolder.remainingCapacity += deleteFileSize;
        db.put(userId, finalFolder);
        return Optional.of(finalFiles.size());
    }
}
