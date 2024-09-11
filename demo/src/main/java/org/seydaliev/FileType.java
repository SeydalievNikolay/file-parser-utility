package org.seydaliev;

public enum FileType {
    INTEGERS ("integers.txt"),
    FLOAT("floats.txt"),
    STRING("strings.txt");
    private final String fileName;

    FileType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
