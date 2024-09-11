package org.seydaliev;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileProcessor {
    private Map<FileType, Statistics> statisticsMap = new HashMap<>();
    private Map<FileType, BufferedWriter> writerMap = new HashMap<>();

    public void processFile(String filePath, String outputDir, String prefix, boolean appendMode) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                FileType fileType = determineFileType(line);
                if (fileType != null) {
                    processLine(fileType, line, outputDir, prefix, appendMode);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processLine(FileType fileType, String line, String outputDir, String prefix, boolean appendMode) throws IOException {
        Statistics statistics = statisticsMap.computeIfAbsent(fileType, k -> new Statistics());
        switch (fileType) {
            case INTEGERS, FLOAT:
                double value  = Double.parseDouble(line);
                statistics.add(value);
                break;
            case STRING:
                statistics.getCount();
                break;
        }

        try (BufferedWriter writer = getWriter(fileType, outputDir, prefix, appendMode)) {
            writer.write(line);
            writer.newLine();
        }
    }

    private BufferedWriter getWriter(FileType fileType, String outputDir, String prefix, boolean appendMode) throws IOException {
        return writerMap.computeIfAbsent(fileType, k -> {
            try {
                File outputFile = new File(outputDir, prefix + fileType.getFileName());
                outputFile.getParentFile().mkdirs();
                return new BufferedWriter(new FileWriter(outputFile, appendMode));
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при создании файла вывода", e);
            }
        });
    }

    private FileType determineFileType(String line) {
        try {
            Double.parseDouble(line);
            return line.contains(".") ? FileType.FLOAT : FileType.INTEGERS;
        } catch (NumberFormatException e) {
            return FileType.STRING;
        }
    }

    public Map<FileType, Statistics> getStatistics() {
        return statisticsMap;
    }

}
