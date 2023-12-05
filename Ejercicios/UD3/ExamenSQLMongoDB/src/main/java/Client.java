import java.time.LocalDate;

public class Client {
    private int clientid;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String usuario;
    private String contrasenya;
    private Address direccion;
    private String dni;
    private String email;
    private String nacionalidad;

    public Client() {
    }

    public Client(String nombre, String apellidos, String telefono, String usuario, String contrasenya, Address direccion, String dni, String email, String nacionalidad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contrasenya = contrasenya;
        this.direccion = direccion;
        this.dni = dni;
        this.email = email;
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos +
                "\n\tId: " + clientid +
                "\n\tNacionalidad: " + nacionalidad +
                "\n\tTel: " + telefono +
                "\n\tUsuario: " + usuario +
                "\n\tDni: " + dni +
                "\n\tEmail: " + email +
                "\n\tDireccion: " + direccion.toString() + "\n";
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Address getDireccion() {
        return direccion;
    }

    public void setDireccion(Address direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
