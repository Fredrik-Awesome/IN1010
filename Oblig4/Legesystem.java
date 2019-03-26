import java.util.*;
import java.io.*;

public class Legesystem{
    // Opprett lister som lagrer objektene i legesystemet
    protected static SortertLenkeliste <Lege> leger = new SortertLenkeliste <Lege> ();
    protected static Lenkeliste <Legemiddel> legemidler = new Lenkeliste <Legemiddel> ();
    protected static Lenkeliste <Resept> resepter = new Lenkeliste <Resept> ();
    protected static Lenkeliste <Pasient> pasienter = new Lenkeliste <Pasient> ();


    public static void main(String[] args){
        File file = new File("inndata.txt");
        lesFraFil(file);
        hovedmeny();
        Scanner inn = new Scanner(System.in);
        int input = inn.nextInt();
        while (input != 0) {
            menyvalg(input);
            hovedmeny();
            input = inn.nextInt();
        }
        System.out.println("\nProgrammet er avsluttet.");
    }
    private static void lesFraFil(File fil){
        Scanner scanner = null;

        try{
            scanner = new Scanner(fil);
        }catch(FileNotFoundException e){
            System.out.println("Fant ikke filen, starter opp som et tomt Legesystem");
            return;
        }

        String innlest = scanner.nextLine();


        while(scanner.hasNextLine()){

           String[] info = innlest.split(" ");

           // Legger til alle pasientene i filen
           if(info[1].compareTo("Pasienter") == 0){
               while(scanner.hasNextLine()) {
                   innlest = scanner.nextLine();
                   info = innlest.split(", ");

                   //Om vi er ferdig med å legge til pasienter, bryt whileløkken,
                   //slik at vi fortsetter til koden for å legge til legemiddler
                   if(innlest.charAt(0) == '#'){
                       break;
                   }
                    Pasient nyPas = new Pasient(info[0], info[1]);

                    pasienter.leggTil(nyPas);
                }

            }
            //Legger inn Legemidlene
            else if(info[1].compareTo("Legemidler") == 0){
                while(scanner.hasNextLine()){
                    innlest = scanner.nextLine();
                    //Om vi er ferdig med å legge til legemidler, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til leger
                    if(innlest.charAt(0) == '#'){
                        break;
                    }
                    String[] legemiddel = innlest.split(", ");
                    if(legemiddel[1].compareTo("a") == 0){

                        PreparatA prepA = new PreparatA(legemiddel[0], Double.parseDouble(legemiddel[2]), Double.parseDouble(legemiddel[3]), Integer.parseInt(legemiddel[4]));
                        legemidler.leggTil(prepA);
                    }
                    else if(legemiddel[1].compareTo("b") == 0){
                        PreparatB prepB = new PreparatB(legemiddel[0], Double.parseDouble(legemiddel[2]), Double.parseDouble(legemiddel[3]), Integer.parseInt(legemiddel[4]));
                        legemidler.leggTil(prepB);
                    }else if (legemiddel[1].compareTo("c") == 0){
                        PreparatC prepC = new PreparatC(legemiddel[0], Double.parseDouble(legemiddel[2]), Double.parseDouble(legemiddel[3]));
                        legemidler.leggTil(prepC);
                    }

                }
            }
            //Legger inn leger
            else if(info[1].compareTo("Leger") == 0){
                while(scanner.hasNextLine()){
                    innlest = scanner.nextLine();
                    //Om vi er ferdig med å legge til leger, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til resepter
                    if(innlest.charAt(0) == '#'){
                        break;
                    }
                    info = innlest.split(", ");
                    int kontrollid = Integer.parseInt(info[1]);
                    if(kontrollid == 0){

                        Lege nyLege = new Lege(info[0]);
                        leger.leggTil(nyLege);
                    }else{
                        Spesialist nySpes = new Spesialist(info[0], Integer.parseInt(info[1]));
                        leger.leggTil(nySpes);
                    }
                }

            }
            //Legger inn Resepter
            else if(info[1].compareTo("Resepter") == 0){
                while(scanner.hasNextLine()){

                    innlest = scanner.nextLine();
                    info = innlest.split(", ");

                    Legemiddel legemiddel = legemidler.hent(Integer.parseInt(info[0]));
                    Lege lege = finnLege(info[1]);
                    Pasient pasient = finnPasient(Integer.parseInt(info[2]));
                    int reit = Integer.parseInt(info[3]);

                    Resept nyRes = new BlaaResept(legemiddel, lege, pasient, reit);
                    pasient.leggTilResept(nyRes);
                    lege.leggTilResept(nyRes);
                    resepter.leggTil(nyRes);
                }
            }
        }
    }

