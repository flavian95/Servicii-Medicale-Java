
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProgramariWindow extends JFrame {

    private JButton okButton;
    private JButton cancelButton;
    private JTextField dataProgramareField;
    private JTextField oraProgramareField;
    private JTextField descriereAfectiuneField;
    private JComboBox<MedicDisplay> mediciComboBox;
    private JLabel ziSaptamanaLabel;

    private Users currentUser;

    public ProgramariWindow(Users currentUser) {
        super("Programare");
        this.currentUser = currentUser;

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(new JLabel("Medici:"), gbc);
        gbc.gridx = 1;
        mediciComboBox = new JComboBox<>();
        RepositoryManager.getInstance().loadMediciIntoComboBox(mediciComboBox);
        formPanel.add(mediciComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Data Programare (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        dataProgramareField = new JTextField(15);
        formPanel.add(dataProgramareField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Ziua săptămânii:"), gbc);
        gbc.gridx = 1;
        ziSaptamanaLabel = new JLabel("-");
        formPanel.add(ziSaptamanaLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Ora Programare (HH:MM):"), gbc);
        gbc.gridx = 1;
        oraProgramareField = new JTextField(15);
        formPanel.add(oraProgramareField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Descriere afectiune:"), gbc);
        gbc.gridx = 1;
        descriereAfectiuneField = new JTextField(15);
        formPanel.add(descriereAfectiuneField, gbc);

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

        ProgramariWindowActionListener actionListener = new ProgramariWindowActionListener();
        okButton.addActionListener(actionListener);
        cancelButton.addActionListener(actionListener);

        dataProgramareField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateZiuaSaptamanii(); }
            public void removeUpdate(DocumentEvent e) { updateZiuaSaptamanii(); }
            public void changedUpdate(DocumentEvent e) { updateZiuaSaptamanii(); }
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void updateZiuaSaptamanii() {
        String dateText = dataProgramareField.getText();
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = dateFormat.parse(dateText);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            String ziua = switch (dayOfWeek) {
                case Calendar.MONDAY -> "Luni";
                case Calendar.TUESDAY -> "Marți";
                case Calendar.WEDNESDAY -> "Miercuri";
                case Calendar.THURSDAY -> "Joi";
                case Calendar.FRIDAY -> "Vineri";
                case Calendar.SATURDAY -> "Sâmbătă";
                case Calendar.SUNDAY -> "Duminică";
                default -> "-";
            };

            ziSaptamanaLabel.setText(ziua);
        } catch (ParseException ex) {
            ziSaptamanaLabel.setText("-");
        }
    }

    public class ProgramariWindowActionListener implements ActionListener {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == okButton) {
                MedicDisplay selectedDisplay = (MedicDisplay) mediciComboBox.getSelectedItem();
                Medic selectedMedic = selectedDisplay.getMedic();
                String selectedMedicId = String.valueOf(selectedMedic.getCNP());

                String descriereAfectiune = descriereAfectiuneField.getText();
                String dateText = dataProgramareField.getText();
                String timeText = oraProgramareField.getText();

                DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                java.util.Date utilDate;

                try {
                    utilDate = dateTimeFormat.parse(dateText + " " + timeText);

                    if (!utilDate.after(new java.util.Date())) {
                        JOptionPane.showMessageDialog(ProgramariWindow.this,
                                "Data și ora trebuie să fie în viitor.");
                        return;
                    }

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(utilDate);
                    int minutes = calendar.get(Calendar.MINUTE);
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int fullMinutes = hour * 60 + minutes;

                    if (minutes != 0 && minutes != 30) {
                        JOptionPane.showMessageDialog(ProgramariWindow.this,
                                "Ora trebuie să se termine în :00 sau :30 (ex. 14:00, 14:30)");
                        return;
                    }

                    String oraStart = selectedMedic.getOra_start();
                    String oraEnd = selectedMedic.getOra_end();

                    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    java.util.Date startTime = timeFormat.parse(oraStart);
                    java.util.Date endTime = timeFormat.parse(oraEnd);

                    Calendar startCal = Calendar.getInstance();
                    startCal.setTime(startTime);
                    int startMinutes = startCal.get(Calendar.HOUR_OF_DAY) * 60 + startCal.get(Calendar.MINUTE);

                    Calendar endCal = Calendar.getInstance();
                    endCal.setTime(endTime);
                    int endMinutes = endCal.get(Calendar.HOUR_OF_DAY) * 60 + endCal.get(Calendar.MINUTE);

                    if (fullMinutes < startMinutes || fullMinutes >= endMinutes) {
                        JOptionPane.showMessageDialog(ProgramariWindow.this,
                                "Ora programării trebuie să fie între " + oraStart + " și " + oraEnd + " pentru medicul selectat.");
                        return;
                    }

                    String zileLucru = selectedMedic.getZileLucru();
                    String[] zileArray = zileLucru.split(",");

                    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                    String ziProgramare = getZileSaptamani(dayOfWeek);

                    if (!Arrays.asList(zileArray).contains(ziProgramare)) {
                        JOptionPane.showMessageDialog(ProgramariWindow.this,
                                "Medicul selectat nu lucrează în ziua selectată (" + ziProgramare + ").");
                        return;
                    }

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(ProgramariWindow.this,
                            "Format invalid. Folosește formatul yyyy-MM-dd pentru dată și HH:mm pentru oră.");
                    ex.printStackTrace();
                    return;
                }

                Timestamp timestamp = new Timestamp(utilDate.getTime());

                if (repositoryManager.isSlotTaken(selectedMedicId, timestamp)) {
                    JOptionPane.showMessageDialog(ProgramariWindow.this,
                            "Acest interval orar este deja ocupat pentru medicul selectat.");
                    return;
                }

                if (descriereAfectiune.length() < 10) {
                    JOptionPane.showMessageDialog(ProgramariWindow.this,
                            "Descrierea trebuie să aibă cel puțin 10 caractere.",
                            "Descriere invalidă",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Programari newProgramare = new Programari(
                        currentUser.getCNP(),
                        selectedMedicId,
                        utilDate,
                        descriereAfectiune,
                        "programata"
                );

                boolean success = repositoryManager.CreateProgramare(newProgramare);

                if (success) {
                    JOptionPane.showMessageDialog(ProgramariWindow.this, "Programarea a fost înregistrată cu succes!");
                } else {
                    JOptionPane.showMessageDialog(ProgramariWindow.this, "Programarea nu a fost înregistrată.");
                }
            } else {
                ProgramariWindow.this.dispose();
            }
        }

        private static String getZileSaptamani(int javaDayOfWeek) {
            return switch (javaDayOfWeek) {
                case Calendar.MONDAY -> "L";
                case Calendar.TUESDAY -> "Ma";
                case Calendar.WEDNESDAY -> "Mi";
                case Calendar.THURSDAY -> "J";
                case Calendar.FRIDAY -> "V";
                case Calendar.SATURDAY -> "S";
                case Calendar.SUNDAY -> "D";
                default -> "";
            };
        }
    }
}