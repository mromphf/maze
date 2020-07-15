package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Storage {

    public static List<List<List<Character>>> loadLevels(String directory) {
        File folder = new File(directory);
        if (folder.exists()) {
            List<File> listOfFiles = Arrays.asList(Objects.requireNonNull(folder.listFiles()));
            return listOfFiles.stream()
                    .filter(File::isFile)
                    .sorted()
                    .map(f -> loadLevel(f.getPath()))
                    .collect(Collectors.toList());
        }
        else {
            throw new IllegalArgumentException(String.format("I can't find a directory called %s!", directory));
        }
    }

    private static List<List<Character>> loadLevel(String filename) {
        List<List<Character>> chars = new LinkedList<>();
        int x = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                chars.add(x, new LinkedList<>());
                String[] tokens = line.split(",");
                for (String token : tokens) {
                    chars.get(x).add(token.charAt(0));
                }
                x++;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return chars;
    }
}
