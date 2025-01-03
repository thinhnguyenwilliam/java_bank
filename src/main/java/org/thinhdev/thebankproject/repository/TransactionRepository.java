package org.thinhdev.thebankproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thinhdev.thebankproject.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>
{

}
