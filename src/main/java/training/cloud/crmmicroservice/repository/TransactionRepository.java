package training.cloud.crmmicroservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.cloud.crmmicroservice.model.entity.Transaction;


@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, String> {
}