
public class Medic {
    private String nume;
    private String prenume;
    private String data_nastere;
    private String CNP;
    private String adresa;
    private String telefon;
    private String email;
    private String specializare;
    private String zileLucru;
    private String ora_start;
    private String ora_end;
    private String parola;
    private String clinica_id;

    public Medic(String nume, String prenume, String data_nastere,
                 String CNP, String adresa, String telefon, String email,
                 String specializare, String zileLucru, String ora_start,
                 String ora_end, String parola, String clinica_id) {
        this.nume = nume;
        this.prenume = prenume;
        this.data_nastere = data_nastere;
        this.CNP = CNP;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
        this.specializare = specializare;
        this.zileLucru = zileLucru;
        this.ora_start = ora_start;
        this.ora_end = ora_end;
        this.parola = parola;
        this.clinica_id = clinica_id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getData_nastere() {
        return data_nastere;
    }

    public void setData_nastere(String data_nastere) {
        this.data_nastere = data_nastere;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public String getZileLucru() {
        return zileLucru;
    }

    public void setZileLucru(String zileLucru) {
        this.zileLucru = zileLucru;
    }

    public String getOra_start() {
        return ora_start;
    }

    public void setOra_start(String ora_start) {
        this.ora_start = ora_start;
    }

    public String getOra_end() {
        return ora_end;
    }

    public void setOra_end(String ora_end) {
        this.ora_end = ora_end;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getClinicaId() {
        return clinica_id;
    }

    public void setClinicaId(String clinica_id) {
        this.clinica_id = clinica_id;
    }

    @Override
    public String toString() {
        return nume + " " + prenume + " " + specializare + " " + "Program: " + zileLucru + " " + ora_start + "-" + ora_end + " Id clinica: " + clinica_id;
    }
}