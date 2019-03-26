class Integrasjonstest {
    public static void main(String[] args) {

        //Bruker verdier fra tidligere tester
        PreparatA prepA = new PreparatA("Morfin", 500.0, 75.0, 20);
        PreparatB prepB = new PreparatB("Nesespray", 100.0, 20.0, 10);
        PreparatC prepC = new PreparatC("Adenosin", 50.0, 15.0);
        Lege arne = new Lege("Arne");
        Spesialist frank = new Spesialist("Frank", 17230);
        Resept pRes = new Presept(prepB, arne, 1234, 3);
        Resept mil = new MilitaerResept(prepC, arne, 5678, 1);
        Resept blaa = new BlaaResept(prepC, frank, 9101, 5);
        Resept hvit = new HvitResept(prepC, frank, 1121, 3);

        //Printer info om preparatene
        System.out.println(prepA.toString());
        System.out.println();
        System.out.println(prepB.toString());
        System.out.println();
        System.out.println(prepC.toString());
        System.out.println();

        //Kopierer inn test fra tidligere program
        boolean sjekkStatus = true;
        if (blaa.hentId() != 2 || pRes.hentId() != 0 || mil.hentId() != 1 || hvit.hentId() != 3) {
            sjekkStatus = false;
            System.out.println("hentId fungerer ikke, skulle vært 2-0-1-3, fikk " +
            blaa.hentId() + " - " + pRes.hentId() + " - " + mil.hentId() + " - " + hvit.hentId());
        }

        if (blaa.hentLegemiddel() != prepC || pRes.hentLegemiddel() != prepB || mil.hentLegemiddel() != prepC || hvit.hentLegemiddel() != prepC) {
            sjekkStatus = false;
            System.out.println("hentLegemiddel fungerer ikke, skulle vært Adenosin-Nesespray-Adenosin-Adenosin, fikk " +
            blaa.hentLegemiddel() + " - " + pRes.hentLegemiddel() + " - " + mil.hentLegemiddel() + " - " + hvit.hentLegemiddel());
        }

        if (blaa.hentLege() != frank || pRes.hentLege() != arne || mil.hentLege() != arne || hvit.hentLege() != frank) {
            sjekkStatus = false;
            System.out.println("hentId fungerer ikke, skulle vært Frank-Arne-Arne-Frank, fikk " +
            blaa.hentLege() + " - " + pRes.hentLege() + " - " + mil.hentLege() + " - " + hvit.hentLege());
        }

        if (blaa.hentPasientId() != 9101 || pRes.hentPasientId() != 1234 || mil.hentPasientId() != 5678 || hvit.hentPasientId() != 1121) {
            sjekkStatus = false;
            System.out.println("hentPasientId() fungerer ikke, skulle vært 9101-1234-5678-1121, fikk " +
            blaa.hentPasientId() + " - " + pRes.hentPasientId() + " - " + mil.hentPasientId() + " - " + hvit.hentPasientId());
        }

        if (blaa.hentReit() != 5 || pRes.hentReit() != 3 || mil.hentReit() != 1 || hvit.hentReit() != 3) {
            sjekkStatus = false;
            System.out.println("hentReit() fungerer ikke, skulle vært 5-8-1-3, fikk " +
            blaa.hentReit() + " - " + pRes.hentReit() + " - " + mil.hentReit() + " - " + hvit.hentReit());
        }

        blaa.bruk();
        pRes.bruk();
        mil.bruk();
        hvit.bruk();

        if (blaa.hentReit() != 4 || pRes.hentReit() != 2 || mil.hentReit() != 0 || hvit.hentReit() != 2) {
            sjekkStatus = false;
            System.out.println("hentReit() fungerer ikke, skulle vært 4-7-0-2, fikk " +
            blaa.hentReit() + " - " + pRes.hentReit() + " - " + mil.hentReit() + " - " + hvit.hentReit());
        }

        if (blaa.farge() != "Blaa" || pRes.farge() != "Hvit" || mil.farge() != "Hvit" || hvit.farge() != "Hvit") {
            sjekkStatus = false;
            System.out.println("farge() fungerer ikke, skulle vært Blaa-Hvit-Hvit-Hvit, fikk " +
            blaa.farge() + " - " + pRes.farge() + " - " + mil.farge() + " - " + hvit.farge());
        }

        if (blaa.prisAaBetale() != 12.5 || pRes.prisAaBetale() != 0 || mil.prisAaBetale() != 0 || hvit.prisAaBetale() != 50) {
            sjekkStatus = false;
            System.out.println("prisAaBetale() fungerer ikke, skulle vært 12.5-0-0-50, fikk " +
            blaa.prisAaBetale() + " - " +  pRes.prisAaBetale() + " - " + mil.prisAaBetale() + " - " + hvit.prisAaBetale());
        }
        if (sjekkStatus) {
            System.out.println("Alle sjekker av resepter er ok");
        }else {
            System.out.println("Feil i programmet, se feilmelding(er) over.");
        }

        System.out.println();
        //Tester hentNavn() for legene
        System.out.println(arne.toString());
        System.out.println(frank.toString());

        System.out.println();


        //Sjekker skrivResept()
        try{
            System.out.println(frank.skrivResept(prepA, 7382, 5));
        } catch (UlovligUtskrift e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try{
            System.out.println(arne.skrivResept(prepB, 6572, 10));
        } catch (UlovligUtskrift e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        try{
            System.out.println(arne.skrivResept(prepA, 3829, 3));
        } catch (UlovligUtskrift e) {
            System.out.println(e.getMessage());
        }


    }
}
