
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateMedicWindow extends JFrame {
    private JButton okButton;
    private JButton cancelButton;
    private JTextField numeField;
    private JTextField prenumeField;
    private JTextField dataNastereField;
    private JTextField cnpField;
    private JTextField adresaField;
    private JTextField telefonField;
    private JTextField emailField;
    private JPasswordField parolaField;
    private JComboBox<OraStart> oraStartComboBox;
    private JComboBox<OraEnd> oraEndComboBox;
    private JComboBox<Specializare> specializareComboBox;
    private JComboBox<Clinica> clinicaComboBox;

    private JCheckBox luniCheckBox, martiCheckBox, miercuriCheckBox, joiCheckBox,
            vineriCheckBox, sambataCheckBox, duminicaCheckBox;

    public CreateMedicWindow() {
        super("Fereastra Creare Cont Medic");

        JPanel formPanel = new JPanel(new GridLayout(13, 2, 10, 10));

        formPanel.add(new JLabel("Nume:"));
        formPanel.add(numeField = new JTextField(15));

        formPanel.add(new JLabel("Prenume:"));
        formPanel.add(prenumeField = new JTextField(15));

        formPanel.add(new JLabel("Data Nastere (YYYY-MM-DD):"));
        formPanel.add(dataNastereField = new JTextField(15));

        formPanel.add(new JLabel("CNP:"));
        formPanel.add(cnpField = new JTextField(15));

        formPanel.add(new JLabel("Adresa:"));
        formPanel.add(adresaField = new JTextField(15));

        formPanel.add(new JLabel("Telefon:"));
        formPanel.add(telefonField = new JTextField(15));

        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField = new JTextField(15));

        specializareComboBox = new JComboBox<>(Specializare.values());
        formPanel.add(new JLabel("Specializare: "));
        formPanel.add(specializareComboBox);

        formPanel.add(new JLabel("Zile Lucru:"));
        JPanel zilePanel = new JPanel(new GridLayout(1, 7));
        luniCheckBox = new JCheckBox("L");
        martiCheckBox = new JCheckBox("Ma");
        miercuriCheckBox = new JCheckBox("Mi");
        joiCheckBox = new JCheckBox("J");
        vineriCheckBox = new JCheckBox("V");
        sambataCheckBox = new JCheckBox("S");
        duminicaCheckBox = new JCheckBox("D");

        zilePanel.add(luniCheckBox);
        zilePanel.add(martiCheckBox);
        zilePanel.add(miercuriCheckBox);
        zilePanel.add(joiCheckBox);
        zilePanel.add(vineriCheckBox);
        zilePanel.add(sambataCheckBox);
        zilePanel.add(duminicaCheckBox);
        formPanel.add(zilePanel);

        oraStartComboBox = new JComboBox<>(OraStart.values());
        formPanel.add(new JLabel("Ora Start:"));
        formPanel.add(oraStartComboBox);

        oraEndComboBox = new JComboBox<>(OraEnd.values());
        formPanel.add(new JLabel("Ora End:"));
        formPanel.add(oraEndComboBox);

        formPanel.add(new JLabel("Parola: "));
        formPanel.add(parolaField = new JPasswordField(15));

        clinicaComboBox = new JComboBox<>();
        RepositoryManager.getInstance().loadCliniciIntoComboBox(clinicaComboBox);
        formPanel.add(new JLabel("Clinica: "));
        formPanel.add(clinicaComboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton = new JButton("Ok"));
        buttonPanel.add(cancelButton = new JButton("Cancel"));

        JPanel wrappedPanel = new JPanel(new BorderLayout(10, 10));
        wrappedPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // top, left, bottom, right

        wrappedPanel.add(formPanel, BorderLayout.CENTER);
        wrappedPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(wrappedPanel);

        AccountWindowActionListener actionListener = new AccountWindowActionListener();
        okButton.addActionListener(actionListener);
        cancelButton.addActionListener(actionListener);

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
                String prenume = prenumeField.getText();
                String dataNastereText = dataNastereField.getText();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                String cnp = cnpField.getText();
                String adresa = adresaField.getText();
                String telefon = telefonField.getText();
                String email = emailField.getText();
                String password = new String(parolaField.getPassword());


                if (nume.length() < 2) {
                    JOptionPane.showMessageDialog(CreateMedicWindow.this,
                            "Numele introdus trebuie sa aiba cel putin 2 caractere.",
                            "Nume invalid",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (prenume.length() < 2) {
                    JOptionPane.showMessageDialog(CreateMedicWindow.this,
                            "Prenumele introdus trebuie sa aiba cel putin 2 caractere.",
                            "Prenume invalid",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    java.util.Date dataNastere = dateFormat.parse(dataNastereText);
                    java.util.Date currentDate = new java.util.Date();

                    if (!dataNastere.before(currentDate)) {
                        JOptionPane.showMessageDialog(CreateMedicWindow.this,
                                "Data nașterii trebuie să fie în trecut.",
                                "Dată invalidă",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dataNastere);
                    int year = cal.get(Calendar.YEAR);

                    if (year < 1950) {
                        JOptionPane.showMessageDialog(CreateMedicWindow.this,
                                "Anul nașterii trebuie să fie 1950 sau mai recent.",
                                "Dată invalidă",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(CreateMedicWindow.this,
                            "Formatul datei de naștere este invalid. Folosește formatul yyyy-MM-dd.",
                            "Dată invalidă",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!cnp.matches("\\d{13}")) {
                    JOptionPane.showMessageDialog(CreateMedicWindow.this,
                            "CNP-ul trebuie să conțină exact 13 cifre.",
                            "CNP invalid",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (adresa.length() < 5) {
                    JOptionPane.showMessageDialog(CreateMedicWindow.this,
                            "Adresa introdusă trebuie să aibă cel puțin 5 caractere.",
                            "Adresă invalidă",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!telefon.matches("^(07\\d{8}|02\\d{8}|03\\d{8})$")) {
                    JOptionPane.showMessageDialog(CreateMedicWindow.this,
                            "Numărul de telefon trebuie să conțină 10 cifre și să înceapă cu 07, 02 sau 03.",
                            "Telefon invalid",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (email.indexOf("@") == -1 || email.indexOf("@") != email.lastIndexOf("@")) {
                    JOptionPane.showMessageDialog(CreateMedicWindow.this,
                            "Emailul introdus nu este valid. Trebuie să conțină un singur caracter '@'.",
                            "Email invalid",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String template = "((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@&#$%]).{8,32})";
                Pattern pattern = Pattern.compile(template);
                Matcher matcher = pattern.matcher(password);

                if (!matcher.matches()) {
                    JOptionPane.showMessageDialog(CreateMedicWindow.this,
                            "Parola trebuie să conțină între 8 și 32 caractere și să includă:\n" +
                                    "- cel puțin o literă mică\n" +
                                    "- cel puțin o literă mare\n" +
                                    "- cel puțin o cifră\n" +
                                    "- cel puțin un caracter special (@, &, #, $, %)",
                            "Parolă invalidă",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }


                Specializare selectedSpecializare = (Specializare) specializareComboBox.getSelectedItem();
                String selectedSpecializareName = selectedSpecializare.name();

                OraStart selectedOraStart = (OraStart) oraStartComboBox.getSelectedItem();
                String selectedOraStartName = selectedOraStart.getLabel();

                OraEnd selectedOraEnd = (OraEnd) oraEndComboBox.getSelectedItem();
                String selectedOraEndName = selectedOraEnd.getLabel();

                Clinica selectedClinica = (Clinica) clinicaComboBox.getSelectedItem();
                String selectedClinicaId = String.valueOf(selectedClinica.getIdClinica());

                StringBuilder selectedZileLucru = new StringBuilder();
                if (luniCheckBox.isSelected()) selectedZileLucru.append("L,");
                if (martiCheckBox.isSelected()) selectedZileLucru.append("Ma,");
                if (miercuriCheckBox.isSelected()) selectedZileLucru.append("Mi,");
                if (joiCheckBox.isSelected()) selectedZileLucru.append("J,");
                if (vineriCheckBox.isSelected()) selectedZileLucru.append("V,");
                if (sambataCheckBox.isSelected()) selectedZileLucru.append("S,");
                if (duminicaCheckBox.isSelected()) selectedZileLucru.append("D,");

                if (selectedZileLucru.length() > 0) {
                    selectedZileLucru.deleteCharAt(selectedZileLucru.length() - 1);
                }

                Medic newMedic = new Medic(
                        nume,
                        prenume,
                        dataNastereText,
                        cnp,
                        adresa,
                        telefon,
                        email,
                        selectedSpecializareName,
                        selectedZileLucru.toString(),
                        selectedOraStartName,
                        selectedOraEndName,
                        password,
                        selectedClinicaId
                );

                boolean success = repositoryManager.CreateMedic(newMedic);

                if (success) {
                    JOptionPane.showMessageDialog(CreateMedicWindow.this, "Contul de medic a fost creat cu succes!");
                } else {
                    JOptionPane.showMessageDialog(CreateMedicWindow.this, "Eroare, contul de medic u a fost creat");
                }
            } else {
                CreateMedicWindow.this.dispose();
            }
        }
    }
}