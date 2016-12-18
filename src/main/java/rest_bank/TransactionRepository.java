package rest_bank;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	@Query("select t from Transaction t where t.userId = ?1 order by createdAt desc")
	 public List<Transaction> findByUserId(Long userId);
}