    public static Lege finnLege(String lege) {
        Lege finnL = null;
        for (Lege l : leger) {
            if (lege.equals(l.hentNavn())) {
                finnL = l;
            }
        }
        return finnL;

    }

    public static Pasient finnPasient(int pas) {
        Pasient finnP = null;
        for (Pasient p : pasienter) {
            if (pas == p.hentID()) {
                finnP = p;
            }
        }
        return finnP;
    }

    public static void hovedmeny() {
        System.out.println();
        System.out.println();
        System.out.println("****************************HOVEDMENY****************************");
        System.out.println("Velg en kommando fra menyen under. Skriv inn tallet i parentes.");
        System.out.println("(1) Skriv ut en fullstendig oversikt over pasienter,\n" +
        "leger, legemidler og resepter.");
        System.out.println("(2) Opprett og legg til et nytt element i systemet.");
        System.out.println("(3) Bruk en resept fra listen til en pasient.");
        System.out.println("(4) Skriv ut statistikk.");
        System.out.println("(0) Avslutt programmet");
    }

    public static void totalUtskrift() {
        System.out.println("\nPasienter:");
        skrivUtPasienter();
        System.out.println("\nLeger:");
        skrivUtLeger();
        System.out.println("\nLegemidler:");
        skrivUtLegemidler();
        System.out.println("\nResepter:");
        skrivUtResepter();
    }

    public static void skrivUtLeger() {
        for (Lege l : leger) {
            System.out.println(l);
            System.out.println();
        }
    }

    public static void skrivUtLegemidler() {
        for (Legemiddel lm : legemidler) {
            System.out.println(lm);
            System.out.println();
        }
    }

    public static void skrivUtPasienter() {
        for (Pasient p : pasienter) {
            System.out.println(p);
            System.out.println();
        }
    }

    public static void skrivUtPasienterMedID() {
        int teller = 0;
        for (Pasient p : pasienter) {
            System.out.print("ID: ");
            System.out.println(teller);
            System.out.println(p);
            System.out.println();
            teller++;
        }
    }

    public static void skrivUtResepter() {
        for (Resept r : resepter) {
            System.out.println(r);
            System.out.println();
        }
    }

    public static void menyvalg(int input) {
        if (input == 1) {
            totalUtskrift();
        } else if (input == 2) {
            leggTilNy();
        } else if (input == 3) {
            brukResept();
        } else if (input == 4) {
            statistikk();
        }
    }

    public static void leggTilNy() {
        System.out.println("****************************VALGMENY****************************");
        System.out.println("Fyll inn tallet i parentes. Vil du legge til:\n" +
        "(1) Ny lege\n" + "(2) Ny pasient\n" + "(3) Ny resept\n" + "(4) Nytt legemiddel\n"
        + "(0) Gaa tilbake til hovedmeny");

        Scanner inn = new Scanner(System.in);
        int input = inn.nextInt();

        while (input != 0) {
            if (input == 1) {//Legg til ny lege
                nyLege();
            }else if (input == 2) {//Legg til ny pasient
                nyPasient();
            }else if (input == 3) {//Legg til ny resept
                nyResept();
            }else if (input == 4) {//Legg til nytt legemiddel
                nyttLegemiddel();
            }else {
                System.out.println("Velg et tall fra menyen.\n");
            }
            input = inn.nextInt();
        }
    }

