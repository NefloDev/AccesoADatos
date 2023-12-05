public class Account {
    private int accountid;
    private String iban;
    private double balance;
    private int clientid;

    public Account() {
    }

    public Account(String iban, double balance, int clientid) {
        this.iban = iban;
        this.balance = balance;
        this.clientid = clientid;
    }

    @Override
    public String toString() {
        return "Id: " + accountid +
                "\n\tIban: " + iban +
                "\n\tBalance: " + balance + "\n";
    }

    public int getId() {
        return accountid;
    }

    public void setId(int id) {
        this.accountid = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }
}
