public class DatabaseManager {
    private PostgreSQLManager postgresManager;
    private MongoDBManager mongoManager;

    public DatabaseManager(){
        postgresManager = new PostgreSQLManager();
        mongoManager = new MongoDBManager();
    }

    public void createClient(Client client){
        mongoManager.createClient(client);
    }

    public void deleteClient(Client client){
        if(mongoManager.deleteClient(client))
            postgresManager.deleteAllAccountsFromClientId(client.getClientid());
    }

    public void deleteClientById(int id){
        if(mongoManager.deleteClientById(id))
            postgresManager.deleteAllAccountsFromClientId(id);
    }

    public void modifyClient(Client client){
        mongoManager.modifyClient(client);
    }

    public void showAllClients(){
        mongoManager.showAllClients();
    }

    public void showClientById(int id){
        mongoManager.showClientById(id);
    }
    public Client getClientById(int id){
        return mongoManager.getClientById(id);
    }

    public void createAccount(Account acc){
        postgresManager.createAccount(acc);
    }

    public void deleteAccount(Account acc){
        postgresManager.deleteAccount(acc);
    }

    public void deleteAccountById(int id){
        postgresManager.deleteAccountById(id);
    }

    public void updateAccount(Account acc){
        postgresManager.updateAccount(acc);
    }

    public void showAllAccounts(){
        postgresManager.showAllAccounts();
    }

    public void showAccountById(int id){
        postgresManager.showAccountById(id);
    }

    public Account getAcctountById(int id){
        return postgresManager.getAccountById(id);
    }

    public void makeTransaction(int origin, int destination, double amount){
        postgresManager.makeTransaction(origin, destination, amount);
    }
}
