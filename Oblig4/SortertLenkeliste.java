class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

    @Override
    public void leggTil(T x) {
        Node ny = new Node(x);

        if (antallNoder == 0) {
            forste = ny;
            siste = ny;
        }

        else if (x.compareTo(forste.data) < 0) {
            forste.forrige = ny;
            ny.neste = forste;
            forste = ny;
        }

        else if (x.compareTo(siste.data) > 0) {
            siste.neste = ny;
            ny.forrige = siste;
            siste = ny;
        }

        else {
            for (Node n = forste; n.neste != null; n = n.neste) {
                if (x.compareTo(n.neste.data) < 0) {
                    ny.neste = n.neste;
                    n.neste = ny;
                    break;
                }
            }
        }
        antallNoder++;
    }

    @Override
    public T fjern() {
        Node f = forste;
        Node forrige = forste;

        if (forste == null) {
            throw new UgyldigListeIndeks(antallNoder);
        }
        else if (forste.neste == null) {
            forste = null;
            siste = null;
        }
        else {
            while (f.neste != null) {
                forrige = f;
                f = f.neste;
            }
            forrige.neste = null;
            siste = forrige;
        }
        antallNoder--;
        return f.data;
    }


    @Override
    public void sett(int pos, T x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggTil(int pos, T x) {
        throw new UnsupportedOperationException();
    }
}
