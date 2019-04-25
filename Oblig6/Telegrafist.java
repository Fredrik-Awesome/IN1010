class Telegrafist implements Runnable{

    private String melding;
    private Kanal kanal;
    private Monitor monitor;
    private int sekvensnr;

    public Telegrafist(Kanal kanal, Monitor monitor) {
        this.kanal = kanal;
        this.monitor = monitor;
    }

    public void run() {

        melding = kanal.lytt();

        while (melding != null) {
            System.out.println("Legger til kryptert melding...");
            Melding m = new Melding(melding, sekvensnr, kanal.hentId());
            monitor.leggTilKryptert(m);
            sekvensnr++;
            melding = kanal.lytt();
        }
        monitor.telegrafistFerdig();
    }
}
