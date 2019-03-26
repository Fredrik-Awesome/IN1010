class Pasient {
    public int teller = 0;
    private static int id;
    public String navn;
    public String fodselsnr;
    Stabel<Resept> stabel;

    public Pasient(String navn, String fodselsnr) {
        this.navn = navn;
        this.fodselsnr = fodselsnr;
        teller = id;
        id++;
        stabel = new Stabel<Resept> ();
    }

    public void leggTilResept(Resept res) {
        stabel.leggPaa(res);
    }

    public void hentData() {
        for (int i = 0; i < stabel.stoerrelse(); i++) {
            System.out.print(i);
            System.out.println(stabel.hent(i));
        }
    }

    public int hentID() {
        return teller;
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFodselsnr() {
        return fodselsnr;
    }

    public Stabel <Resept> hentResepter() {
        return stabel;
    }

    @Override
    public String toString() {
        return hentNavn() + System.lineSeparator() +
        "Fodselsnummer: " + hentFodselsnr();
    }
}
