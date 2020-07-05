package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class File {
    public static char[][] loadLevel(String filename) {
        char[][] theThing = new char[20][20];
        int x = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                for (int y = 0; y < tokens.length; y++) {
                    theThing[x][y] = tokens[y].charAt(0);
                }
                x++;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return theThing;
    }
}
