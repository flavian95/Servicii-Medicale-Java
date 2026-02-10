
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminPanel extends JFrame {

    private JButton addMedicBtn, addClinicaBtn;
    private JLabel nameLabel, surnameLabel, birthdateLabel, cnpLabel, addressLabel, phoneLabel, emailLabel;
    private Users user;

    public AdminPanel(Users user) {
        super("Panou Administrator");
        this.user=user;

        addMedicBtn = new JButton("Adaugati Medic");
        addClinicaBtn = new JButton("Adaugati o Clinica");
        nameLabel = new JLabel("Nume: " + user.getNume());
        surnameLabel = new JLabel("Prenume: " + user.getPrenume());
        birthdateLabel = new JLabel("Data nașterii: " + user.getData_nastere());
        cnpLabel = new JLabel("CNP: " + user.getCNP());
        addressLabel = new JLabel("Adresa: " + user.getAdresa());
        phoneLabel = new JLabel("Telefon: " + user.getTelefon());
        emailLabel = new JLabel("Email: " + user.getEmail());

        setLayout(new GridLayout(0, 1, 5, 5));

        add(addMedicBtn);
        add(addClinicaBtn);
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

        addMedicBtn.addActionListener(new AdminPanel.MainActionListener());
        addClinicaBtn.addActionListener(new AdminPanel.MainActionListener());
    }

        public class MainActionListener implements ActionListener {
            private JFrame newWindow;

            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addMedicBtn)  {
                    newWindow = new CreateMedicWindow();
                    newWindow.pack();
                    newWindow.setVisible(true);
                    newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                }

                if (e.getSource() == addClinicaBtn)  {
                    newWindow = new CreateClinicaWindow();
                    newWindow.pack();
                    newWindow.setVisible(true);
                    newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                }
            }
        }
    }