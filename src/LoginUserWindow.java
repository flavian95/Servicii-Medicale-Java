import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginUserWindow extends JFrame {

    private JTextField cnpField;
    private JLabel cnpLabel, parolaLabel;
    private JPasswordField parolaField;
    private JButton loginButton, cancelButton;

    public LoginUserWindow() {
        super("Login Utilizator");

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        cnpLabel = new JLabel("CNP:");
        cnpField = new JTextField(15);

        parolaLabel = new JLabel("Parola:");
        parolaField = new JPasswordField(15);

        formPanel.add(cnpLabel);
        formPanel.add(cnpField);
        formPanel.add(parolaLabel);
        formPanel.add(parolaField);

        JPanel buttonPanel = new JPanel();
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");

        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        JPanel wrappedPanel = new JPanel(new BorderLayout(10, 10));
        wrappedPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        wrappedPanel.add(formPanel, BorderLayout.CENTER);
        wrappedPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(wrappedPanel);

        loginButton.addActionListener(new LoginUserWindow.LoginActionListener());
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

            String role = RepositoryManager.getInstance().loginUser(cnp, parola);
            boolean authenticated = false;

            Users currentUser = RepositoryManager.getInstance().getUserByCredentials(cnp, parola);
            if (currentUser != null) {
                switch (role) {
                    case "USER":
                        newWindow = new UserPanel(currentUser);
                        authenticated = true;
                        break;
                    case "ADMIN":
                        newWindow = new AdminPanel(currentUser);
                        authenticated = true;
                        break;
                }
            }

            if (authenticated) {
                JOptionPane.showMessageDialog(LoginUserWindow.this, "Autentificare reușită!");

                for (Window window : Window.getWindows()) {
                    if (window instanceof JFrame) {
                        window.dispose();
                    }
                }

                newWindow.pack();
                newWindow.setVisible(true);
                newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            } else {
                JOptionPane.showMessageDialog(LoginUserWindow.this, "CNP sau parolă incorecte sau nu aveți un rol valid.");
            }
        }
    }
}