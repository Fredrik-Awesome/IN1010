abstract class Rute {
    protected int kolonne;
    protected int rad;
    Labyrint labyrint;
    protected Liste<Rute> naboRuter = new Lenkeliste<>();
    protected Liste<Rute> besokt = new Lenkeliste<>();
    protected boolean alleredeBesokt = false;

    public Rute(int kol, int rad, Labyrint lab) {
        this.kolonne = kol;
        this.rad = rad;
        this.labyrint = lab;
    }


    public void finnNabo(Rute[][] rn, int antKol, int antRad) {

        if (rad > 0) {
            if (rn[rad][kolonne].tilTegn() != '#') {
                naboRuter.leggTil(rn[rad - 1][kolonne]); }
        } if (kolonne > 0) {
            if (rn[rad][kolonne].tilTegn() != '#') {
                naboRuter.leggTil(rn[rad][kolonne - 1]);}
        } if (rad < rn.length - 1) {
            if (rn[rad][kolonne].tilTegn() != '#') {
                naboRuter.leggTil(rn[rad + 1][kolonne]); }
        } if (kolonne < rn[0].length - 1) {
            if (rn[rad][kolonne].tilTegn() != '#') {
                naboRuter.leggTil(rn[rad][kolonne + 1]); }
        }
    }

    public void gaa(String vei) {
        alleredeBesokt = true;

        vei = vei + " --> " + "(" + kolonne + ", " + rad + ")";

        for (Rute r : naboRuter) {
                if (r.tilTegn() == '.') {
                    if (r.erBesokt() != true) {
                        r.gaa(vei);
                    }
                }
        }
        alleredeBesokt = false;
    }

    public void finnUtvei() {
         alleredeBesokt = true;

        for (Rute r : naboRuter) {
            r.gaa("(" + kolonne + ", " + rad + ")");
        }
        alleredeBesokt = false;
    }

    public boolean erBesokt() {
        return alleredeBesokt;
    }

    public String toString() {
        return "(" + kolonne + ", " + rad + ")";
    }

    protected abstract char tilTegn();
}