    public static void nyLege() {
        String navnLege;
        int kontID;
        System.out.println("***********************LEGG TIL NY LEGE***********************");
        System.out.println("Fyll inn navn på legen:\n");
        Scanner legeInn = new Scanner(System.in);
        navnLege = legeInn.nextLine();
        System.out.println("Fyll inn kontrollID (0 for vanlig lege):\n");
        kontID = legeInn.nextInt();

        if (kontID == 0) {
            Lege ny = new Lege(navnLege);
            leger.leggTil(ny);
        }else {
            Lege ny = new Spesialist(navnLege, kontID);
            leger.leggTil(ny);
        }
        System.out.println("Onsker du aa legge til flere leger?\n" +
        "(1) Ja" + "(0) Nei\n");
    }

    public static void nyPasient() {
        String navnPasient;
        String fodselsnr;
        System.out.println("**********************LEGG TIL NY PASIENT*********************");
        System.out.println("Fyll inn navn på pasienten: \n");
        Scanner pasInn = new Scanner(System.in);
        navnPasient = pasInn.nextLine();
        System.out.println("Fyll inn pasientens fodselsnr.:\n");
        fodselsnr = pasInn.nextLine();
        Pasient nyPas = new Pasient(navnPasient, fodselsnr);
        pasienter.leggTil(nyPas);

        System.out.println("Onsker du aa legge til flere Pasienter?\n" +
        "(2) Ja" + "(0) Nei\n");
    }

    public static void nyResept() {
        Legemiddel lm;
        Lege lege;
        Pasient pas;
        int reit;

        System.out.println("**********************LEGG TIL NY RESEPT*********************");
        System.out.println("Hvilken lege skal skrive ut resepten? (Skriv navnet nøyaktig slik det oppgis)\n");
        skrivUtLeger();
        Scanner resInn = new Scanner(System.in);
        lege = finnLege(resInn.nextLine());
        System.out.println("Hvilken type resept onsker du aa legge til?\n" +
        "(a) Blaa resept\n" +
        "(b) Hvit resept\n" +
        "(c) Presept\n" +
        "(d) Militaer-resept\n");

        String type = resInn.nextLine();
        if (type.equals("a")) {//Blaa resept
            int teller = 0;
            System.out.println("Oppgi hvilket legemiddel som skal legges til (ID):");
            for (Legemiddel lmScan : legemidler) {
                System.out.println(lmScan);
            }
            lm = legemidler.hent(resInn.nextInt());
            System.out.println("Oppgi pasienten som resepten skal skrives til (ID):");
            for (Pasient p : pasienter) {
                System.out.print("ID: ");
                System.out.println(teller);
                System.out.println(p);
                teller++;
            }
            pas = finnPasient(resInn.nextInt());
            System.out.println("Oppgi reit for resepten:");
            reit = resInn.nextInt();

            try{
                resepter.leggTil(lege.skrivBlaaResept(lm, pas, reit));
                pas.leggTilResept(lege.skrivBlaaResept(lm, pas, reit));
                System.out.println("Resept lagt til.");
                System.out.println("Trykk (3) for aa legge til en ny resept, eller (0) for aa returnere til hovedmeny.");
            } catch (UlovligUtskrift e) {
                System.out.println(e);
                System.out.println("Trykk (3) for aa prove igjen, eller (0) for aa returnere til hovedmeny");
            }

        } else if (type.equals("b")) {//Hvit resept
            int teller = 0;
            System.out.println("Oppgi hvilket legemiddel som skal legges til (ID):");
            for (Legemiddel lmScan : legemidler) {
                System.out.println(lmScan);
            }
            lm = legemidler.hent(resInn.nextInt());
            System.out.println("Oppgi pasienten som resepten skal skrives til (ID):");
            for (Pasient p : pasienter) {
                System.out.print("ID: ");
                System.out.println(teller);
                System.out.println(p);
                teller++;
            }
            pas = finnPasient(resInn.nextInt());
            System.out.println("Oppgi reit for resepten:");
            reit = resInn.nextInt();


            try{
                resepter.leggTil(lege.skrivHvitResept(lm, pas, reit));
                pas.leggTilResept(lege.skrivHvitResept(lm, pas, reit));
                System.out.println("Resept lagt til.");
                System.out.println("Trykk (3) for aa legge til en ny resept, eller (0) for aa returnere til hovedmeny.");
            } catch (UlovligUtskrift e) {
                System.out.println(e);
                System.out.println("Trykk (3) for aa prove igjen, eller (0) for aa returnere til hovedmeny");
            }


        } else if (type.equals("c")) {//Presept
            int teller = 0;
            System.out.println("Oppgi hvilket legemiddel som skal legges til (ID):");
            for (Legemiddel lmScan : legemidler) {
                System.out.println(lmScan);
            }
            lm = legemidler.hent(resInn.nextInt());
            System.out.println("Oppgi pasienten som resepten skal skrives til (ID):");
            for (Pasient p : pasienter) {
                System.out.print("ID: ");
                System.out.println(teller);
                System.out.println(p);
                teller++;
            }
            pas = finnPasient(resInn.nextInt());
            reit = 3;

            try{
                resepter.leggTil(lege.skrivPResept(lm, pas, reit));
                pas.leggTilResept(lege.skrivPResept(lm, pas, reit));
                System.out.println("Resept lagt til.");
                System.out.println("Trykk (3) for aa legge til en ny resept, eller (0) for aa returnere til hovedmeny.");
            } catch (UlovligUtskrift e) {
                System.out.println(e);
                System.out.println("Trykk (3) for aa prove igjen, eller (0) for aa returnere til hovedmeny");
            }

        } else {//Militaerresept
            int teller = 0;
            System.out.println("Oppgi hvilket legemiddel som skal legges til (ID):");
            for (Legemiddel lmScan : legemidler) {
                System.out.println(lmScan);
            }
            lm = legemidler.hent(resInn.nextInt());
            System.out.println("Oppgi pasienten som resepten skal skrives til (ID):");
            for (Pasient p : pasienter) {
                System.out.print("ID: ");
                System.out.println(teller);
                System.out.println(p);
                teller++;
            }
            pas = finnPasient(resInn.nextInt());
            System.out.println("Oppgi reit for resepten:");
            reit = resInn.nextInt();

            try{
                resepter.leggTil(lege.skrivMilitaerResept(lm, pas, reit));
                pas.leggTilResept(lege.skrivMilitaerResept(lm, pas, reit));
                System.out.println("Resept lagt til.");
                System.out.println("Trykk (3) for aa legge til en ny resept, eller (0) for aa returnere til hovedmeny.");
            } catch (UlovligUtskrift e) {
                System.out.println(e);
                System.out.println("Trykk (3) for aa prove igjen, eller (0) for aa returnere til hovedmeny");
            }
        }

    }

