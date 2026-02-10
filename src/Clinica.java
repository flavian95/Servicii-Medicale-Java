
public class Clinica {
    private int id_Clinica;
    private String nume;
    private String adresa;

    public Clinica(int id_Clinica, String nume, String adresa) {
        this.id_Clinica= id_Clinica;
        this.nume = nume;
        this.adresa = adresa;
    }

    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public int getIdClinica() {
        return id_Clinica;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setIdClinica(int id_Clinica) {
        this.id_Clinica = id_Clinica;
    }

    @Override
    public String toString() {
        return nume;
    }
}
