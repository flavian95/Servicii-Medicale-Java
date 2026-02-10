import javax.swing.*;
import java.awt.*;

public class ModificaDescriereFereastra extends JFrame {

    private JLabel label;
    private JTextArea descriereField;
    private JButton saveButton;

    public ModificaDescriereFereastra(DisplayProgramari programare) {
        super("Modificare Descrierea");

        label = new JLabel("Descriere nouă:");
        descriereField = new JTextArea(programare.getDescriereAfectiune(), 5, 10);
        saveButton = new JButton("Salvează");

        saveButton.addActionListener(e -> {
            String descriereAfectiune = descriereField.getText();

            if (descriereAfectiune.length() < 10) {
                JOptionPane.showMessageDialog(
                        this,
                        "Descriere afectiunii trebuie să aibă cel puțin 10 caractere.",
                        "Descriere afectiune invalidă",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            RepositoryManager.getInstance().updateDescriereProgramare(
                    programare.getCnpUser(),
                    programare.getCnpMedic(),
                    new java.sql.Date(programare.getDataProgramare().getTime()),
                    descriereAfectiune
            );
            JOptionPane.showMessageDialog(this, "Descriere actualizată.");
            this.dispose();
        });

        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);
        add(new JScrollPane(descriereField), BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
