package io.litedb;

import java.io.IOException;
import java.util.Scanner;

import io.litedb.liteql.LiteQLParsingMachine;
import io.litedb.liteql.statements.LiteQLStatement;

public class LiteDBMain {
	public static void main(String[] args) throws IOException {
		LiteQLParsingMachine machine = new LiteQLParsingMachine();
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

			LiteQLStatement statementObj = machine.parseStatement(stmt);
			statementObj.execute(db);

			System.out.println(statementObj.getResult().toString());
			System.out.println();
		}

		scanner.close();
	}
}
