class BlaaResept extends Resept {

    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public String farge() {
        return "Blaa";
    }

    public double prisAaBetale() {
        return (legemiddel.hentPris()*.25);
    }
    @Override
    public String toString() {
        return("Type: Blaa resept" + System.lineSeparator() +
        "ID: " + id + System.lineSeparator()) +
        "Legemiddel: " + legemiddel.hentNavn() + System.lineSeparator() +
        "Lege: " + utskrivendeLege.hentNavn() + System.lineSeparator() +
        pasient + System.lineSeparator() +
        "Reit: " + reit + System.lineSeparator() +
        "Pris: " + prisAaBetale();

    }
}
