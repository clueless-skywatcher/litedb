package io.litedb.tuples.data.info;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface LTTupleDatumInfo {
    public int getSize();
    public String getTypeString();

    public static LTTupleDatumInfo inferTypeFromString(String fieldType) {
        LTTupleDatumInfo info = null;
        switch (fieldType.toLowerCase()) {
            case "int":
                info = new LTIntegerInfo();
                break;
            case "boolean":
                info = new LTBooleanInfo();
                break;
            default:
                if (fieldType.matches("varchar\\([0-9]+\\)")) {
                    Pattern pattern = Pattern.compile("-?\\d+");
                    Matcher matcher = pattern.matcher(fieldType);
                    int charLength;
                    while (matcher.find()) {
                        charLength = Integer.parseInt(matcher.group());
                        info = new LTVarcharInfo(charLength);
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
