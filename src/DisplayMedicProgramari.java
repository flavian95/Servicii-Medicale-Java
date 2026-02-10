
import java.util.Date;

public class DisplayMedicProgramari {
    private String cnpUser;
    private String cnpMedic;
    private String numePacient;
    private String prenumePacient;
    private Date data_nastere;
    private String telefon;
    private String email;
    private Date data_programare;
    private String descriere_afectiune;
    private String status;

    public DisplayMedicProgramari(String cnpUser, String cnpMedic, String numePacient,
                                  String prenumePacient, Date data_nastere,
                                  String telefon, String email, Date data_programare,
                                  String descriere_afectiune, String status) {
        this.cnpUser = cnpUser;
        this.cnpMedic = cnpMedic;
        this.numePacient = numePacient;
        this.prenumePacient = prenumePacient;
        this.data_nastere = data_nastere;
        this.telefon = telefon;
        this.email= email;
        this.data_programare = data_programare;
        this.descriere_afectiune = descriere_afectiune;
        this.status = status;
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

    public String getNumePacient() {
        return numePacient;
    }

    public void setNumePacient(String numePacient) {
        this.numePacient = numePacient;
    }

    public String getPrenume() {
        return prenumePacient;
    }

    public void setPrenume(String prenume) {
        this.prenumePacient = prenume;
    }

    public Date getData_nastere() {
        return data_nastere;
    }

    public void setData_nastere(Date data_nastere) {
        this.data_nastere = data_nastere;
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

    public Date getData_programare() {
        return data_programare;
    }

    public void setData_programare(Date data_programare) {
        this.data_programare = data_programare;
    }

    public String getDescriere_afectiune() {
        return descriere_afectiune;
    }

    public void setDescriere_afectiune(String descriere_afectiune) {
        this.descriere_afectiune = descriere_afectiune;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pacientul: " + numePacient + " " + prenumePacient + "   ,Data nastere:   " + data_nastere +
                "  ,Programat pe data:   " + data_programare + "   ,Email:   " +  email + "   ,Telefon:   " + telefon +
                "    ,Afectiune:   " + descriere_afectiune + "    ,Status programare:   " + status;
    }
}