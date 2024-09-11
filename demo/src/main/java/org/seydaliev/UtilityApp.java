package org.seydaliev;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import java.io.FileNotFoundException;
import java.util.Map;

public class UtilityApp {
    public static void main(String[] args) {
        try {
            Parser parser = new Parser();
            CommandLine commandLine = parser.parse(args);
            FileProcessor processor = new FileProcessor();

            String outputDir  = commandLine.getOptionValue("o", ".");
            String prefix  = commandLine.getOptionValue("p", "");
            Boolean appendMode  = commandLine.hasOption("a");

            String[] inputFiles = commandLine.getArgs();
            if (inputFiles.length == 0) {
                System.out.println("Hет данных");
            }
            for (String inputfile: inputFiles) {
                processor.processFile(inputfile,outputDir,prefix,appendMode);
            }
            printStatistics(processor.getStatistics(),commandLine.hasOption("f"));
        } catch (ParseException | FileNotFoundException e) {
            System.out.println("Ошибка при обработке файлов: " + e.getMessage());
        }
    }

    private static void printStatistics(Map<FileType, Statistics> statistics, boolean fullStats) {
        for (FileType fileType : FileType.values()) {
            Statistics stats = statistics.get(fileType);
            if (stats != null) {
                System.out.println("Статистика для " + fileType.name() + ":");
                System.out.println("Количество элементов: " + stats.getCount());
                if (fullStats && (fileType == FileType.INTEGERS || fileType == FileType.FLOAT)) {
                    System.out.printf("Минимальное значение: %.2f%n", stats.getMin());
                    System.out.printf("Максимальное значение: %.2f%n", stats.getMax());
                    System.out.printf("Сумма: %.2f%n", stats.getSum());
                    System.out.printf("Среднее значение: %.2f%n", stats.getAverage());
                }
                System.out.println();
            }
        }
    }
}