    public static void nyttLegemiddel() {
        String navn;
        double pris;
        double virkestoff;
        int styrke;
        Legemiddel lmNy;
        System.out.println("**********************LEGG TIL NYTT LEGEMIDDEL*********************");
        System.out.println("Onsker du aa legge til:\n" + "(a) Preparat A (Narkotisk)\n" +
        "(b) Preparat B (Vanedannende)\n" +
        "(c) Preparat C");
        Scanner lmInn = new Scanner(System.in);
        if (lmInn.nextLine().equals("a")) {
            System.out.println("Oppgi navn paa legemiddelet:\n");
            navn = lmInn.nextLine();
            System.out.println("Oppgi pris paa legemiddel:\n");
            pris = lmInn.nextDouble();
            System.out.println("Oppgi virkestoff for legemiddel:\n");
            virkestoff = lmInn.nextDouble();
            System.out.println("Oppgi styrke paa legemiddel:\n");
            styrke = lmInn.nextInt();
            lmNy = new PreparatA(navn, pris, virkestoff, styrke);
        }
        else if (lmInn.nextLine().equals("b")) {
            System.out.println("Oppgi navn paa legemiddelet:\n");
            navn = lmInn.nextLine();
            System.out.println("Oppgi pris paa legemiddel:\n");
            pris = lmInn.nextDouble();
            System.out.println("Oppgi virkestoff for legemiddel:\n");
            virkestoff = lmInn.nextDouble();
            System.out.println("Oppgi styrke paa legemiddel:\n");
            styrke = lmInn.nextInt();
            lmNy = new PreparatB(navn, pris, virkestoff, styrke);
        }
        else {
            System.out.println("Oppgi navn paa legemiddelet:\n");
            navn = lmInn.nextLine();
            System.out.println("Oppgi pris paa legemiddel:\n");
            pris = lmInn.nextDouble();
            System.out.println("Oppgi virkestoff for legemiddel:\n");
            virkestoff = lmInn.nextDouble();
            lmNy = new PreparatC(navn, pris, virkestoff);
        }
        legemidler.leggTil(lmNy);
        System.out.println("Onsker du aa legge til flere legemidler?\n" +
        "(4) Ja" + "(0) Nei\n");

    }

