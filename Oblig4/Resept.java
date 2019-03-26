abstract class Resept {

    public static int lmTeller = 0;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;
    protected int id;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        this.id = lmTeller++;
    }

    protected int hentId() {
        return id;
    }

    protected Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    protected Lege hentLege() {
        return utskrivendeLege;
    }

    protected Pasient hentPasientId() {
        return pasient;
    }

    protected int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit <= 0) {
            return false;
        }
        reit--;
        return true;
    }

    abstract public String farge();

    abstract public double prisAaBetale();

}
