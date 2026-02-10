
public enum OraEnd {
    ORA_14_00("14:00"), ORA_14_30("14:30"), ORA_15_00("15:00"), ORA_15_30("15:30"),
    ORA_16_00("16:00"), ORA_16_30("16:30"), ORA_17_00("17:00"), ORA_17_30("17:30"),
    ORA_18_00("18:00"), ORA_18_30("18:30"), ORA_19_00("19:00"), ORA_19_30("19:30"),
    ORA_20_00("20:00"), ORA_20_30("20:30"), ORA_21_00("21:00"), ORA_21_30("21:30"),
    ORA_22_00("22:00"), ORA_22_30("22:30"), ORA_23_00("23:00"), ORA_23_30("23:30"),
    ORA_23_59("23:59");

    private final String label;

    OraEnd(String label) { this.label = label; }

    @Override
    public String toString() { return label; }

    public String getLabel() { return label; }
}