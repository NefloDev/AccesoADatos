
public class Main {

    public static void main(String[] args) {
        DatabaseManager manager = new DatabaseManager();
        Client fake = new Client(
                "Alejandro",
                "Nebot Flores",
                "666666666",
                "aleneflo",
                "1234",
                new Address("Calle Falsa", 12550, "Almassora", "Castelló"),
                "12345678A",
                "aleneflo@alu.edu.gva.es",
                "Española"
        );
        System.out.println("All clients: ");
        manager.showAllClients();
        manager.createClient(fake);
        System.out.println("Clients after creating another one: ");
        manager.showAllClients();
        fake.setNombre("Juan");
        manager.modifyClient(fake);
        System.out.println("Clients after modifying one: ");
        manager.showAllClients();

        Account fakeAccount = new Account("ES1234567891234567891234", 1000, 11);
        Account fakeAccount2 = new Account("ES9999999999999999999999", 1000, 11);
        System.out.println("All accounts: ");
        manager.showAllAccounts();
        manager.createAccount(fakeAccount);
        System.out.println("Accounts after creating another one: ");
        manager.showAllAccounts();
        manager.makeTransaction(11, 12, 10000);
        fakeAccount.setBalance(1000000);
        manager.updateAccount(fakeAccount);
        System.out.println("Accounts after modifying one: ");
        manager.showAllAccounts();
        manager.deleteAccount(fakeAccount);

        manager.deleteClient(fake);
        System.out.println("Clients after deleting one: ");
        manager.showAllClients();
        System.out.println("Accounts after deleting the owner: ");
        manager.showAllAccounts();
    }

}
