class Presept extends HvitResept{
    public Presept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
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
        "Pasient: " + pasient + System.lineSeparator() +
        "Reit: " + reit + System.lineSeparator() +
        "Pris: " + prisAaBetale();

    }
}
