
import java.util.Date;

public class Programari {

    private String cnpUser;
    private String cnpMedic;
    private Date dataProgramare;
    private String descriereAfectiune;
    private String status;

    public Programari(String cnpUser, String cnpMedic, Date dataProgramare, String descriereAfectiune, String status) {
        this.cnpUser = cnpUser;
        this.cnpMedic = cnpMedic;
        this.dataProgramare = dataProgramare;
        this.descriereAfectiune = descriereAfectiune;
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

    // toString Method
    @Override
    public String toString() {
        return "Programari:" +
                " cnpMedic=" + cnpMedic +
                ", dataProgramare=" + dataProgramare +
                ", descriereAfectiune='" + descriereAfectiune + '\'' +
                ", status='" + status + '\'';
    }
}
