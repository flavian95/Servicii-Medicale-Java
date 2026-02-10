import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JButton userOption;
    private JButton medicOption;
    private JLabel description;

    private JLabel loginLabel;
    private JButton LoginBtn;
    private JLabel optionLoginBtn;
    private JButton CreateUserBtn;

    private JButton lastBtnClicked = null;

    public Main() {
        super("eServicii Medicale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        description = new JLabel("Alegeti daca sunteti pacient sau daca sunteti medic:");
        userOption = new JButton("Pacient");
        medicOption = new JButton("Medic");

        loginLabel = new JLabel("Logare utilizator: ");
        LoginBtn = new JButton("Logare: ");
        CreateUserBtn = new JButton("Creeaza Utilizator");
        optionLoginBtn = new JLabel("Daca nu aveti cont, creati un cont: ");

        JPanel topPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        topPanel.add(description);
        topPanel.add(userOption);
        topPanel.add(medicOption);

        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        mainPanel.add(loginLabel);
        mainPanel.add(LoginBtn);
        mainPanel.add(optionLoginBtn);
        mainPanel.add(CreateUserBtn);
        mainPanel.setVisible(false);
        
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel wrappedPanel = new JPanel();
        wrappedPanel.setLayout(new BoxLayout(wrappedPanel, BoxLayout.Y_AXIS));
        wrappedPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        wrappedPanel.add(topPanel);
        wrappedPanel.add(Box.createVerticalStrut(15));
        wrappedPanel.add(mainPanel);

        add(wrappedPanel);

        userOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(true);
                loginLabel.setText("Logare utilizator: ");
                lastBtnClicked = userOption;
                CreateUserBtn.setVisible(true);
                optionLoginBtn.setVisible(true);
                pack();
            }
        });

        medicOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(true);
                loginLabel.setText("Logare medic: ");
                lastBtnClicked = medicOption;
                CreateUserBtn.setVisible(false);
                optionLoginBtn.setVisible(false);
                pack();
            }
        });

        CreateUserBtn.addActionListener(new MainActionListener());
        LoginBtn.addActionListener(new MainActionListener());
    }

    public class MainActionListener implements ActionListener {
        private JFrame newWindow;

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == CreateUserBtn && lastBtnClicked== userOption) {
                newWindow = new CreateUserWindow();
                newWindow.pack();
                newWindow.setVisible(true);
                newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }

            if (e.getSource() == LoginBtn && lastBtnClicked== userOption) {
                newWindow = new LoginUserWindow();
                newWindow.pack();
                newWindow.setVisible(true);
                newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }

            if (e.getSource() == LoginBtn && lastBtnClicked== medicOption) {
                newWindow = new LoginMedicWindow();
                newWindow.pack();
                newWindow.setVisible(true);
                newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        }
    }

    public static void main(String[] args) {
        var mainWindow = new Main();
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}