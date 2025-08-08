package com.wikisearch.index;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Indexer {
    private static final String[] stopWords = { "i", "me", "my", "myself", "we", "our", "ours",
            "you", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she",
            "her", "hers", "herself", "it", "its", "itself", "they", "them", "their", "theirs",
            "themselves", "what", "which", "who", "whom", "this", "that", "these", "those", "am",
            "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having",
            "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because",
            "as", "until", "while", "of", "at", "by", "for", "with", "about", "against", "between",
            "into", "through", "during", "before", "after", "above", "below", "to", "from", "up",
            "down", "in", "out", "on", "off", "over", "under", "again", "further", "then", "once",
            "here", "there", "when", "where", "why", "how", "all", "any", "both", "each", "few",
            "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same",
            "so", "than", "too", "very", "s", "t", "can", "will", "just", "don", "should", "now",
            "ourselves" };

    private static final Set<String> stopWordsSet = new HashSet<>(Arrays.asList(stopWords));

    public static Map<String, HashSet<String>> index(String path) {
        Map<String, HashSet<String>> invertedIndex = new HashMap<>();

        try {
            File[] files = findAndReturnFilesArray(path);

            for (File file : files) {
                File fileObject = new File(file.getAbsolutePath());

                Scanner scanner = new Scanner(fileObject);

                while (scanner.hasNext()) {
                    String word = scanner.next();

                    String cleanedWord = word.replaceAll("[^a-z0-9]", "").toLowerCase();
                    if (!stopWordsSet.contains(cleanedWord)) {
                        if (!invertedIndex.containsKey(cleanedWord)) {
                            invertedIndex.put(cleanedWord, new HashSet<>());
                        }
                        invertedIndex.get(cleanedWord).add(file.getName());
                    }
                }

                scanner.close();
            }
        } catch (Exception e) {
            System.err.println("An error occurred while indexing the files at path: " + path);
        }

        return invertedIndex;
    }

    // Had to create a separate method to avoid duplicating naming and collisions.
    private static File[] findAndReturnFilesArray(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        return files;
    }
}
