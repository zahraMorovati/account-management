import model.Manager;
import model.enumation.AccountType;
import util.ColorText;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();
        login(manager);

    }

    public static void login(Manager manager) {
        Scanner input = new Scanner(System.in);
        String username;
        String password;

        System.out.print(printBlue("user name: "));
        username = input.next();
        if (username.equals(manager.getUserName())) {
            System.out.print(printBlue("password: "));
            password = input.next();
            if (password.equals(manager.getPassword())) {
                managerMenu(manager);
            } else {
                System.out.println(printRed("wrong password!"));
                login(manager);
            }
        } else {
            System.out.println(printRed("wrong username!"));
            login(manager);
        }
    }

    public static void managerMenu(Manager manager) {

        System.out.println(printBlue("******** manger menu ********"));
        System.out.println("1)add new account\n2)view accounts \n3)withdraw \n4)deposit \n5)calculate interests \n6)view transactions \n7)exit");
        System.out.print("enter number of your choice:");
        Scanner input = new Scanner(System.in);
        String choice = input.next();
        try {
            try {
                switch (choice) {
                    case "1": {

                        System.out.print(printBlue("enter customer name: "));
                        String name = input.next();
                        System.out.print(printBlue("enter amount: "));
                        double amount = input.nextDouble();
                        System.out.print(printBlue("enter year month day: "));
                        int year = input.nextInt();
                        int month = input.nextInt();
                        int day = input.nextInt();
                        System.out.println(printWight("1)checking account \n2)saving account \n3)loan account"));
                        System.out.print(printWight("enter number of your choice: "));
                        int accountType = input.nextInt();
                        switch (accountType) {
                            case 1: {
                                manager.addAccount(name, amount, year, month, day, AccountType.CHECKING);
                            }
                            break;
                            case 2: {
                                manager.addAccount(name, amount, year, month, day, AccountType.SAVING);

                            }
                            break;
                            case 3: {
                                manager.addAccount(name, amount, year, month, day, AccountType.LOAN);

                            }
                            break;
                            default: {
                                System.out.println();
                            }
                            break;
                        }
                        managerMenu(manager);
                    }
                    break;
                    case "2": {

                        System.out.print(printBlue("enter customer name: "));
                        String name = input.next();
                        manager.viewAccounts(name);
                        managerMenu(manager);

                    }
                    break;
                    case "3": {
                        System.out.print(printBlue("enter account number: "));
                        int accountNumber = input.nextInt();
                        System.out.println(printBlue("enter amount: "));
                        double amount = input.nextDouble();
                        manager.withdraw(accountNumber, amount);
                        managerMenu(manager);
                    }
                    break;
                    case "4": {
                        System.out.print(printBlue("enter account number: "));
                        int accountNumber = input.nextInt();
                        System.out.println(printBlue("enter amount: "));
                        double amount = input.nextDouble();
                        manager.deposit(accountNumber, amount);
                        managerMenu(manager);
                    }
                    break;
                    case "5": {
                        System.out.print(printBlue("enter account number: "));
                        int accountNumber = input.nextInt();
                        manager.calculateInterests(accountNumber);
                        managerMenu(manager);
                    }
                    break;
                    case "6": {
                        System.out.print(printBlue("enter account number: "));
                        int accountNumber = input.nextInt();
                        manager.printAllTransactions(accountNumber);
                        managerMenu(manager);
                    }
                    break;
                    case "7": {
                        System.exit(0);
                    }
                    break;
                    default: {
                        System.out.println(printRed("wrong choice!"));
                        managerMenu(manager);
                    }
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(printRed("Invalid input. "));
                managerMenu(manager);
            }
        } catch (NumberFormatException exception) {
            System.out.println(printRed("invalid input!"));
            managerMenu(manager);
        }


    }


    public static String printRed(String str) {
        return ColorText.TEXT_RED.getColorCODE() + str + ColorText.TEXT_RESET.getColorCODE();
    }

    public static String printBlue(String str) {
        return (ColorText.TEXT_BLUE.getColorCODE() + str + ColorText.TEXT_RESET.getColorCODE());
    }

    public static String printWight(String str) {
        return (ColorText.TEXT_WHITE.getColorCODE() + str + ColorText.TEXT_RESET.getColorCODE());
    }

    public static String printGreen(String str) {
        return (ColorText.TEXT_GREEN.getColorCODE() + str + ColorText.TEXT_RESET.getColorCODE());
    }
}
