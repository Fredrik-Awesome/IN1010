class MilitaerResept extends HvitResept {

    public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    public double prisAaBetale() {
        return(0);
    }

    public String farge() {
        return "Hvit";
    }

    @Override
    public String toString() {
        return("Type: MilitaerResept" + System.lineSeparator() +
        "ID: " + id + System.lineSeparator()) +
        "Legemiddel: " + legemiddel.hentNavn() + System.lineSeparator() +
        "Lege: " + utskrivendeLege.hentNavn() + System.lineSeparator() +
        "PasientID: " + pasientId + System.lineSeparator() +
        "Reit: " + reit + System.lineSeparator() +
        "Pris: " + prisAaBetale();

    }

}
