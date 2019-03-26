class TestResepter {
    public static void main(String[] args) {
        Legemiddel prepA = new PreparatA("Morfin", 500.0, 75.0, 20);
        Legemiddel prepB = new PreparatB("Nesespray", 100.0, 20.0, 10);
        Legemiddel prepC = new PreparatC("Adenosin", 50.0, 15.0);
        Lege arne = new Lege("Arne");
        Lege frank = new Lege("Frank");
        Resept pRes = new Presept(prepB, arne, 5678, 8);
        Resept mil = new MilitaerResept(prepC, arne, 9101, 1);
        Resept blaa = new BlaaResept(prepC, frank, 1234, 5);
        Resept hvit = new HvitResept(prepC, frank, 1121, 3);

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

        if (blaa.hentPasientId() != 1234 || pRes.hentPasientId() != 5678 || mil.hentPasientId() != 9101 || hvit.hentPasientId() != 1121) {
            sjekkStatus = false;
            System.out.println("hentPasientId() fungerer ikke, skulle vært 1234-5678-9101-1121, fikk " +
            blaa.hentPasientId() + " - " + pRes.hentPasientId() + " - " + mil.hentPasientId() + " - " + hvit.hentPasientId());
        }

        if (blaa.hentReit() != 5 || pRes.hentReit() != 8 || mil.hentReit() != 1 || hvit.hentReit() != 3) {
            sjekkStatus = false;
            System.out.println("hentReit() fungerer ikke, skulle vært 5-8-1-3, fikk " +
            blaa.hentReit() + " - " + pRes.hentReit() + " - " + mil.hentReit() + " - " + hvit.hentReit());
        }

        blaa.bruk();
        pRes.bruk();
        mil.bruk();
        hvit.bruk();

        if (blaa.hentReit() != 4 || pRes.hentReit() != 7 || mil.hentReit() != 0 || hvit.hentReit() != 2) {
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
            System.out.println("Alt er ok :)");
        }else {
            System.out.println("Feil i programmet, se feilmelding(er) over.");
        }
    }
}
