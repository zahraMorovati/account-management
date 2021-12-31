package model;

import util.ColorText;
import util.MyDate;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Customer {

    private int id;
    private String name;
    private long phoneNumber;
    private MyDate birthDate;

    // array of checking accounts
    private int maxCheckingAccounts=10;
    private int pointerCheckingAccount=0;
    CheckingAccount[] checkingAccounts=new CheckingAccount[maxCheckingAccounts];

    // array of saving accounts
    private int maxSavingAccounts=10;
    private int pointerSavingAccount=0;
    SavingAccount[] savingAccounts=new SavingAccount[maxSavingAccounts];

    //array of loan accounts
    private int maxLoanAccounts=10;
    private int pointerLoanAccount=0;
    LoanAccount[] loanAccounts=new LoanAccount[maxLoanAccounts];


    //constructor
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer(int id, String name, long phoneNumber, MyDate birthDate) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    // print single customer info
    public void print(){
        System.out.println("id: "+id);
        System.out.println("name: "+name);
        System.out.println("phone number: "+phoneNumber);
        birthDate.print("birth date");
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    //add checking account
    public void addCheckingAccount(int id,double amount,int year,int month,int day){

        MyDate openingDate=new MyDate(year,month,day);
        checkingAccounts[pointerCheckingAccount]=new CheckingAccount(id,amount,openingDate);
        pointerCheckingAccount++;
    }

    //add saving account
    public void addSavingAccount(int id,double amount,int year,int month,int day){
        MyDate openingDate=new MyDate(year,month,day);
        savingAccounts[pointerSavingAccount]=new SavingAccount(id,amount,openingDate);
        pointerSavingAccount++;
    }

    //add loan account
    public void addLoanAccount(int id,double amount,int year,int month,int day){
        MyDate openingDate=new MyDate(year,month,day);
        Scanner input=new Scanner(System.in);
        System.out.print(printBlue("enter loan interestRate: "));
        double interestRate=input.nextDouble();
        System.out.print(printBlue("enter loan term: "));
        int loanTerm=input.nextInt();
        loanAccounts[pointerLoanAccount]=new LoanAccount(id,amount,openingDate,interestRate,loanTerm);
        pointerLoanAccount++;
    }

    //print accounts
    public void printAllAccounts(){
        if(pointerCheckingAccount !=0){
            System.out.println(printBlue("checking account: "));
            IntStream.range(0, pointerCheckingAccount).forEach(i -> {
                checkingAccounts[i].print();
                checkingAccounts[i].calculateFee();
            });
        }
        if(pointerSavingAccount!=0){
            System.out.println(printBlue("saving accounts: "));
            IntStream.range(0, pointerSavingAccount).forEach(i -> savingAccounts[i].print());
        }
        if(pointerLoanAccount!=0){
            System.out.println(printBlue("loan account: "));
            IntStream.range(0, pointerLoanAccount).forEach(i -> loanAccounts[i].print());
        }
    }

    public int searchLoanAccountNumber(int accountNumber){
        for (int i = 0; i < pointerLoanAccount; i++) {
            if(loanAccounts[i].getId()==accountNumber)
                return i;
        }
        return -1;
    }

    public int searchSavingAccountNumber(int accountNumber){
        for (int i = 0; i < pointerSavingAccount; i++) {
            if(savingAccounts[i].getId()==accountNumber)
                return i;
        }
        return -1;
    }

    public int searchCheckingAccountNumber(int accountNumber){
        for (int i = 0; i < pointerCheckingAccount; i++) {
            if(checkingAccounts[i].getId()==accountNumber)
                return i;
        }
        return -1;
    }

    public String printRed(String str){
        return ColorText.TEXT_RED.getColorCODE()+str+ ColorText.TEXT_RESET.getColorCODE();
    }
    public String printBlue(String str){
        return (ColorText.TEXT_BLUE.getColorCODE()+str+ ColorText.TEXT_RESET.getColorCODE());
    }

}
