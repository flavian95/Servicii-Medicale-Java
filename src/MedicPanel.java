
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicPanel extends JFrame {
    private JLabel numeLabel, prenumeLabel, dataNastereLabel, CNPLabel, adresaLabel, telefonLabel, emailLabel,
    specializareLabel, zileLucruLabel, oraStartLabel, oraEndLabel, clinicaNumeLabel, clinicaAdresaLabel;
    private Medic medic;
    private JButton viewProgramariBtn;

    public MedicPanel(Medic medic) {
        super("Medic");
        this.medic = medic;

        viewProgramariBtn = new JButton("Vizualizati programarile dvs:");
        numeLabel = new JLabel("Nume: " + medic.getNume());
        prenumeLabel = new JLabel("Prenume: " + medic.getPrenume());
        dataNastereLabel = new JLabel("Data Nastere: " + medic.getData_nastere());
        CNPLabel = new JLabel("CNP: " + medic.getCNP());
        adresaLabel = new JLabel("Adresa: " + medic.getAdresa());
        telefonLabel = new JLabel("Telefon: " + medic.getTelefon());
        emailLabel = new JLabel("Email: " + medic.getEmail());
        specializareLabel = new JLabel("Specializare: " + medic.getSpecializare());
        zileLucruLabel = new JLabel("Zile lucru: " + medic.getZileLucru());
        oraStartLabel = new JLabel("Programul de la ora: " + medic.getOra_start());
        oraEndLabel = new JLabel("Pana la ora: " + medic.getOra_end());

        RepositoryManager repo = RepositoryManager.getInstance();
        Clinica clinica = repo.loadClinicaForMedic(medic.getCNP());

        clinicaNumeLabel = new JLabel("Clinica: " + clinica.getNume());
        clinicaAdresaLabel = new JLabel("Adresa clinica: " + clinica.getAdresa());

        setLayout(new GridLayout(0, 1, 5, 5));

        add(viewProgramariBtn);
        add(numeLabel);
        add(prenumeLabel);
        add(dataNastereLabel);
        add(CNPLabel);
        add(adresaLabel);
        add(telefonLabel);
        add(emailLabel);
        add(specializareLabel);
        add(zileLucruLabel);
        add(oraStartLabel);
        add(oraEndLabel);
        add(clinicaNumeLabel);
        add(clinicaAdresaLabel);

        viewProgramariBtn.addActionListener(new MedicPanel.MedicActionListener());
    }

    public class MedicActionListener implements ActionListener {
        private JFrame newWindow;

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewProgramariBtn) {
                newWindow = new ViewMedicProgramari(medic);
                newWindow.pack();
                newWindow.setVisible(true);
                newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        }
    }
}