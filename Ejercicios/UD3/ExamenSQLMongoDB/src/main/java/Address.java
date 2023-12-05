public class Address {

    private String calle;
    private int cp;
    private String municipio;
    private String provincia;

    public Address() {
    }

    public Address(String calle, int cp, String municipio, String provincia) {
        this.calle = calle;
        this.cp = cp;
        this.municipio = municipio;
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "\n\t\tCalle: " + calle +
                "\n\t\tCp: " + cp +
                "\n\t\tMunicipio: " + municipio +
                "\n\t\tProvincia: " + provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}

