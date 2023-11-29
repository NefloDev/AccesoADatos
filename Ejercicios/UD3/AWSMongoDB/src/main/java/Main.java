
public class Main {

    public static void main(String[] args) {
        CRUDOperationsPilot.showPilotsOlderThan(30);

        CRUDOperationsPilot.stopConnection();

        //Crear un id autogenerado personalizado desde la base de datos de MongoDB puede ayudar a la gestión de elementos
        //pero también puede ser un problema de eficiencia si se tiene una base de datos muy grande

        //Si realmente seguir usando el id autogenerado de MongoDB no supone ningun inconveniente al gestionar la Base de datos
        //puede dejarse como está para mejorar el rendimiento
    }

}
