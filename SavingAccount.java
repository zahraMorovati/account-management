public class SavingAccount extends Account{

    private final double perMonthProfit=0.1;

    //constructor
    public SavingAccount(int id, double balance, MyDate openingDate) {
        super(id,  balance, openingDate);
    }


    @Override
    public void calculateInterests() {
        super.calculateInterests();
        double temp=perMonthProfit * getBalance();
        setBalance(getBalance()+temp);
        addCurrentTransaction(temp,TransactionType.INTEREST);

    }
}
