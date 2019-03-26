class Lege {
    protected String navn;

    public Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }

    public Resept skrivResept(Legemiddel legemiddel, int pasientID, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof PreparatA && this instanceof Spesialist) {
            return new BlaaResept(legemiddel, this, pasientID, reit);
        }
        else if (legemiddel instanceof PreparatB || legemiddel instanceof PreparatC) {
            return new BlaaResept(legemiddel, this, pasientID, reit);
        }
        else {
            throw new UlovligUtskrift(this, legemiddel);
        }
    }

    public String toString() {
        return "Lege: " + navn;
    }
}