    public static void brukResept() {
        System.out.println("Hvilken pasient vil du se resepter for? (ID)");
        skrivUtPasienterMedID();
        Scanner resBruk = new Scanner(System.in);

        int inp = resBruk.nextInt();

        Pasient pasient = finnPasient(inp);

        Stabel <Resept> resListe = pasient.hentResepter();

        System.out.print("Valgt pasient: ");
        System.out.println(pasient);

        int teller = 0;
        System.out.println("Hvilken resept vil du bruke?");
        for (Resept r : resListe) {
            System.out.println(teller + ": " + r.hentLegemiddel().hentNavn() + " (Antall reit: " +
            r.hentReit() + ")");
            teller++;
        }

        inp = resBruk.nextInt();
        Resept resept = resListe.hent(inp);

        if (resept.bruk()) {
            System.out.println("Resept brukt. gjenvaerende reit: " + resept.hentReit());
        } else {
            System.out.println("Ingen gjenvaerende reit. Resepten ble ikke brukt");
        }

        System.out.println("Tast (3) for aa prove paa nytt, eller tast (0) for aa returnere til hovedmeny");
    }

    public static void statistikk() {
        System.out.println("**********************STATISTIKK*********************");
        System.out.println("Velg hva du onsker aa skrive ut statistikk om:");
        System.out.println("(1) Antall utskrevne resepter paa narkotiske legemidler\n" +
        "(2) Antall utskrevne resepter paa vanedannende legemidler\n" +
        "(3) Statistikk om mulig misbruk av narkotiske legemidler");

        Scanner statScan = new Scanner(System.in);
        int inp = statScan.nextInt();

        if (inp == 1) {
            int utskrevneNarkotiske = 0;
            for (Resept r : resepter) {
                if(r.hentLegemiddel() instanceof PreparatA) {
                    utskrevneNarkotiske++;
                }
            }
            System.out.println("Antall utskrevne resepter paa narkotiske legemidler: " + utskrevneNarkotiske);
        } else if (inp == 2) {
            int utskrevneVanedannende = 0;
            for (Resept r : resepter) {
                if(r.hentLegemiddel() instanceof PreparatB) {
                    utskrevneVanedannende++;
                }
            }
            System.out.println("Antall utskrevne resepter paa vanedannende legemidler: " + utskrevneVanedannende);
        } else if (inp == 3) {
            for (Lege l : leger) {
                int legeMedNarkotiskUtskrift = 0;
                for (Resept r : l.hentResepter()) {
                    if (r.hentLegemiddel() instanceof PreparatA) {
                        legeMedNarkotiskUtskrift++;
                    }
                }
                if (legeMedNarkotiskUtskrift > 0) {
                    System.out.println(l.hentNavn() + " har skrevet ut " + legeMedNarkotiskUtskrift + " narkotiske resepter.");
                }
            }

            for (Pasient p : pasienter) {
                int pasMedNarkotiskResept = 0;

                for (Resept r : p.hentResepter()) {
                    if (r.hentLegemiddel() instanceof PreparatA) {
                        if (r.hentReit() > 0) {
                            pasMedNarkotiskResept++;
                        }
                    }
                }
                if (pasMedNarkotiskResept > 0) {
                    System.out.println(p.hentNavn() + " har " + pasMedNarkotiskResept + " gyldige resepter paa narkotiske legemidler.");
                }
            }

        }
    }
}
