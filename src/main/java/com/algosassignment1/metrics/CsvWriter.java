package com.algosassignment1.metrics;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    public static void writeLine(String file, String... values) throws IOException {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(String.join(",", values));
            fw.write("\n");
        }
    }
}

