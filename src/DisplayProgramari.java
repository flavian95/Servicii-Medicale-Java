import java.util.Date;

public class DisplayProgramari {
    private String cnpUser;
    private String cnpMedic;
    private Date dataProgramare;
    private String descriereAfectiune;
    private String status;
    private String numeMedic;
    private String prenumeMedic;
    private String specializareMedic;
    private String adresaEmail;
    private String numeClinica;
    private String adresaClinica;

    public DisplayProgramari(String cnpUser, String cnpMedic, Date dataProgramare, String descriereAfectiune, String status,
                      String numeMedic, String prenumeMedic, String specializareMedic, String adresaEmail, String numeClinica, String adresaClinica) {
        this.cnpUser = cnpUser;
        this.cnpMedic = cnpMedic;
        this.dataProgramare = dataProgramare;
        this.descriereAfectiune = descriereAfectiune;
        this.status = status;
        this.numeMedic = numeMedic;
        this.prenumeMedic = prenumeMedic;
        this.specializareMedic = specializareMedic;
        this.adresaEmail = adresaEmail;
        this.numeClinica = numeClinica;
        this.adresaClinica = adresaClinica;
    }

    public String getCnpUser() {
        return cnpUser;
    }

    public void setCnpUser(String cnpUser) {
        this.cnpUser = cnpUser;
    }

    public String getCnpMedic() {
        return cnpMedic;
    }

    public void setCnpMedic(String cnpMedic) {
        this.cnpMedic = cnpMedic;
    }

    public Date getDataProgramare() {
        return dataProgramare;
    }

    public void setDataProgramare(Date dataProgramare) {
        this.dataProgramare = dataProgramare;
    }

    public String getDescriereAfectiune() {
        return descriereAfectiune;
    }

    public void setDescriereAfectiune(String descriereAfectiune) {
        this.descriereAfectiune = descriereAfectiune;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumeMedic() {
        return numeMedic;
    }

    public void setNumeMedic(String numeMedic) {
        this.numeMedic = numeMedic;
    }

    public String getPrenumeMedic() {
        return prenumeMedic;
    }

    public void setPrenumeMedic(String prenumeMedic) {
        this.prenumeMedic = prenumeMedic;
    }

    public String getSpecializareMedic() {
        return specializareMedic;
    }

    public void setSpecializareMedic(String specializareMedic) {
        this.specializareMedic = specializareMedic;
    }

    public String getAdresaEmail() {
        return adresaEmail;
    }

    public void setAdresaEmail(String adresaEmail) {
        this.adresaEmail = adresaEmail;
    }

    public String getNumeClinica() {
        return numeClinica;
    }

    public void setNumeClinica(String numeClinica) {
        this.numeClinica = numeClinica;
    }

    public String getAdresaClinica() {
        return adresaClinica;
    }

    public void setAdresaClinica(String adresaClinica) {
        this.adresaClinica = adresaClinica;
    }

    @Override
    public String toString() {
        return "Programare cu Dr. " + numeMedic + " " + prenumeMedic +
                "    (" + specializareMedic + ")   ,  la data:   " + dataProgramare + "   email medic:   " +  adresaEmail +
        "  Clinica:  " + numeClinica + " " + adresaClinica + "    afectiune:   " + descriereAfectiune + "    status:   " + status;
    }
}