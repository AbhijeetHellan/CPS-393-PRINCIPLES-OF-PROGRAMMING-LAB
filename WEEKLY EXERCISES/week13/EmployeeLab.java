package week13;

import java.sql.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class EmployeeLab {

	public static void main(String[] args) {

		List<Employee> employeesList = new ArrayList<>();

		// 1. Connect to MariaDB and retrieve employee records.
		String url = "jdbc:mariadb://localhost:3307/employee";
		String user = "abhij";
		String password = "tester";
		String query = "select id, name, salary from employees";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				employeesList.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getDouble("salary")));
			}
		} catch (SQLException e) {
			System.err.println(
					"database connection failed. check mariadb, database name, table name, username, and password.");
			e.printStackTrace();
		}

		// 2. Print all employees.
		System.out.println("--- 2. All Employees ---");
		employeesList.forEach(System.out::println);

		// 3. Predicate to filter employees earning more than $50,000.
		Predicate<Employee> isHighEarner = e -> e.getSalary() > 50000;

		List<Employee> highEarners = employeesList.stream().filter(isHighEarner).collect(Collectors.toList());

		// 4. Print high earners.
		System.out.println("\n--- 4. High Earners (> $50,000) ---");
		highEarners.forEach(System.out::println);

		// 5. Function to apply 15% tax reduction to high earners.
		Function<Employee, Employee> applyTax = e -> new Employee(e.getId(), e.getName(), e.getSalary() * 0.85);

		// 6. Function to format salary with a dollar sign.
		Function<Employee, String> formatSalary = e -> String.format("$%.2f", e.getSalary());

		// 7. Filter high earners, apply tax, and collect into a new list.
		List<Employee> taxedHighEarners = employeesList.stream().filter(isHighEarner).map(applyTax)
				.collect(Collectors.toList());

		System.out.println("\n 7. High Earners After 15% Tax ");
		taxedHighEarners.forEach(e -> System.out.println(e.getName() + " | Adjusted Salary: " + formatSalary.apply(e)));

		// 8. Extra: Use partitioningBy to apply different tax rates.
		System.out.println("\n 8. Extra: Partitioned Tax Report ");

		Map<Boolean, List<String>> taxReport = employeesList.stream()
				.collect(Collectors.partitioningBy(isHighEarner, Collectors.mapping(e -> {
					double rate = e.getSalary() > 50000 ? 0.85 : 0.90;
					double newSalary = e.getSalary() * rate;

					return String.format("Name: %-10s | Final Salary: $%.2f", e.getName(), newSalary);
				}, Collectors.toList())));

		taxReport.get(true).forEach(s -> System.out.println("[High Earner] " + s));

		taxReport.get(false).forEach(s -> System.out.println("[Standard] " + s));
	}
}