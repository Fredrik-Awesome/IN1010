class PreparatB extends Legemiddel {
    protected int styrke;

    public PreparatB(String navn, double pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentVanedannendeStyrke() {
        return this.styrke;
    }

    @Override
    public String toString() {
        return ("ID: " + id + System.lineSeparator() +
        "Navn: " + navn + System.lineSeparator() +
        "Preparat: B" + System.lineSeparator() + 
        "Pris: " + pris + System.lineSeparator() +
        "Virkestoff: " + virkestoff + System.lineSeparator() +
        "Styrke: " + styrke);
    }

}
