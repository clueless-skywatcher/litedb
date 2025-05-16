package io.litedb.planning;

import java.io.IOException;

import io.litedb.scanning.LTCartesianProductScan;
import io.litedb.scanning.LTDBScan;

public class LTCartesianProductPlan implements LTDBPlan {
    private LTDBPlan leftPlan;
    private LTDBPlan rightPlan;

    public LTCartesianProductPlan(LTDBPlan leftPlan, LTDBPlan rightPlan) {
        this.leftPlan = leftPlan;
        this.rightPlan = rightPlan;
    }

    @Override
    public LTDBScan start() throws IOException {
        return new LTCartesianProductScan(leftPlan.start(), rightPlan.start());
    }
}
