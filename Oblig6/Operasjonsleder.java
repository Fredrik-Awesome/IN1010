import java.util.*;
import java.io.*;

public class Operasjonsleder implements Runnable {

    private Monitor monitor;
    private int antKanaler;
    private ArrayList<Melding> meldinger;
    private Melding m;

    public Operasjonsleder(Monitor m, int antKanaler) {
        this.monitor = m;
        this.antKanaler = antKanaler;
    }

    public void run() {
      try {
          meldinger = monitor.hentDekryptert();
          Collections.sort(meldinger);
          skrivTilFil();

      } catch(InterruptedException ie) {
          System.out.println(ie);
      }
    }

    public void skrivTilFil() {
        try {
            PrintWriter skriv1 = new PrintWriter("1.txt", "UTF-8");
            PrintWriter skriv2 = new PrintWriter("2.txt", "UTF-8");
            PrintWriter skriv3 = new PrintWriter("3.txt", "UTF-8");

            for (Melding mld : meldinger) {
                switch (mld.hentID()) {
                    case 1:
                        skriv1.println(mld + "\n\n");
                        break;
                    case 2:
                        skriv2.println(mld + "\n\n");
                        break;
                    case 3:
                        skriv3.println(mld + "\n\n");
                        break;
          }
        }
        skriv1.close();
        skriv2.close();
        skriv3.close();
        } catch (Exception e) {
            System.out.println("Fant ikke fil for utskrift");
        }
    }
}
