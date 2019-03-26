import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


class Regneklynge {
    //Deklarerer variabler til bruk senere i klassen
    int kapasitet;
    int paakrevdMinne;
    int teller = 0;

    //Oppretter en ArrayList for lagring av racks.
    //Velger ArrayList over vanlig array fordi det maa kunne legges til
    //nye racks avhengig av hvor mange noder vi faar inn, derfor kan det ikke
    //bestemmes noen storrelse paa forhand.
    ArrayList <Rack> alleRacks = new ArrayList <Rack> ();



    public Regneklynge(int noderPerRack) {
        kapasitet = noderPerRack;
        alleRacks.add(new Rack(kapasitet));

    }

    //Lager en andre konstruktor for aa ta inn data fra fil.
    public Regneklynge(String filnavn){
        dataFraFil(filnavn);
    }


    public void dataFraFil(String filnavn) {

        int antNoderFraFil;
        int minneFraFil;
        int antProsFraFil;

        //Sjekker om filen som sendes med eksisterer
        try (Scanner fil = new Scanner(new File(filnavn))) {

            this.kapasitet = fil.nextInt();

            //Gaar gjennom filen og tilordner de ulike verdiene til variabler
            //saa lenge det er flere objekter i filen.
            while(fil.hasNext()) {
                antNoderFraFil = fil.nextInt();
                minneFraFil = fil.nextInt();
                antProsFraFil  = fil.nextInt();

                for (int i = 0; i < antNoderFraFil; i++) {
                    this.plasserIRack(new Node(minneFraFil, antProsFraFil));
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println(filnavn + " kunne ikke leses.");
        }
    }

    public void plasserIRack(Node nyNode) {

        for (Rack rack : alleRacks) {
            if (rack.settInn(nyNode)) {
                return;
            }
        }
        teller++;
        //Oppretter et nytt rack og legger til i ArrayListen om alle racks er
        //fulle.
        Rack nyRack = new Rack(kapasitet);
        alleRacks.add(nyRack);
        nyRack.settInn(nyNode);
    }
    public int antProsessorer() {

        int antallProsessorer = 0;

        for (Rack rack: alleRacks) {
            antallProsessorer += rack.antallProsessorer();
        }
        return antallProsessorer;
    }

    public int noderMedNokMinne(int paakrevdMinne) {
        int nokMinne = 0;
        for (Rack rack : alleRacks) {
            nokMinne += rack.noderMedNokMinne(paakrevdMinne);
        }
        return nokMinne;
    }

    public int antRacks() {
        return alleRacks.size();
    }
}
