package com.rahul.expense_tracker.repository;

import com.rahul.expense_tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense,Integer> {


}
