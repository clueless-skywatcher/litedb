package io.litedb.filesystem;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class LiteStorageEngine {
    private @Getter File dbDirectory;
    private Map<String, LiteFile> openFiles = new HashMap<>();

    public LiteStorageEngine(String fileDir) {
        this.dbDirectory = new File(fileDir);
        this.dbDirectory.mkdirs();
    }

    public LiteFile getFile(String fileName) {
        try {
            if (!openFiles.containsKey(fileName)) {
                openFiles.put(fileName, new LiteFile(dbDirectory + "/" + fileName));
            }
            return openFiles.get(fileName);
        } catch (IOException e) {
            throw new RuntimeException("Failed to open file: " + fileName);
        }
    }

    public void closeFile(String fileName) {
        getFile(fileName).close();
    }
}
