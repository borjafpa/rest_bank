package rest_bank;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
	@Autowired
	private UserService userService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public Balance balance() {
		User user = userService.findByUsername(securityService.findLoggedInUsername());

		return new Balance(transactionService.findAll(user.getId()), 
				transactionService.getBalance(user.getId()));
	}

	@RequestMapping(value = "/transaction", method = RequestMethod.GET)
	public Transaction transaction(@RequestParam(value="type") String parameterType, 
			@RequestParam(value="amount") String parameterAmount) {
		BigDecimal amount = new BigDecimal(parameterAmount);

		Transaction transaction = new Transaction();
		transaction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		transaction.setUserId(userService.findByUsername(
				securityService.findLoggedInUsername()).getId());
		transaction.setType(parameterType);
		transaction.setAmount(amount.abs());

		transactionService.save(transaction);

		return transaction;
	}
}
