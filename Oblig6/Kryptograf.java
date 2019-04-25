class Kryptograf implements Runnable {

    protected Melding melding;
    protected Monitor monitor;
    private String krypt;

    public Kryptograf(Monitor mon) {
        monitor = mon;
    }

    public void run() {
        try {
            while (!monitor.alleTelegrafisterFerdige()){
                this.melding = monitor.hentKryptertMeld();
                this.krypt = melding.hentMelding();
                System.out.println("Dekrypterer meldinger...");
                this.krypt = Kryptografi.dekrypter(krypt);
                melding.settDekrypt(krypt);
                System.out.println("Legger til dekryptert melding...");
                monitor.leggTilDekryptert(melding);


            }
            monitor.kryptografFerdig();

        } catch (Exception e) {
            System.out.println("Det oppstod en feil under dekrypteringen.");
        }
    }
}
