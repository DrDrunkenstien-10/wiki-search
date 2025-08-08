package com.wikisearch.fetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;

public class WikiFetcher {
    public static void main(String[] args) throws Exception {
        // List of 20 Wikipedia article titles
        String[] titles = {
                "Java_(programming_language)", "Inverted_index", "Search_engine", "Data_structure", "Algorithm",
                "Computer_science", "Information_retrieval", "Software_engineering", "Big_data",
                "Natural_language_processing",
                "Artificial_intelligence", "Machine_learning", "Index_(information_technology)", "PageRank", "Lucene",
                "Apache_Solr", "ElasticSearch", "TF-IDF", "Text_mining", "Web_crawler"
        };

        for (String title : titles) {
            try {
                // Fetch article content from Wikipedia
                Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/" + title).get();
                String content = doc.select("p").text();

                // Clean title for filename (replace illegal filename characters)
                String fileName = "src/main/java/com/wikisearch/input/documents/"
                        + title.replaceAll("[\\\\/:*?\"<>|]", "_") + ".txt";

                // Save to a local text file
                try (FileWriter writer = new FileWriter(fileName)) {
                    writer.write("Title: " + title.replace("_", " ") + "\n\n");
                    writer.write(content);
                }

                System.out.println("Saved: " + fileName);
            } catch (IOException e) {
                System.err.println("Failed to fetch/save article: " + title);
            }
        }

        System.out.println("\n✅ Done. All articles saved as text files.");
    }
}
