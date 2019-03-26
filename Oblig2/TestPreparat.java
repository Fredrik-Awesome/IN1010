class TestPreparat {
    public static void main(String[] args) {
        //Oppretter objekter av hver klasse
        PreparatA prepA = new PreparatA("Preparat A", 10.0, 40.0, 5);
        PreparatB prepB = new PreparatB("Preparat B", 20.0, 30.0, 9);
        PreparatC prepC = new PreparatC("Preparat C", 20.0, 40.0);

        //Printer ut for å sjekke at alt stemmer med toString()
        System.out.println(prepA.toString());
        System.out.println();
        System.out.println(prepB.toString());
        System.out.println();
        System.out.println(prepC.toString());
        System.out.println();

        //Tester metodene hentNarkotiskStyrke() og hentVanedannendeStyrke()
        System.out.println(prepA.hentNarkotiskStyrke());
        System.out.println();
        System.out.println(prepB.hentVanedannendeStyrke());

        //Setter ny pris til prepA, utskrift skal nå vise 20 istedenfor 10
        prepA.settNyPris(20);
        System.out.println();
        System.out.println(prepA.toString());
    }
}
