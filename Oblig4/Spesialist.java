class Spesialist extends Lege implements GodkjenningsFritak {

    protected int kontrollID;

    public Spesialist(String navn, int kontrollID) {
        super(navn);
        this.kontrollID = kontrollID;
    }

    public int hentKontrollID() {
        return kontrollID;
    }

    @Override
    public String toString() {
        return "Spesialist: " + navn + System.lineSeparator() +
        "KontrollID for " + navn + ": " + kontrollID;
    }

    @Override
    public Resept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {

        Resept resept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(resept);
        return resept;
    }

    @Override
    public Resept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        Resept resept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(resept);
        return resept;
    }

    @Override
    public Resept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        Resept resept = new MilitaerResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(resept);
        return resept;
    }

    @Override
    public Resept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        Resept resept = new Presept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(resept);
        return resept;
    }
}
