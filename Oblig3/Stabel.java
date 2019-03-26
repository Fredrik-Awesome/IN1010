class Stabel<T> extends Lenkeliste<T>{
    public void leggPaa(T x) {
        super.leggTil(x);
    }

    public T taAv() {
        int indeks = antallNoder - 1;
        return super.fjern(indeks);
    }
}
