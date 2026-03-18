package week9;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
	public static void main(String[] args) {
		// check command line inputs
		if (args.length < 3) {
			System.out.println("Usage: java DBConnection <username> <password> <database>");
			return;
		}

		String user = args[0];
		String password = args[1];
		String database = args[2];
		String url = "jdbc:mariadb://localhost:3307/" + database;

		ArrayList<Sales> salesList = new ArrayList<>();

		// connect to database
		try (Connection conn = DriverManager.getConnection(url, user, password)) {

			System.out.println("Connected to MariaDB database: " + database);

			// join all 3 tables
			String sql = "SELECT o.order_no, c.customer_name, c.city, s.name, o.purchase_amt, "
					+ "(o.purchase_amt * s.commission) AS comm_amt "
					+ "FROM orders o "
					+ "JOIN customer c ON o.customer_id = c.customer_id "
					+ "JOIN salesman s ON o.salesman_id = s.salesman_id "
					+ "ORDER BY o.order_no";

			try (PreparedStatement stmt = conn.prepareStatement(sql);
				 ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					// make object and add to list
					salesList.add(
							new Sales(
									rs.getInt("order_no"),
									rs.getString("customer_name"),
									rs.getString("city"),
									rs.getString("name"),
									rs.getDouble("purchase_amt"),
									rs.getDouble("comm_amt")
							));
				}
			}

			if (salesList.isEmpty()) {
				System.out.println("No records found.");
			} else {
				System.out.println("\nSales records:");
				for (Sales sale : salesList) {
					System.out.println(sale);
				}
				System.out.println("Total records: " + salesList.size());
			}

		} catch (SQLException e) {
			System.out.println("Database error:");
			e.printStackTrace();
		}
	}
}