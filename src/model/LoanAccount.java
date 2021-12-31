package model;

import model.Account;
import model.enumation.TransactionType;
import util.MyDate;

public class LoanAccount extends Account {

    private double interestRate;
    private int loanTerm;

    public LoanAccount(int id, double balance, MyDate openingDate, double interestRate, int loanTerm) {
        super(id, balance, openingDate);
        this.interestRate = interestRate;
        this.loanTerm = loanTerm;
    }


    @Override
    public void calculateInterests() {
        super.calculateInterests();
        double temp= (interestRate)*(getBalance());
        setBalance(getBalance()+temp);
        addCurrentTransaction(temp, TransactionType.INTEREST);
    }

}
