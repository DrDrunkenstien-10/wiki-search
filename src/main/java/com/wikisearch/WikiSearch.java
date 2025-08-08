package com.wikisearch;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import com.wikisearch.index.Indexer;

public class WikiSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the path of the documents you want to index:");
        String path = scanner.next();

        Map<String, HashSet<String>> invertedIndex = Indexer.index(path);

        System.out.println("Inverted Index content:");
        for (Map.Entry<String, HashSet<String>> entry : invertedIndex.entrySet()) {
            String word = entry.getKey();
            HashSet<String> files = entry.getValue();

            System.out.println(word + " : " + "[ " + files + " ]");
        }

        System.out.println("Enter the word you want to search:");
        String searchWord = scanner.next();

        System.out.println("The word appeared in the following files:");
        HashSet<String> files = invertedIndex.get(searchWord.toLowerCase());

        for (String file : files) {
            System.out.println(file);
        }

        scanner.close();
    }
}
