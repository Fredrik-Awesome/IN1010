abstract class Resept {

    public static int lmTeller = 0;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pasientId;
    protected int reit;
    protected int id;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
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

    protected int hentPasientId() {
        return pasientId;
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
