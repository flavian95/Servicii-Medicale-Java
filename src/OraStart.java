
public enum OraStart {
    ORA_05_00("05:00"), ORA_05_30("05:30"), ORA_06_00("06:00"), ORA_06_30("06:30"),
    ORA_07_00("07:00"), ORA_07_30("07:30"), ORA_08_00("08:00"), ORA_08_30("08:30"),
    ORA_09_00("09:00"), ORA_09_30("09:30"), ORA_10_00("10:00"), ORA_10_30("10:30"),
    ORA_11_00("11:00"), ORA_11_30("11:30"), ORA_12_00("12:00");

    private final String label;

    OraStart(String label) { this.label = label; }

    @Override
    public String toString() { return label; }

    public String getLabel() { return label; }
}