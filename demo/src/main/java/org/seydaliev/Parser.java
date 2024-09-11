package org.seydaliev;

import org.apache.commons.cli.*;

public class Parser {
    private final Options option;

    public Parser() {
        option = new Options();

        Option outputOption = new Option("o", "output-dir", true, "Укажите директорию для выходных файлов");
        outputOption.setRequired(false);
        option.addOption(outputOption);

        Option prefixOption = new Option("p", "prefix", true, "Укажите префикс для имен файлов");
        prefixOption.setRequired(false);
        option.addOption(prefixOption);

        Option appendOption = new Option("a", "append", false, "Добавить данные в существующие файлы");
        appendOption.setRequired(false);
        option.addOption(appendOption);

        Option shortStatsOption = new Option("s", "short-stats", false, "Вывести краткую статистику");
        shortStatsOption.setRequired(false);
        option.addOption(shortStatsOption);

        Option fullStatsOption = new Option("f", "full-stats", false, "Вывести полную статистику");
        fullStatsOption.setRequired(false);
        option.addOption(fullStatsOption);
    }

    public CommandLine parse(String[] args) throws ParseException {
        DefaultParser parser = new DefaultParser();
        return parser.parse(option, args);
    }
}
