import java.util.*;

class Lenkeliste<T> implements Liste<T> {

    class Node {
        Node neste;
        Node forrige;
        T data;


        public Node(T x) {
            data = x;
        }

        public Node(T data, Node neste) {
            this.data = data;
            this.neste = neste;
        }
    }

    class LenkelisteIterator implements Iterator<T> {
        private Node posisjon;

        public LenkelisteIterator() {
            posisjon = forste;
        }
        public boolean hasNext() {
            return posisjon != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T posData = posisjon.data;
            posisjon = posisjon.neste;
            return posData;

        }/*
        private Liste<T> liste;
		private int teller = 0;

		public LenkelisteIterator(Liste<T> lx) {
			liste = lx;
		}
		public boolean hasNext() {
			return teller < liste.stoerrelse();
		}
		public T next() {
			return liste.hent(teller++);
		}

        public void remove() {
            throw new UnsupportedOperationException();
        }*/
    }


    protected Node forste = null; //Forste node i listen
    protected Node siste = null;  //Siste node i listen
    protected int antallNoder = 0;

    public void flush() {
		antallNoder = 0;
		forste = null;
		siste = null;
	}

    public int stoerrelse() {
        return antallNoder;
    }

    public void leggTil(int pos, T x) {

        if (pos == 0) {
            forste = new Node(x, forste);
        } else {
            Node p = hentNode(pos - 1);
            p.neste = new Node(x, p.neste);
        }

        antallNoder++;
    }


    public void leggTil(T x) {

        Node ny = new Node(x);

        if (forste == null) {
            forste = ny;
        } else {
            Node p = forste;
            while (p.neste != null) {
                p = p.neste;
            }
            p.neste = ny;
        }

        antallNoder++;
    }



    public void sett(int pos, T x) {
        Node p = hentNode(pos);
        T gammelNode = p.data;
        p.data = x;

    }

    public T hent(int pos) {
        Node p = hentNode(pos);
        return p.data;
    }


    protected Node hentNode(int pos) {
        if (pos < 0 || pos >= antallNoder) {
            throw new UgyldigListeIndeks(pos);
        }

        Node p = forste;
        for (int i = 0; i < pos; i++) {
            p = p.neste;
        }

        return p;
    }


    public T fjern(int pos) {
        T fjernetData = hent(pos);
        if (pos == 0) {
            forste = forste.neste;
        } else {
            Node p = hentNode(pos - 1);
            p.neste = p.neste.neste;
        }



        antallNoder--;

        return fjernetData;
    }

    public T fjern() {
        T fjernet = null;

        if (antallNoder == 0) {
            throw new UgyldigListeIndeks(antallNoder);
        }
        if (stoerrelse() != 0) {
            fjernet = forste.data;
            forste = forste.neste;
        }

        antallNoder--;

        return fjernet;
    }

    public Iterator<T> iterator() {
        return new LenkelisteIterator();
    }

}
