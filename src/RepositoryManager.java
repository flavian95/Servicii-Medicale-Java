import javax.swing.*;
import java.sql.*;

public class RepositoryManager {
    private static RepositoryManager instance;
    private Connection connection;

    private RepositoryManager() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        connection = databaseConnection.getConnection();
    }

    public static RepositoryManager getInstance() {
        if (instance == null) {
            instance = new RepositoryManager();
        }
        return instance;
    }

    public boolean CreateUser(Users account) {
        try {
            var preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (nume, prenume, data_nastere, CNP, adresa, telefon, email, parola, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'USER')");
            preparedStatement.setString(1, account.getNume());
            preparedStatement.setString(2, account.getPrenume());
            preparedStatement.setString(3, account.getData_nastere());
            preparedStatement.setString(4, account.getCNP());
            preparedStatement.setString(5, account.getAdresa());
            preparedStatement.setString(6, account.getTelefon());
            preparedStatement.setString(7, account.getEmail());
            preparedStatement.setString(8, account.getParola());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean CreateMedic(Medic account) {
        try {
            var preparedStatement = connection.prepareStatement(
                    "INSERT INTO medici (nume, prenume, data_nastere, CNP_medic, adresa, telefon, email, specializare, zi_saptamana, ora_start, ora_end, parola, id_clinica) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, account.getNume());
            preparedStatement.setString(2, account.getPrenume());
            preparedStatement.setString(3, account.getData_nastere());
            preparedStatement.setString(4, account.getCNP());
            preparedStatement.setString(5, account.getAdresa());
            preparedStatement.setString(6, account.getTelefon());
            preparedStatement.setString(7, account.getEmail());
            preparedStatement.setString(8, account.getSpecializare());
            preparedStatement.setString(9, account.getZileLucru());
            preparedStatement.setString(10, account.getOra_start());
            preparedStatement.setString(11, account.getOra_end());
            preparedStatement.setString(12, account.getParola());
            preparedStatement.setString(13, account.getClinicaId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean CreateClinica(ClinicaAdd clinica) {
        try {
            var preparedStatement = connection.prepareStatement(
                    "INSERT INTO Clinici (nume, adresa) VALUES (?, ?)");
            preparedStatement.setString(1, clinica.getNume());
            preparedStatement.setString(2, clinica.getAdresa());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public String loginUser(String cnp, String parola) {
        try {
            String sql = "SELECT role FROM users WHERE CNP = ? AND parola = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cnp);
            preparedStatement.setString(2, parola);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean loginMedic(String cnp, String parola) {
        try {
            String sql = "SELECT * FROM medici WHERE CNP_medic = ? AND parola = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cnp);
            preparedStatement.setString(2, parola);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void loadCliniciIntoComboBox(JComboBox<Clinica> comboBox) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id_clinica, nume, adresa FROM clinici")) {

            while (rs.next()) {
                Clinica clinica = new Clinica(
                        rs.getInt("id_clinica"),
                        rs.getString("nume"),
                        rs.getString("adresa")
                );
                comboBox.addItem(clinica);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadMediciIntoComboBox(JComboBox<MedicDisplay> comboBox) {
        String query = """
        SELECT m.*, c.nume AS clinic_name, c.adresa AS clinic_address
        FROM medici m
        JOIN clinici c ON m.id_clinica = c.id_clinica
    """;

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Medic medic = new Medic(
                        rs.getString("nume"),
                        rs.getString("prenume"),
                        rs.getString("data_nastere"),
                        rs.getString("CNP_medic"),
                        rs.getString("adresa"),
                        rs.getString("telefon"),
                        rs.getString("email"),
                        rs.getString("specializare"),
                        rs.getString("zi_saptamana"),
                        rs.getString("ora_start"),
                        rs.getString("ora_end"),
                        rs.getString("parola"),
                        rs.getString("id_clinica")
                );

                String clinicName = rs.getString("clinic_name");
                String clinicAddress = rs.getString("clinic_address");
                comboBox.addItem(new MedicDisplay(medic, clinicName, clinicAddress));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean CreateProgramare(Programari programare) {
        try {
            var preparedStatement = connection.prepareStatement(
                    "INSERT INTO Programari (cnp_user, cnp_medic, data_programare, descriere_afectiune, status) VALUES (?, ?, ?, ?, 'programata')");
            preparedStatement.setString(1, programare.getCnpUser());
            preparedStatement.setString(2, programare.getCnpMedic());
            java.util.Date utilDate = programare.getDataProgramare();
            Timestamp timestamp = new Timestamp(utilDate.getTime());
            preparedStatement.setTimestamp(3, timestamp);

            preparedStatement.setString(4, programare.getDescriereAfectiune());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Users getUserByCredentials(String cnp, String parola) {
        try {
            String sql = "SELECT * FROM users WHERE CNP = ? AND parola = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cnp);
            preparedStatement.setString(2, parola);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new Users(
                        rs.getString("nume"),
                        rs.getString("prenume"),
                        rs.getString("data_nastere"),
                        rs.getString("CNP"),
                        rs.getString("adresa"),
                        rs.getString("telefon"),
                        rs.getString("email"),
                        rs.getString("parola"),
                        rs.getString("role")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isSlotTaken(String cnpMedic, Timestamp timestamp) {
        try {
            String sql = "SELECT COUNT(*) FROM Programari WHERE cnp_medic = ? AND data_programare = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cnpMedic);
            stmt.setTimestamp(2, timestamp);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public DefaultListModel<DisplayProgramari> loadProgramariForUser(String cnpUser) {
        DefaultListModel<DisplayProgramari> model = new DefaultListModel<>();

        String sql = "SELECT p.cnp_user, p.cnp_medic, p.data_programare, p.descriere_afectiune, p.status, " +
                "m.nume AS nume_medic, m.prenume, m.specializare, m.email, m.id_clinica, " +
                "c.nume AS nume_clinica, c.adresa " +
                "FROM Programari p " +
                "JOIN Medici m ON p.cnp_medic = m.cnp_medic " +
                "JOIN Clinici c ON m.id_clinica = c.id_clinica " +
                "WHERE p.cnp_user = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cnpUser);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Date dataProgramare = new Date(rs.getTimestamp("data_programare").getTime());

                DisplayProgramari programare = new DisplayProgramari(
                        rs.getString("cnp_user"),
                        rs.getString("cnp_medic"),
                        dataProgramare,
                        rs.getString("descriere_afectiune"),
                        rs.getString("status"),
                        rs.getString("nume_medic"),
                        rs.getString("prenume"),
                        rs.getString("specializare"),
                        rs.getString("email"),
                        rs.getString("nume_clinica"),
                        rs.getString("adresa")
                );
                model.addElement(programare);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    public void loadProgramariModifica(String cnpUser, JComboBox<DisplayProgramari> comboBox) {
        String sql = "SELECT p.cnp_user, p.cnp_medic, p.data_programare, p.descriere_afectiune, p.status, " +
                "m.nume AS nume_medic, m.prenume, m.specializare, m.email, " +
                "c.nume AS nume_clinica, c.adresa " +
                "FROM Programari p " +
                "JOIN Medici m ON p.cnp_medic = m.cnp_medic " +
                "JOIN Clinici c ON m.id_clinica = c.id_clinica " +
                "WHERE p.cnp_user = ? and p.status = 'programata'";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cnpUser);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Date dataProgramare = new Date(rs.getTimestamp("data_programare").getTime());

                DisplayProgramari programare = new DisplayProgramari(
                        rs.getString("cnp_user"),
                        rs.getString("cnp_medic"),
                        dataProgramare,
                        rs.getString("descriere_afectiune"),
                        rs.getString("status"),
                        rs.getString("nume_medic"),
                        rs.getString("prenume"),
                        rs.getString("specializare"),
                        rs.getString("email"),
                        rs.getString("nume_clinica"),
                        rs.getString("adresa")
                );

                comboBox.addItem(programare);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDescriereProgramare(String cnpUser, String cnpMedic, Date dataProgramare, String newDescriere) {
        String sql = "UPDATE Programari SET descriere_afectiune = ? " +
                "WHERE cnp_user = ? AND cnp_medic = ? AND data_programare = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newDescriere);
            stmt.setString(2, cnpUser);
            stmt.setString(3, cnpMedic);
            stmt.setTimestamp(4
                    , new Timestamp(dataProgramare.getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void anuleazaProgramare(String cnpUser, String cnpMedic, Date dataProgramare, String newStatus) {
        String sql = "UPDATE Programari SET status = ? " +
                "WHERE cnp_user = ? AND cnp_medic = ? AND data_programare = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setString(2, cnpUser);
            stmt.setString(3, cnpMedic);
            stmt.setTimestamp(4
                    , new Timestamp(dataProgramare.getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Medic getMedicByCredentials(String cnp, String parola) {
        try {
            String sql = "SELECT * FROM medici WHERE CNP_medic = ? AND parola = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cnp);
            preparedStatement.setString(2, parola);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new Medic(
                        rs.getString("nume"),
                        rs.getString("prenume"),
                        rs.getString("data_nastere"),
                        rs.getString("CNP_medic"),
                        rs.getString("adresa"),
                        rs.getString("telefon"),
                        rs.getString("email"),
                        rs.getString("specializare"),
                        rs.getString("zi_saptamana"),
                        rs.getString("ora_start"),
                        rs.getString("ora_end"),
                        rs.getString("parola"),
                        rs.getString("id_clinica")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Clinica loadClinicaForMedic(String cnpUser) {
        String sql = "SELECT c.id_clinica, c.nume, c.adresa " +
                "FROM clinici c JOIN medici m ON c.id_clinica = m.id_clinica " +
                "WHERE m.cnp_medic = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cnpUser);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Clinica(
                        rs.getInt("id_clinica"),
                        rs.getString("nume"),
                        rs.getString("adresa")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public DefaultListModel<DisplayMedicProgramari> loadProgramariForMedic(String cnpMedic) {
        DefaultListModel<DisplayMedicProgramari> model = new DefaultListModel<>();
        String sql = "SELECT p.cnp_user, p.cnp_medic, p.data_programare, p.descriere_afectiune, p.status, " +
                "m.cnp_medic, " +
                "u.cnp, u.nume, u.prenume, u.data_nastere, u.adresa, u.telefon, u.email " +
                "FROM Programari p " +
                "JOIN Medici m ON p.cnp_medic = m.cnp_medic " +
                "JOIN Users u ON u.cnp = p.cnp_user " +
                "WHERE m.cnp_medic = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cnpMedic);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Date dataNastere = new Date(rs.getTimestamp("data_nastere").getTime());
                Date dataProgramare = new Date(rs.getTimestamp("data_programare").getTime());

                DisplayMedicProgramari programare = new DisplayMedicProgramari(
                        rs.getString("cnp_user"),
                        rs.getString("cnp_medic"),
                        rs.getString("nume"),
                        rs.getString("prenume"),
                        dataNastere,
                        rs.getString("telefon"),
                        rs.getString("email"),
                        dataProgramare,
                        rs.getString("descriere_afectiune"),
                        rs.getString("status")
                );
                model.addElement(programare);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }
}