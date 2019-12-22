package Castle_Wars.constants;

public enum Team {

    ZAMORAK("Zamorak Portal"),
    SARADOMIN("Saradomin Portal"),
    GUTHIX("Guthix Portal");

    private String portalName;

    Team(String portalName) {
        this.portalName = portalName;
    }

    public String getPortalName() {
        return portalName;
    }
}
