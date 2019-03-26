class PreparatA extends Legemiddel {
    protected int styrke;

    public PreparatA(String navn, double pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentNarkotiskStyrke() {
        return this.styrke;
    }

    @Override
    public String toString() {
        return ("ID: " + id + System.lineSeparator() +
        "Navn: " + navn + System.lineSeparator() +
        "Pris: " + pris + System.lineSeparator() +
        "Virkestoff: " + virkestoff + System.lineSeparator() +
        "Styrke: " + styrke);
    }
}
