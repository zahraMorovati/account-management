package model;

import model.Account;
import model.Transaction;
import model.enumation.TransactionType;
import util.MyDate;

public class CheckingAccount extends Account {

    private final int wage=700;
    private final long withdrawalLimit= 2_000_000;

    //constructor
    public CheckingAccount(int id, double balance, MyDate openingDate) {
        super(id, balance, openingDate);
    }

    public CheckingAccount(int id, double balance, MyDate openingDate, Transaction[] transactions) {
        super(id, balance, openingDate, transactions);
    }

    public void calculateFee(){
        double temp=(getBalance())-(wage);
        setBalance(temp);
        addCurrentTransaction(wage, TransactionType.FEES);
    }

    @Override
    public void withdraw(double withdrawAmount) {
        if(withdrawAmount<withdrawalLimit){
            super.withdraw(withdrawAmount);
        }else {
            System.out.println(printRed("you can only withdraw less than "+withdrawalLimit+" !"));
        }

    }
}
