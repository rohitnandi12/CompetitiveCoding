package com.company.salesforce;

public class TaxiDriverMaxPassengers {

    public static void main(String[] args) {

        System.out.println("Starting...........");
        assert maxPassengers(new int[][]{
                {0, 1},
                {-1, 0}
        }) == 1;

        assert maxPassengers(new int[][]{
                {0, 0, 0, 1},
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        }) == 2;

        assert maxPassengers(new int[][]{
                {0, 1, -1},
                {1, 0, -1},
                {1, 1, 1}
        }) == 5;
    }

    public static int maxPassengers(int[][] routes) {
        boolean debug = false;
        int N = routes.length;
        int maxPassengers = 0;
        int[][] toAirport = new int[N][N];

        for (int c = 1; c < N; c++) {
            if (routes[0][c] == -1)
                break;
            toAirport[0][c] += toAirport[0][c - 1] + routes[0][c];
        }

        for (int r = 1; r < N; r++) {
            if (routes[r][0] == -1)
                break;
            toAirport[r][0] += toAirport[r - 1][0] + routes[r][0];
        }

        for (int r = 1; r < N; r++) {
            for (int c = 1; c < N; c++) {
                if (routes[r][c] == -1)
                    continue;

                if (routes[r][c - 1] == -1 && routes[r - 1][c] == -1)
                    return 0;

                toAirport[r][c] = routes[r][c] + Math.max(
                        toAirport[r][c - 1],
                        toAirport[r - 1][c]);
            }
        }
        maxPassengers = toAirport[N - 1][N - 1];

        display(debug, routes);
        display(debug, toAirport);

        // clean the path which was taken
        int row = N - 1, col = N - 1;
        while (row >= 0 && col >= 0) {
            routes[row][col] = 0;
            // go next
            if (row == 0) {
                col -= 1;
            } else if (col == 0) {
                row -= 1;
            } else if (toAirport[row][col - 1] < toAirport[row - 1][col]) {
                row -= 1;
            } else {
                col -= 1;
            }
        }

        display(debug, routes);

        // going back home, ignoring the path which was taken

        int[][] toHome = new int[N][N];

        for (int c = 1; c < N; c++) {
            if (routes[0][c] == -1)
                break;
            toAirport[0][c] += toAirport[0][c - 1] + routes[0][c];
        }

        for (int c = N - 2; c >= 0; c--) {
            if (routes[N - 1][c] == -1)
                break;
            toHome[N - 1][c] = toHome[N - 1][c + 1] + routes[N - 1][c];
        }

        for (int r = N - 2; r >= 0; r--) {
            if (routes[r][N - 1] == -1)
                break;
            toHome[r][N - 1] += toHome[r + 1][N - 1] + routes[r][N - 1];
        }

        for (int r = N - 2; r >= 0; r--) {
            for (int c = N - 2; c >= 0; c--) {
                if (routes[r][c] == -1)
                    continue;

                if (routes[r][c + 1] == -1 && routes[r + 1][c] == -1)
                    return maxPassengers;

                toHome[r][c] = routes[r][c] + Math.max(
                        toHome[r][c + 1],
                        toHome[r + 1][c]);
            }
        }
        maxPassengers += toHome[0][0];

        display(debug, toHome);
        display(debug, routes);
        return maxPassengers;
    }


    private static void display(boolean debug, int[][] arr) {
        if (!debug)
            return;
        for (int[] a : arr) {
            for (int i : a) {
                System.out.printf("%d, ", i);
            }
            System.out.println();
        }
        System.out.println("----------------------------------");
    }
}
