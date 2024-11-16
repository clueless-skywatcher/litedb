package io.litedb.tuples.data.info;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface TupleDatumInfo {
    public int getSize();
    public String getTypeString();

    public static TupleDatumInfo inferTypeFromString(String fieldType) {
        TupleDatumInfo info = null;
        switch (fieldType.toLowerCase()) {
            case "int":
                info = new IntegerInfo();
                break;
            case "boolean":
                info = new BooleanInfo();
                break;
            default:
                if (fieldType.matches("varchar\\([0-9]+\\)")) {
                    Pattern pattern = Pattern.compile("-?\\d+");
                    Matcher matcher = pattern.matcher(fieldType);
                    int charLength;
                    while (matcher.find()) {
                        charLength = Integer.parseInt(matcher.group());
                        info = new VarcharInfo(charLength);
                        break;
                    }
                } else {
                    throw new RuntimeException("Invalid fieldType read from file: " + fieldType);
                }
                break;
        }

        return info;
    }
}
