
import javax.swing.*;

public class ViewMedicProgramari extends JFrame {
    private JList<DisplayMedicProgramari> medicProgramariList;

    public ViewMedicProgramari(Medic medic) {
        super("Programările dvs");

        DefaultListModel<DisplayMedicProgramari> model = RepositoryManager.getInstance()
                .loadProgramariForMedic(medic.getCNP());

        medicProgramariList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(medicProgramariList);

        add(scrollPane);
        setSize(400, 300);
    }
}