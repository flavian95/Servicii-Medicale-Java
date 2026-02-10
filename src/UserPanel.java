import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends JFrame {

    private JButton programariBtn, vizualizeazaProgramariBtn, modificaProgramareBtn;
    private JLabel nameLabel, surnameLabel, birthdateLabel, cnpLabel, addressLabel, phoneLabel, emailLabel;
    private Users user;

    public UserPanel(Users user) {
        super("eServicii Medicale");
        this.user=user;

        nameLabel = new JLabel("Nume: " + user.getNume());
        surnameLabel = new JLabel("Prenume: " + user.getPrenume());
        birthdateLabel = new JLabel("Data nașterii: " + user.getData_nastere());
        cnpLabel = new JLabel("CNP: " + user.getCNP());
        addressLabel = new JLabel("Adresa: " + user.getAdresa());
        phoneLabel = new JLabel("Telefon: " + user.getTelefon());
        emailLabel = new JLabel("Email: " + user.getEmail());

        programariBtn = new JButton("Programati consultatie");
        vizualizeazaProgramariBtn = new JButton("Vizualizati programarile dvs");
        modificaProgramareBtn = new JButton("Modificati o programare");

        setLayout(new GridLayout(0, 1, 5, 5));

        add(programariBtn);
        add(vizualizeazaProgramariBtn);
        add(modificaProgramareBtn);
        add(nameLabel);
        add(surnameLabel);
        add(birthdateLabel);
        add(cnpLabel);
        add(addressLabel);
        add(phoneLabel);
        add(emailLabel);

        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        programariBtn.addActionListener(new ProgramariActionListener());
        vizualizeazaProgramariBtn.addActionListener(new ProgramariActionListener());
        modificaProgramareBtn.addActionListener(new ProgramariActionListener());
    }

    public class ProgramariActionListener implements ActionListener {
        private JFrame newWindow;

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == programariBtn ) {
                newWindow = new ProgramariWindow(user);
                newWindow.pack();
                newWindow.setVisible(true);
                newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }

            if (e.getSource() == vizualizeazaProgramariBtn ) {
                newWindow = new ViewProgramari(user);
                newWindow.pack();
                newWindow.setVisible(true);
                newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }

            if (e.getSource() == modificaProgramareBtn ) {
                newWindow = new ModificaProgramare(user);
                newWindow.pack();
                newWindow.setVisible(true);
                newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        }
    }
}