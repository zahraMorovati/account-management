package model;

import model.enumation.TransactionType;
import util.ColorText;
import util.MyDate;

public class Account {

    private int id;
    private double balance;
    private MyDate openingDate;
    private int maxTransaction = 30;
    private int pointerTransaction = 0;
    Transaction[] transactions = new Transaction[maxTransaction];

    public Account(int id, double balance, MyDate openingDate) {
        this.id = id;
        this.balance = balance;
        this.openingDate = openingDate;
    }

    public Account(int id, double balance, MyDate openingDate, Transaction[] transactions) {
        this.id = id;
        this.balance = balance;
        this.openingDate = openingDate;
        this.transactions = transactions;
    }

    public void withdraw(double withdrawAmount) {
        double temp = balance - withdrawAmount;
        balance = temp;
        addCurrentTransaction(withdrawAmount, TransactionType.WITHDRAW);
    }

    public void deposit(double depositAmount) {
        double temp = balance + depositAmount;
        balance = temp;
        addCurrentTransaction(depositAmount, TransactionType.DEPOSIT);
    }

    public void print() {
        System.out.println(printWight("account number : " + id));
        System.out.println(printWight("balance: " + balance));
        openingDate.print(printWight("opening date"));
    }

    public void calculateInterests() {

    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MyDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(MyDate openingDate) {
        this.openingDate = openingDate;
    }


    public MyDate getCurrentDate(){
        int year=java.time.LocalDate.now().getYear();
        int month=java.time.LocalDate.now().getMonthValue();
        int day=java.time.LocalDate.now().getDayOfMonth();
        MyDate currentDate=new MyDate(year,month,day);
        return currentDate;
    }

    //add current transaction
    public void addCurrentTransaction(double amount, TransactionType type) {

        transactions[pointerTransaction] = new Transaction(getCurrentDate(), type, amount);
        pointerTransaction++;
    }

    //print transactions
    public void printTransactions() {
        if (pointerTransaction != 0) {
            System.out.println(printBlue("transactions:"));
            for (int i = 0; i < pointerTransaction; i++) {
                transactions[i].print();
            }
        } else {
            System.out.println(printRed("there is no transaction!"));

        }

    }
    public String printRed(String str){
        return ColorText.TEXT_RED.getColorCODE()+str+ ColorText.TEXT_RESET.getColorCODE();
    }
    public String printBlue(String str){
      return (ColorText.TEXT_BLUE.getColorCODE()+str+ ColorText.TEXT_RESET.getColorCODE());
    }
    public String printWight(String str){
        return (ColorText.TEXT_WHITE.getColorCODE()+str+ ColorText.TEXT_RESET.getColorCODE());
    }
    public String printGreen(String str){
        return (ColorText.TEXT_GREEN.getColorCODE()+str+ ColorText.TEXT_RESET.getColorCODE());
    }
}
