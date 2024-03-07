public class BankAccount {
    String nameHolder;
    Double balance;

    public BankAccount(String nameHolder){
        this.nameHolder = nameHolder;
        this.balance = 0.;
    }

    public void deposit(double amount){
        this.balance += amount;
    }

    public void withdraw(double amount){
        if (amount < this.balance){
            this.balance -= amount;
        }
    }

    public String showBalance(){
        return String.valueOf(this.balance);
    }
}
