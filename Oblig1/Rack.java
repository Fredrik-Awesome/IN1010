class Rack {

    //Deklarerer variabler
    int antProsessorer;
    int maksAntNoder;
    Node[] rack;
    int teller = 0;

    //Vi har en paa forhand gitt lengde paa hver array, og jeg oppretter derfor
    //en vanlig array fremfor en ArrayList.
    public Rack(int maxNoder) {

        maksAntNoder = maxNoder;
        rack = new Node[maksAntNoder];
    }
    //Sjekker om det er plass flere noder i racket. Om det er plass legges det
    //til en node i racken og returneres true, ellers returneres false.
    public boolean settInn(Node nyNode) {

        int maksLengde = maksAntNoder;

        if (teller < maksLengde) {
            rack[teller] = nyNode;
            teller++;
            return true;
        } else {
            return false;
        }
    }

    //Returnerer antall noder i racket.
    public int getAntNoder() {

        return teller;
    }

    //Gaar gjennom racket og returnerer antall prosessorer.
    public int antallProsessorer() {

        int antallProsessorer = 0;

        for (Node node: rack) {
            if (node != null){
                antallProsessorer += node.antProsessorer();
            }
        }
        return antallProsessorer;
    }

    //Gaar gjennom racket og returnerer hvor mange noder som har minne over
    //paakrevdMinne.
    public int noderMedNokMinne(int paakrevdMinne) {

        int nokMinne = 0;

        for (Node node : rack) {
            if (node != null) {
                if (node.nokMinne(paakrevdMinne)) {
                    nokMinne++;
                }
            }
        }
        return nokMinne;
    }
}
