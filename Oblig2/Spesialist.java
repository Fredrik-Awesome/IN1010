class Spesialist extends Lege implements GodkjenningsFritak {

    protected int kontrollID;

    public Spesialist(String navn, int kontrollID) {
        super(navn);
        this.kontrollID = kontrollID;
    }

    public int hentKontrollID() {
        return kontrollID;
    }

    @Override
    public String toString() {
        return "Spesialist: " + navn + System.lineSeparator() +
        "KontrollID for " + navn + ": " + kontrollID;
    }
}
