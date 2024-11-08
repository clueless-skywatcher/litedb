package io.litedb.metadata;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.litedb.tuples.TableSchema;
import lombok.Getter;

public class MetadataOverseer {
    private @Getter File dbDirectory;
    private static MetadataOverseer INSTANCE;

    private Map<String, TableSchema> tableSchemata = new HashMap<>();

    private MetadataOverseer(String dbDirectory) {
        this.dbDirectory = new File(dbDirectory + "/metadata");
    }

    public static MetadataOverseer getInstance(String dbDirectory) {
        if (INSTANCE == null) {
            INSTANCE = new MetadataOverseer(dbDirectory);
        }
        return INSTANCE;
    }

    public void addTable(String tableName, TableSchema rowSchema) {
        this.tableSchemata.put(tableName, rowSchema);
    }

    public TableSchema getTableSchema(String tableName) {
        return this.tableSchemata.get(tableName);
    }
}
