package rest_bank;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    void save(Transaction transaction);
    
    List<Transaction> findAll(Long userId);
    
    BigDecimal getBalance(Long userId);
}
