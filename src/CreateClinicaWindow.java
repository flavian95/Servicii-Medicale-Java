
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateClinicaWindow extends JFrame {
    private JButton okButton, cancelButton;
    private JLabel numeLabel, adresaLabel;
    private JTextField numeField;
    private JTextField adresaField;

    public CreateClinicaWindow() {
        super("Fereastra Creare Clinica");

        JPanel formPanel = new JPanel(new GridLayout(12, 2, 10, 10));

        numeLabel =new JLabel("Nume:");
        numeField = new JTextField(15);

        adresaLabel = new JLabel("Adresa:");
        adresaField = new JTextField(15);

        formPanel.add(numeLabel);
        formPanel.add(numeField);
        formPanel.add(adresaLabel);
        formPanel.add(adresaField);

        JPanel buttonPanel = new JPanel();
        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        JPanel wrappedPanel = new JPanel(new BorderLayout(10, 10));
        wrappedPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        wrappedPanel.add(formPanel, BorderLayout.CENTER);
        wrappedPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(wrappedPanel);

        okButton.addActionListener(new CreateClinicaWindow.AccountWindowActionListener());
        cancelButton.addActionListener(new CreateClinicaWindow.AccountWindowActionListener());

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public class AccountWindowActionListener implements ActionListener {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == okButton) {

                String nume = numeField.getText();
                String adresa = adresaField.getText();

                if(nume.length() < 5){
                    JOptionPane.showMessageDialog(CreateClinicaWindow.this,
                            "Numele clinicii introduse trebuie sa aiba cel putin 5 caractere.",
                            "Nume invalid",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(adresa.length() < 5){
                    JOptionPane.showMessageDialog(CreateClinicaWindow.this,
                            "Adresa introdusa trebuie sa aiba cel putin 5 caractere.",
                            "Adresa invalida",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ClinicaAdd newClinica = new ClinicaAdd(
                        nume,
                        adresa
                );

                boolean success = repositoryManager.CreateClinica(newClinica);

                if (success) {
                    JOptionPane.showMessageDialog(CreateClinicaWindow.this, "Clinica a fost creata cu success!");
                } else {
                    JOptionPane.showMessageDialog(CreateClinicaWindow.this, "Eroare, clinica nu a fost creata");
                }
            } else {
                CreateClinicaWindow.this.dispose();
            }
        }
    }
}