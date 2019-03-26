class BlaaResept extends Resept {

    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
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
        "PasientID: " + pasientId + System.lineSeparator() +
        "Reit: " + reit + System.lineSeparator() +
        "Pris: " + prisAaBetale();

    }
}
