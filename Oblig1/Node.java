class Node {

    //Deklarerer variabler
    int minne;
    int antPros;

    public Node(int min, int pros) {
        this.minne = min;
        this.antPros = pros;
    }
    //Returnerer antall prosessorer i noden.
    public int antProsessorer() {
        return antPros;
    }

    //Sjekker om noden har tilstrekkelig minne ift. paakrevdMinne.
    public boolean nokMinne(int paakrevdMinne) {
        if (paakrevdMinne <= minne) {
            return true;
        } else {
            return false;
        }
    }
}
