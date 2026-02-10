
import javax.swing.*;
import java.awt.*;

public class ModificaProgramare extends JFrame {
    private JComboBox<DisplayProgramari> programariComboBox;
    private JLabel modificaLabel;
    private JButton editDescriereBtn, anulareProgramareBtn;

    public ModificaProgramare(Users user) {
        super("Modifică o programare");

        modificaLabel = new JLabel("Selectează programarea de modificat:");
        programariComboBox = new JComboBox<>();
        RepositoryManager.getInstance().loadProgramariModifica(user.getCNP(), programariComboBox);

        editDescriereBtn = new JButton("Modifică descrierea afectiunii");
        editDescriereBtn.addActionListener(e -> {
            DisplayProgramari selected = (DisplayProgramari) programariComboBox.getSelectedItem();
            if (selected != null) {
                new ModificaDescriereFereastra(selected);
            }
        });

        anulareProgramareBtn = new JButton("Anuleaza programarea");
        anulareProgramareBtn.addActionListener(e -> {
            DisplayProgramari selected = (DisplayProgramari) programariComboBox.getSelectedItem();

            if (selected != null) {
                RepositoryManager.getInstance().anuleazaProgramare(
                        selected.getCnpUser(),
                        selected.getCnpMedic(),
                        new java.sql.Date(selected.getDataProgramare().getTime()),
                        "anulata"
                );
                JOptionPane.showMessageDialog(this, "Programare anulată.");
                this.dispose();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(modificaLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(programariComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(editDescriereBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(anulareProgramareBtn, gbc);

        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}