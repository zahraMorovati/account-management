public class Transaction {

    MyDate date;
    TransactionType type;
    private double amount;

    public Transaction(MyDate date, TransactionType type, double amount) {
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public MyDate getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public void print(){
        System.out.println(date.printDate()+ "  "+type+"  "+amount);
    }
}
