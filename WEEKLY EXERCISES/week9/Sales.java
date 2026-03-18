package week9;

public class Sales {
	int orderNumber;
	String customerName;
	String customerCity;
	String salesmanName;
	double amount;
	double commissionAmount;

	public Sales(int orderNumber, String customerName, String customerCity, String salesmanName, double amount,
			double commissionAmount) {
		this.orderNumber = orderNumber;
		this.customerName = customerName;
		this.customerCity = customerCity;
		this.salesmanName = salesmanName;
		this.amount = amount;
		this.commissionAmount = commissionAmount;
	}

	@Override
	public String toString() {
		return String.format("Order: %d | Customer: %s (%s) | Salesman: %s | Amount: $%.2f | Commission: $%.2f",
				orderNumber, customerName, customerCity, salesmanName, amount, commissionAmount);
	}
}