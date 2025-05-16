package io.litedb.planning;

import java.io.IOException;
import java.util.List;

import io.litedb.scanning.LTDBScan;
import io.litedb.scanning.LTProjectionScan;

public class LTProjectionPlan implements LTDBPlan {
    private List<String> fields;
    private LTDBPlan underlyingPlan;

    public LTProjectionPlan(LTDBPlan plan, List<String> fields) {
        this.fields = fields;
        this.underlyingPlan = plan;
    }

    @Override
    public LTDBScan start() throws IOException {
        return new LTProjectionScan(underlyingPlan.start(), fields);
    }
    
    public String toString() {
        return String.format("ProjectionScan on (%s)", underlyingPlan.toString());
    }
}
