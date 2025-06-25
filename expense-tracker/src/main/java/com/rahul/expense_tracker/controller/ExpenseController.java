package com.rahul.expense_tracker.controller;

import com.rahul.expense_tracker.model.Expense;
import com.rahul.expense_tracker.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ExpenseController {

    ExpenseService service;

    ExpenseController(ExpenseService service){
        this.service=service;
    }



    @PostMapping("/addExpense")
    public ResponseEntity<?> insertExpense(@RequestBody Expense expense){
        Expense expense1=service.addExpense(expense);
        return new ResponseEntity<>(expense1, HttpStatus.OK);
    }

    @GetMapping("/getExpenses")
    public ResponseEntity<List<Expense>> getExpenses(){
        List<Expense> expenses=service.allExpenses();
        return new ResponseEntity<>(expenses,HttpStatus.OK);
    }

    @GetMapping("/viewSummary")
    public ResponseEntity<?> viewSummaryOfExpenses(){
        List<Expense> expenses=service.allExpenses();
        int summary=0;
        for(Expense expense:expenses)
            summary+=expense.getAmount();
        return new ResponseEntity<>(summary+"$",HttpStatus.OK);
    }

    @PutMapping("/updateExpense/{Id}")
    public ResponseEntity<?> update(@RequestBody Expense expense,@PathVariable int Id){
     Expense expense1= service.updateExpense(Id,expense);
     if(expense1==null){
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }else {
         return new ResponseEntity<>(expense,HttpStatus.OK);
     }
    }

    @DeleteMapping("/deleteExpense/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable int id){
        service.deleteExpense(id);

            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }

}
