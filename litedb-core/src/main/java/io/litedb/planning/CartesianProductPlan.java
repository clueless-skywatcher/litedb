package io.litedb.planning;

import java.io.IOException;

import io.litedb.scanning.CartesianProductScan;
import io.litedb.scanning.DBScan;

public class CartesianProductPlan implements DBPlan {
    private DBPlan leftPlan;
    private DBPlan rightPlan;

    public CartesianProductPlan(DBPlan leftPlan, DBPlan rightPlan) {
        this.leftPlan = leftPlan;
        this.rightPlan = rightPlan;
    }

    @Override
    public DBScan start() throws IOException {
        return new CartesianProductScan(leftPlan.start(), rightPlan.start());
    }
}
