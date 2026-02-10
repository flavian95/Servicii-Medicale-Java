
public class MedicDisplay {
    private Medic medic;
    private String clinicName;
    private String clinicAddress;

    public MedicDisplay(Medic medic, String clinicName, String clinicAddress) {
        this.medic = medic;
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
    }

    public Medic getMedic() {
        return medic;
    }

    @Override
    public String toString() {
        return medic.getNume() + " " + medic.getPrenume() + " " +
                medic.getSpecializare() + " " +
                medic.getOra_start() + "-" + medic.getOra_end() + " Zile lucratoare: " + medic.getZileLucru() +
                " - " + clinicName + " (" + clinicAddress + ")";
    }
}
