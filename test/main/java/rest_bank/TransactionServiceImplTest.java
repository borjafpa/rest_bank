package rest_bank;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionServiceImplTest {
	
	@Autowired
	private TransactionService transactionService;

	@Test
	public void testSave() {
		Long userId = 1L;
		BigDecimal amount = new BigDecimal(5);
		
		Transaction transaction = saveTransaction(userId, "Deposit", amount);

		assertNotNull(transaction.getId());
	}

	@Test
	public void testGetBalance() {
		Long firstUserId = 2L;
		Long secondUserId = 3L;
		
		BigDecimal firstAmount = new BigDecimal(5);
		BigDecimal secondAmount = new BigDecimal(3);
		BigDecimal thirdAmount = new BigDecimal(1);
		
		saveTransaction(firstUserId, "Deposit", firstAmount);
		saveTransaction(firstUserId, "Withdraw", thirdAmount);
		saveTransaction(secondUserId, "Deposit", secondAmount);
		
		assertEquals(transactionService.getBalance(firstUserId).compareTo(firstAmount.subtract(thirdAmount)), 0);
		assertEquals(transactionService.getBalance(secondUserId).compareTo(secondAmount), 0);
	}
	
	private Transaction saveTransaction(Long userId, String type, BigDecimal amount) {
		Transaction transaction = new Transaction();
		
		transaction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		transaction.setUserId(userId);
		transaction.setType(type);
		transaction.setAmount(amount);
		
		transactionService.save(transaction);
		
		return transaction;
	}
}
