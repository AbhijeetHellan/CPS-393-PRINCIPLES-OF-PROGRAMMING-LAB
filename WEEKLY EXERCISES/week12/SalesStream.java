package week12;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesStream {
	public static void main(String[] args) {
		// Checking command line arguments
		if (args.length < 3) {
			System.out.println("Usage: java SalesStreamApp <username> <password> <database>");
			return;
		}

		String user = args[0];
		String password = args[1];
		String database = args[2];
		String url = "jdbc:mariadb://localhost:3307/" + database;

		// Empty list to store the SalesPerson objects
		List<SalesPerson> salesPersonList = new ArrayList<>();

		// SQL Query to get total sales per salesperson
		// joins the 'salesman' and 'orders' tables and adds up all the sales for each individual person.
		String sql = "SELECT s.name, s.city, s.commission, SUM(o.purchase_amt) as total_sales " + "FROM salesman s "
				+ "JOIN orders o ON s.salesman_id = o.salesman_id "
				+ "GROUP BY s.salesman_id, s.name, s.city, s.commission";

		// the try-catch closes connection when done
		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through each row in the database
			while (rs.next()) {
				// Creates a new SalesPerson object for every row and add it to our list
				salesPersonList.add(new SalesPerson(rs.getString("name"), rs.getString("city"),
						rs.getDouble("commission"), rs.getDouble("total_sales")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Table 1: Sales Performance
		System.out.println("\nTable 1:Sales Performance\n");
		// Printing the header with specific spacing
		System.out.printf("%-15s | %-10s%n", "Salesman Name", "Total Sales");
		System.out.println("--------------------------------------------------");

		// Uses Java Streams to go through the list
		salesPersonList.stream()
				.forEach(sp -> System.out.printf("%-15s | $%,10.2f%n", sp.getName(), sp.getTotalSales()));

		// Table 2: Total Earnings (Commission)
		System.out.println("\nTable 2: Total Earnings (Commission)\n");
		System.out.printf("%-15s | %-10s%n", "Salesman Name", "Total Commission");
		System.out.println("--------------------------------------------------");

		// Uses Streams again to calculate and print the commission money for each
		// person
		salesPersonList.stream().forEach(sp -> {
			double totalComm = sp.getTotalSales() * sp.getCommission();
			System.out.printf("%-15s | $%,10.2f%n", sp.getName(), totalComm);
		});
	}
}