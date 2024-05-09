package com.pristine.lombardo.repository;

import com.pristine.lombardo.addition.LoanStatus;
import com.pristine.lombardo.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByStatus(LoanStatus status);
}
