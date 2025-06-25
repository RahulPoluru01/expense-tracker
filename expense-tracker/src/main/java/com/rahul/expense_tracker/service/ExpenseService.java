package com.rahul.expense_tracker.service;

import com.rahul.expense_tracker.model.Expense;
import com.rahul.expense_tracker.repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepo repo;
    public Expense addExpense(Expense expense){
        return repo.save(expense);
    }

    public List<Expense> allExpenses() {
        return repo.findAll();
    }

    public Expense updateExpense(int expenseId,Expense expense) {
        expense.setId(expenseId);
       return repo.save(expense);
    }

    public void deleteExpense(int expenseId) {
        repo.deleteById(expenseId);
    }
}
