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
import io.litedb.tuples.data.IntegerData;
import io.litedb.tuples.data.TupleData;
import io.litedb.tuples.data.VarcharData;
import io.litedb.tuples.data.info.IntegerInfo;
import io.litedb.tuples.data.info.TupleDatumInfo;
import io.litedb.tuples.data.info.VarcharInfo;

public class LiteCSVImporter {
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
            Map<String, TupleDatumInfo> infos = new HashMap<>();

            for (String field: header) {
                String[] fieldDetails = field.split("\\.");
                String fieldName = fieldDetails[0];
                String fieldType = fieldDetails[1];
                
                if (fieldType.equals("int")) {
                    infos.put(fieldName, new IntegerInfo());
                } else if (fieldType.equals("varchar")) {
                    int fieldLength = Integer.parseInt(fieldDetails[2]);
                    infos.put(fieldName, new VarcharInfo(fieldLength));
                }

                fields.add(fieldName);
            }

            String tableName = fileName.replace(".", "_");

            db.createTable(tableName, infos);

            while (scanner.hasNextLine()) {
                Map<String, TupleData<?>> data = new HashMap<>();
                String[] line = scanner.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                System.out.println(Arrays.toString(line));
                for (int f = 0; f < line.length; f++) {
                    String fieldName = fields.get(f);
                    TupleDatumInfo info = infos.get(fieldName);
                    if (info instanceof IntegerInfo) {
                        data.put(fieldName, new IntegerData(Integer.parseInt(line[f])));
                    } else if (info instanceof VarcharInfo) {
                        data.put(fieldName, new VarcharData(line[f], ((VarcharInfo) info).getMaxSize()));
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
