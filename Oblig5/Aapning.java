class Aapning extends HvitRute {
    public Aapning(int kol, int rad, Labyrint lab) {
        super(kol, rad, lab);
    }

    @Override
    public void gaa(String vei) {
    labyrint.leggTilString(vei + " --> " + "(" + kolonne + "," + rad + ")");
    }

    @Override
    public void finnUtvei() {
    labyrint.leggTilString("(" + kolonne + "," + rad + ")");

    super.finnUtvei();
  }
}
