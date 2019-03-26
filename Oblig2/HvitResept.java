class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    
    public double prisAaBetale() {
        return legemiddel.hentPris();
    }

    public String farge() {
        return ("Hvit");
    }

}
