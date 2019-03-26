class Lege implements Comparable<Lege>{
    protected String navn;
    Lenkeliste<Resept> utskrevedeResepter;

    public Lege(String navn) {
        this.navn = navn;
        utskrevedeResepter = new Lenkeliste<Resept>();
    }

    public String hentNavn() {
        return navn;
    }

    public Resept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        Resept resept;
        if (legemiddel instanceof PreparatA) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        else {
            resept = new BlaaResept(legemiddel, this, pasient, reit);
            utskrevedeResepter.leggTil(resept);
            return resept;
        }
    }

    public Resept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        Resept resept;
        if (legemiddel instanceof PreparatA) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        else {
            resept = new HvitResept(legemiddel, this, pasient, reit);
            utskrevedeResepter.leggTil(resept);
            return resept;
        }
    }

    public Resept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        Resept resept;
        if (legemiddel instanceof PreparatA) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        else {
            resept = new MilitaerResept(legemiddel, this, pasient, reit);
            utskrevedeResepter.leggTil(resept);
            return resept;
        }
    }

    public Resept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        Resept resept;
        if (legemiddel instanceof PreparatA) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        else {
            resept = new Presept(legemiddel, this, pasient, reit);
            utskrevedeResepter.leggTil(resept);
            return resept;
        }
    }

    public Lenkeliste <Resept> hentResepter() {
        return utskrevedeResepter;
    }

    public void leggTilResept(Resept ny) {
        utskrevedeResepter.leggTil(ny);
    }

    public String toString() {
        return "Lege: " + navn;
    }

    @Override
    public int compareTo(Lege annen) {
        int bak = this.navn.compareTo(annen.navn);
        return bak;
    }

}
