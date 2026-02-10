
public class Users {
    private String nume;
    private String prenume;
    private String data_nastere;
    private String CNP;
    private String adresa;
    private String telefon;
    private String email;
    private String parola;
    private String role;

    public Users(String nume, String prenume, String data_nastere,
                 String CNP, String adresa, String telefon,
                 String email, String parola, String role) {
        this.nume = nume;
        this.prenume = prenume;
        this.data_nastere = data_nastere;
        this.CNP = CNP;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
        this.parola = parola;
        this.role= role;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getData_nastere() {
        return data_nastere;
    }

    public String getCNP() {
        return CNP;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public String getParola() {
        return parola;
    }

    public String getRole() {
        return role;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setData_nastere(String data_nastere) {
        this.data_nastere = data_nastere;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", data_nastere='" + data_nastere + '\'' +
                ", CNP='" + CNP + '\'' +
                ", adresa='" + adresa + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}