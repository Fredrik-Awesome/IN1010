import java.util.concurrent.locks.*;
import java.util.ArrayList;

class Monitor {

    private int antTelegrafister;
    private int antKryptografer;
    private int ferdigeTelegrafister = 0;
    private int ferdigeKryptografer = 0;
    private boolean alleTelegrafisterFerdige = false;
    private boolean alleKryptograferFerdige = false;

    private ArrayList<Melding> krypterteMeldinger = new ArrayList<>();
    private ArrayList<Melding> dekrypterte = new ArrayList <> ();

    private Lock laas = new ReentrantLock();
    private Condition nyMeld = laas.newCondition();
    private Condition kryptoFerdig = laas.newCondition();

    public Monitor(int antTelegrafister, int antKryptografer) {
        this.antTelegrafister = antTelegrafister;
        this.antKryptografer = antKryptografer;
    }

    public void leggTilKryptert(Melding m) {

        laas.lock();

        try {
            krypterteMeldinger.add(m);
            nyMeld.signalAll();
        } finally {
            laas.unlock();
        }
    }

    public Melding hentKryptertMeld() throws InterruptedException {

        laas.lock();

        try {
            while (krypterteMeldinger.size() == 0) {
                nyMeld.await();
            }
            Melding m = krypterteMeldinger.get(0);
            krypterteMeldinger.remove(0);
            return m;
        } finally {
            laas.unlock();
        }
    }

    public int hentKryptID(Melding m) {
        return m.hentID();
    }

    public void leggTilDekryptert(Melding m) {

        laas.lock();

        try {
            dekrypterte.add(m);
        } finally {
            laas.unlock();
        }
    }

    public void telegrafistFerdig() {
        laas.lock();

        try {
            ferdigeTelegrafister++;
            if (ferdigeTelegrafister == antTelegrafister) {
                alleTelegrafisterFerdige = true;
            }
        } finally {
            laas.unlock();
        }
    }

    public void kryptografFerdig() {

        laas.lock();

        try {
            ferdigeKryptografer++;

            if (ferdigeKryptografer == antKryptografer) {
                alleKryptograferFerdige = true;
            }
        } finally {
            kryptoFerdig.signal();
            laas.unlock();
        }
    }

    public boolean alleTelegrafisterFerdige() {
        if (krypterteMeldinger.size() == 0) {
            return alleTelegrafisterFerdige;
        } else {
            return false;
        }
    }

    public boolean alleFerdige() {
        return alleKryptograferFerdige;
    }


    public ArrayList<Melding> hentDekryptert() throws InterruptedException {

      laas.lock();

      try {
          while (!alleFerdige()) {
              kryptoFerdig.await();
          }
          return dekrypterte;

      } finally {
          laas.unlock();
      }

  }
}
