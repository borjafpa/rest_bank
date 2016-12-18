package rest_bank;

import java.math.BigDecimal;
import java.util.List;

public class Balance {
	private final List<Transaction> transactions;
	private final BigDecimal total;
	
	public Balance(List<Transaction> transactions, BigDecimal total) {
		this.transactions = transactions;
		this.total = total;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public BigDecimal getTotal() {
		return total;
	}
}
