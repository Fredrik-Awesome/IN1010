class Presept extends HvitResept{
    public Presept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    public double prisAaBetale() {
        if ((legemiddel.hentPris() - 108) >= 0) {
            return(legemiddel.hentPris() - 108);
        }
        return(0);
    }

    public String farge() {
        return "Hvit";
    }

    @Override
    public String toString() {
        return("Type: P-Resept" + System.lineSeparator() +
        "ID: " + id + System.lineSeparator()) +
        "Legemiddel: " + legemiddel.hentNavn() + System.lineSeparator() +
        "Lege: " + utskrivendeLege.hentNavn() + System.lineSeparator() +
        "PasientID: " + pasientId + System.lineSeparator() +
        "Reit: " + reit + System.lineSeparator() +
        "Pris: " + prisAaBetale();

    }
}
