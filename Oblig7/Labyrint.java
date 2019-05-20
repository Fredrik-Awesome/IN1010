import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Labyrint {
    private int antallRader;
    private int antallKolonner;
    private Rute[][] rutenett;
    protected Liste<String> utveier = new Lenkeliste<>();


    private Labyrint(int antallRader, int antallKolonner, Rute[][] rutenett) {
        this.antallRader = antallRader;
        this.antallKolonner = antallKolonner;
        this.rutenett = rutenett;
    }

    public static Labyrint lesFraFil(File fil) throws FileNotFoundException{

        Scanner les = new Scanner(fil);

        String lest = les.nextLine();

        String[] splittet = lest.split(" ");

        int kolonner = Integer.parseInt(splittet[0]);
        int rader = Integer.parseInt(splittet[1]);
        Rute[][] nyttRN = new Rute[kolonner][rader];


        Labyrint lab = new Labyrint(kolonner, rader, nyttRN);

        for (int i = 0; i < kolonner; i++) {
            splittet = les.nextLine().split("");
            for (int j = 0; j < rader; j++) {
                if (splittet[j].equals("#")) {
                    nyttRN[i][j] = new SortRute(j, i, lab);
                } else {
                    if (j == 0 || i == 0 || i == kolonner - 1 || j == rader - 1) {
                        nyttRN[i][j] = new Aapning(j, i, lab);
                    } else {
                        nyttRN[i][j] = new HvitRute(j, i, lab);
                    }
                }
            }
        }
        for (Rute[] k : nyttRN) {
            for (Rute r : k) {
                r.finnNabo(nyttRN, kolonner, rader);
            }
        }

        return lab;
    }
    public Liste<String> finnUtveiFra(int kol, int rad) {
        utveier.flush();

        rutenett[rad][kol].finnUtvei();
        return utveier;
    }

    public void skrivUtveier() {
        for (String s : utveier) {
            System.out.println(s);
        }
    }

    public void leggTilString(String vei) {
        utveier.leggTil(vei);
    }

    public int hentRader(){
        return antallRader;
    }

    public int hentKolonner(){
        return antallKolonner;
    }

    public Rute[][] hentRutenett() {
        return rutenett;
    }


    public String toString() {
    String labRet = "";

    for (int i = 0; i < antallRader; i++) {
      for (int j = 0; j < antallKolonner; j++) {
        labRet = labRet + rutenett[i][j].tilTegn();
      }
      labRet = labRet + "\n";
    }
    return labRet;
  }
}
