package rest_bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	
    @Override
    public void save(Transaction transaction) {
    	transactionRepository.save(transaction);
    }
    
	@Override
	public BigDecimal getBalance(Long userId) {
		List<Transaction> transactions = findAll(userId);
		return sumAll(transactions, "Deposit").subtract(sumAll(transactions, "Withdraw"));
	}
	
	@Override
	public List<Transaction> findAll(Long userId) {
		return transactionRepository.findByUserId(userId);
	}
	
	private BigDecimal sumAll(List<Transaction> userTransactions, String transactionType) {
		List<BigDecimal> toSum = new ArrayList<BigDecimal>();
		
		userTransactions.forEach(t ->  {
			if (transactionType.equals(t.getType())) {
				toSum.add(t.getAmount());
			}
		});
		
		return toSum.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
