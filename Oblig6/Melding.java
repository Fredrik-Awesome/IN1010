public class Melding implements Comparable<Melding>{

    protected int sekvensnr;
    protected int kanalID;
    private String melding;

    public Melding(String meld, int sek, int id) {
        this.melding = meld;
        this.sekvensnr = sek;
        this.kanalID = id;
    }

    public String hentMelding() {
        return this.melding;
    }

    public void settDekrypt(String dekryptert) {
        this.melding = dekryptert;
    }

    public int hentID() {
        return kanalID;
    }

    public int hentSekvensnr() {
        return this.sekvensnr;
    }

    @Override
    public String toString() {
        return melding;
    }

    @Override
  public int compareTo(Melding m) {

    if (this.kanalID == m.kanalID) {
      return this.sekvensnr - m.sekvensnr;
    } else {
      return this.kanalID - m.kanalID;
      }
    }
}
