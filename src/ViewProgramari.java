
import javax.swing.*;

public class ViewProgramari extends JFrame {
    private JList<DisplayProgramari> programariList;

    public ViewProgramari(Users user) {
        super("Programările mele");

        DefaultListModel<DisplayProgramari> model = RepositoryManager.getInstance()
                .loadProgramariForUser(user.getCNP());

        programariList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(programariList);

        add(scrollPane);
        setSize(400, 300);
    }
}