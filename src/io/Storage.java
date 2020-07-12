package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    public static Map<Integer, List<Character>> loadLevel(String filename) {
        Map<Integer, List<Character>> chars = new HashMap<>();
        int x = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                chars.put(x, new ArrayList<>());
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
