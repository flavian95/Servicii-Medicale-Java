
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginMedicWindow extends JFrame {

    private JTextField cnpField;
    private JPasswordField parolaField;
    private JButton loginButton, cancelButton;

    public LoginMedicWindow() {
        super("Login Medic");

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.add(new JLabel("CNP:"));
        formPanel.add(cnpField = new JTextField(15));

        formPanel.add(new JLabel("Parola:"));
        formPanel.add(parolaField = new JPasswordField(15));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton = new JButton("Login"));
        buttonPanel.add(cancelButton = new JButton("Cancel"));

        JPanel wrappedPanel = new JPanel(new BorderLayout(10, 10));
        wrappedPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // top, left, bottom, right

        wrappedPanel.add(formPanel, BorderLayout.CENTER);
        wrappedPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(wrappedPanel);

        loginButton.addActionListener(new LoginActionListener());
        cancelButton.addActionListener(e -> dispose());

        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private class LoginActionListener implements ActionListener {
        private JFrame newWindow;

        public void actionPerformed(ActionEvent e) {
            String cnp = cnpField.getText();
            String parola = new String(parolaField.getPassword());

            boolean authenticated = RepositoryManager.getInstance().loginMedic(cnp, parola);

            if (authenticated) {
                Medic medic = RepositoryManager.getInstance().getMedicByCredentials(cnp, parola);

                if (medic != null) {
                    JOptionPane.showMessageDialog(LoginMedicWindow.this, "Autentificare reușită!");

                    for (Window window : Window.getWindows()) {
                        if (window instanceof JFrame) {
                            window.dispose();
                        }
                    }

                    newWindow = new MedicPanel(medic);
                    newWindow.pack();
                    newWindow.setVisible(true);
                    newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                } else {
                    JOptionPane.showMessageDialog(LoginMedicWindow.this, "Eroare: nu s-a putut obține obiectul Medic.");
                }
            } else {
                JOptionPane.showMessageDialog(LoginMedicWindow.this, "CNP sau parolă incorecte.");
            }
        }
    }
}