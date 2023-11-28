
public class Main {

    public static void main(String[] args) {
        CRUDOperationsPilot crudOperationsPilot = new CRUDOperationsPilot();
        crudOperationsPilot.showPilotsOlderThan(30);

        crudOperationsPilot.stopConnection();
    }

}
