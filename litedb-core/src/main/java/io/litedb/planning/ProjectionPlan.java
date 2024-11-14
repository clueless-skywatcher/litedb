package io.litedb.planning;

import java.io.IOException;
import java.util.List;

import io.litedb.scanning.DBScan;
import io.litedb.scanning.ProjectionScan;

public class ProjectionPlan implements DBPlan {
    private List<String> fields;
    private DBPlan underlyingPlan;

    public ProjectionPlan(DBPlan plan, List<String> fields) {
        this.fields = fields;
        this.underlyingPlan = plan;
    }

    @Override
    public DBScan start() throws IOException {
        return new ProjectionScan(underlyingPlan.start(), fields);
    }
    
    public String toString() {
        return String.format("ProjectionScan on (%s)", underlyingPlan.toString());
    }
}
