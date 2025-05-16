package io.litedb;

import java.io.IOException;
import java.util.Scanner;

import io.litedb.liteql.LTParsingMachine;
import io.litedb.liteql.statements.LTStatement;
import io.litedb.liteql.statements.LTSelectFromTableStatement;
import io.litedb.planning.LTDBPlan;

public class LiteDBMain {
	public static void main(String[] args) throws IOException {
		LTParsingMachine machine = new LTParsingMachine();
		LiteDB db = new LiteDB(".litedb");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print(">> ");
			String stmt = scanner.nextLine();
			if (stmt.length() == 0) {
				continue;
			}
			if (stmt.equals("!q")) {
				break;
			}

			LTStatement statementObj = machine.parseStatement(stmt);

			if (statementObj.isDQL()) {
				LTDBPlan plan = db.getQueryPlanner().createPlan((LTSelectFromTableStatement) statementObj);
				statementObj.execute(db, plan);
			}
			else if (statementObj.isDML()) {
				LTDBPlan plan = db.getModifyPlanner().createPlan(statementObj);
				statementObj.execute(db, plan);
			}
			else {
				statementObj.execute(db);
			}
			
			System.out.println(statementObj.getResult().toString());
			System.out.println();
		}

		scanner.close();
	}
}
