class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public double prisAaBetale() {
        return legemiddel.hentPris();
    }

    public String farge() {
        return ("Hvit");
    }

}
