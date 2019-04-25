import java.util.*;

class Hovedprogram {
    private static int antTelegrafister = 3;
    private static int antKryptografer = 20;

    public static void main(String[] args) {

        Operasjonssentral ops = new Operasjonssentral(antTelegrafister);
        Kanal[] kanaler = ops.hentKanalArray();
        Monitor monitor = new Monitor(antTelegrafister, antKryptografer);
        Operasjonsleder opl = new Operasjonsleder(monitor, antTelegrafister);
        Thread[] telegrafister = new Thread[antTelegrafister];
        Thread[] kryptografer = new Thread[antKryptografer];

        for (int i = 0; i < antTelegrafister; i++) {
            telegrafister[i] = new Thread(new Telegrafist(kanaler[i], monitor));
        }

        for (int i = 0; i < antKryptografer; i++) {
            kryptografer[i] = new Thread(new Kryptograf(monitor));
        }

        Thread opLeder = new Thread(opl);

        for (Thread t : telegrafister) {
            t.start();
        }
        for (Thread k : kryptografer) {
            k.start();
        }
        opLeder.start();
    }
}
