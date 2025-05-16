package io.litedb.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import io.litedb.LiteDB;
import io.litedb.tuples.data.LTIntegerData;
import io.litedb.tuples.data.LTTupleData;
import io.litedb.tuples.data.LTVarcharData;
import io.litedb.tuples.data.info.LTIntegerInfo;
import io.litedb.tuples.data.info.LTTupleDatumInfo;
import io.litedb.tuples.data.info.LTVarcharInfo;

public class LTCSVImporter {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Require a filename to open");
        }

        String fileName = args[0];

        try {
            LiteDB db = new LiteDB("/tmp/.litedb");   
            Scanner scanner = new Scanner(new File(fileName));
            
            String[] header = scanner.nextLine().split(",");
            List<String> fields = new ArrayList<>();
            Map<String, LTTupleDatumInfo> infos = new HashMap<>();

            for (String field: header) {
                String[] fieldDetails = field.split("\\.");
                String fieldName = fieldDetails[0];
                String fieldType = fieldDetails[1];
                
                if (fieldType.equals("int")) {
                    infos.put(fieldName, new LTIntegerInfo());
                } else if (fieldType.equals("varchar")) {
                    int fieldLength = Integer.parseInt(fieldDetails[2]);
                    infos.put(fieldName, new LTVarcharInfo(fieldLength));
                }

                fields.add(fieldName);
            }

            String tableName = fileName.replace(".", "_");

            db.createTable(tableName, infos);

            while (scanner.hasNextLine()) {
                Map<String, LTTupleData<?>> data = new HashMap<>();
                String[] line = scanner.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                System.out.println(Arrays.toString(line));
                for (int f = 0; f < line.length; f++) {
                    String fieldName = fields.get(f);
                    LTTupleDatumInfo info = infos.get(fieldName);
                    if (info instanceof LTIntegerInfo) {
                        data.put(fieldName, new LTIntegerData(Integer.parseInt(line[f])));
                    } else if (info instanceof LTVarcharInfo) {
                        data.put(fieldName, new LTVarcharData(line[f], ((LTVarcharInfo) info).getMaxSize()));
                    } 
                }

                db.insertValues(tableName, data);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Cannot open file: " + e.getMessage());
        }
    }
}
