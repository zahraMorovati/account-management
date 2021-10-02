public class Manager {

    private String userName = "admin";
    private String password = "1234";

    //relate to customers
    private int maxCustomer = 100;
    private int pointerCustomers = 0;
    Customer[] customers = new Customer[maxCustomer];

    //add customer
    public void addCustomer(String name) {
        customers[pointerCustomers] = new Customer(pointerCustomers, name);
        pointerCustomers++;
    }

    public void addCustomer(String name, long phoneNumber, int year, int month, int day) {

        MyDate birthDate = new MyDate(year, month, day);
        if (birthDate != null) {
            customers[pointerCustomers] = new Customer(pointerCustomers, name, phoneNumber, birthDate);
            pointerCustomers++;
        } else {
            System.out.println(printRed("invalid birth date!"));
        }
    }


    public int searchCustomerByName(String name) {
        for (int i = 0; i < pointerCustomers; i++) {
            if (customers[i].getName().equalsIgnoreCase(name))
                return i;
        }
        return -1;
    }

    //add account
    public void addAccount(String name, double amount, int year, int month, int day, AccountType accountType) {

        if (searchCustomerByName(name) == -1) {
            addCustomer(name);
        }

        int indexCustomer = searchCustomerByName(name);

        if (indexCustomer != -1) {

            switch (accountType) {
                case CHECKING: {
                    customers[indexCustomer].addCheckingAccount(randomAccountNUmber(), amount, year, month, day);
                }
                break;
                case SAVING: {
                    customers[indexCustomer].addSavingAccount(randomAccountNUmber(), amount, year, month, day);
                }
                break;
                case LOAN: {
                    customers[indexCustomer].addLoanAccount(randomAccountNUmber(), amount, year, month, day);
                }
                break;
                default: {
                    System.out.println(printRed("The operation failed!"));
                }
            }
        }

    }

    //view accounts
    public void viewAccounts(String name) {
        int indexCustomer = searchCustomerByName(name);
        if (indexCustomer != -1) {
            customers[indexCustomer].printAllAccounts();
        } else {
            System.out.println(printRed("customer not found!"));
        }
    }

    public int searchCustomerById(int customerId){
        for (int i = 0; i < pointerCustomers; i++) {
            if(customers[i].getId()==customerId){
                return i;
            }
        }
        return -1;
    }


    //calculate interests
    public void calculateInterests(int accountId) {
        boolean isValidAccountNumber = false;
        for (int i = 0; i < pointerCustomers; i++) {
            int indexLoanAccountNumber = customers[i].searchLoanAccountNumber(accountId);
            if (indexLoanAccountNumber != -1) {
                customers[i].loanAccounts[indexLoanAccountNumber].calculateInterests();

                isValidAccountNumber=true;
            } else {
                int indexSavingAccountNumber = customers[i].searchSavingAccountNumber(accountId);
                if (indexSavingAccountNumber != -1) {
                    customers[i].savingAccounts[indexSavingAccountNumber].calculateInterests();
                    isValidAccountNumber=true;
                }
            }
        }
        if(isValidAccountNumber){
            System.out.println(printGreen("The operation was successful !"));
        }else {
            System.out.println(printRed("The operation was failed !"));

        }
    }

    //withdraw
    public void withdraw(int accountNumber,double amount){
        boolean isValidAccountNumber = false;
        for (int i = 0; i < pointerCustomers; i++) {
            int indexLoanAccountNumber = customers[i].searchLoanAccountNumber(accountNumber);
            if (indexLoanAccountNumber != -1) {
                customers[i].loanAccounts[indexLoanAccountNumber].withdraw(amount);
                isValidAccountNumber=true;

            } else {
                int indexSavingAccountNumber = customers[i].searchSavingAccountNumber(accountNumber);
                if (indexSavingAccountNumber != -1) {
                    customers[i].savingAccounts[indexSavingAccountNumber].withdraw(amount);
                    isValidAccountNumber=true;
                }else {
                    int indexCheckingAccountNumber=customers[i].searchCheckingAccountNumber(accountNumber);
                    if(indexCheckingAccountNumber != -1){
                        customers[i].checkingAccounts[indexCheckingAccountNumber].withdraw(amount);
                        isValidAccountNumber=true;
                    }
                }
            }
        }
        if(isValidAccountNumber){
            System.out.println(printGreen("The operation was successful !"));

        }else {
            System.out.println(printRed("The operation was failed !"));

        }
    }

    //deposit
    public void deposit(int accountNumber,double amount){
        boolean isValidAccountNumber = false;
        for (int i = 0; i < pointerCustomers; i++) {
            int indexLoanAccountNumber = customers[i].searchLoanAccountNumber(accountNumber);
            if (indexLoanAccountNumber != -1) {
                customers[i].loanAccounts[indexLoanAccountNumber].deposit(amount);
                isValidAccountNumber=true;

            } else {
                int indexSavingAccountNumber = customers[i].searchSavingAccountNumber(accountNumber);
                if (indexSavingAccountNumber != -1) {
                    customers[i].savingAccounts[indexSavingAccountNumber].deposit(amount);
                    isValidAccountNumber=true;
                }else {
                    int indexCheckingAccountNumber=customers[i].searchCheckingAccountNumber(accountNumber);
                    if(indexCheckingAccountNumber != -1){
                        customers[i].checkingAccounts[indexCheckingAccountNumber].deposit(amount);
                        isValidAccountNumber=true;
                    }
                }
            }
        }
        if(isValidAccountNumber){
            System.out.println(printGreen("The operation was successful !"));
        }else {
            System.out.println(printRed("The operation was failed !"));

        }
    }

    //print transaction
    public void printAllTransactions(int accountNumber){

        for (int i = 0; i < pointerCustomers; i++) {
            int indexLoanAccountNumber = customers[i].searchLoanAccountNumber(accountNumber);
            if (indexLoanAccountNumber != -1) {
                customers[i].loanAccounts[indexLoanAccountNumber].printTransactions();

            } else {
                int indexSavingAccountNumber = customers[i].searchSavingAccountNumber(accountNumber);
                if (indexSavingAccountNumber != -1) {
                    customers[i].savingAccounts[indexSavingAccountNumber].printTransactions();

                }else {
                    int indexCheckingAccountNumber=customers[i].searchCheckingAccountNumber(accountNumber);
                    if(indexCheckingAccountNumber != -1){
                        customers[i].checkingAccounts[indexCheckingAccountNumber].printTransactions();
                    }
                }
            }
        }

    }

    public String printRed(String str){
        return ColorText.TEXT_RED.getColorCODE()+str+ColorText.TEXT_RESET.getColorCODE();
    }

    public String printGreen(String str){
        return (ColorText.TEXT_GREEN.getColorCODE()+str+ColorText.TEXT_RESET.getColorCODE());
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int randomAccountNUmber(){
        int min=1000000;
        int max=9999999;
        int randomAccountNumber=(int)Math.floor(Math.random()*(max-min+1)+min);
        return randomAccountNumber;

    }
}
