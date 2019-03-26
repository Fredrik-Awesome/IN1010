class PreparatC extends Legemiddel {
    public PreparatC(String navn, double pris, double virkestoff){
        super(navn, pris, virkestoff);
    }

    @Override
    public String toString() {
        return ("ID: " + id + System.lineSeparator() +
        "Navn: " + navn + System.lineSeparator() +
        "Pris: " + pris + System.lineSeparator() +
        "Virkestoff: " + virkestoff);
    }
}
