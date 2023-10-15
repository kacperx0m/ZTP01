package com.example.singleton;

// interfejs mówi co może zrobić ale nie jak to zrobić
// narzuca pewne zasady
interface IPolaczenie {
    char get(int indeks);
    void set(int indeks, char c);
    int length();
}

// Singleton
class Baza {
    private char[] tab = new char[100]; /* ... */

    private static Baza instance = null;
    // konstruktor definiuje co się dzieje po utworzeniu obiektu klasy
    private Baza() {}

    public static Baza getInstance() {
        if (instance == null) {
            // new instancjuje - tworzy nowy obiekt przez alokowanie pamięci
            instance = new Baza();
            // Baza() odwołuje się do konstruktora który inicjalizuje nowy obiekt (przypisuje wartości)
        }
        return instance;
    }

    public static IPolaczenie getPolaczenie() {
        return Polaczenie.getInstance();
    }

    // baza umożliwia dostęp przez Polaczenie
    // Multiton
    private static class Polaczenie implements IPolaczenie {
        private static Baza baza; /* ... */
        private static int nr_polaczenia = 0;
        // cos z obiektami zeby nullami byly na poczatku moge pokombinowac
        private static Polaczenie polaczenia[] = {null, null, null};

        private Polaczenie() {}

        public static IPolaczenie getInstance() {
            nr_polaczenia = (nr_polaczenia + 1) % 3;
            // cos z tym zeby nie zmieniac domyslnego kodu
            baza = Baza.getInstance();
            if(polaczenia[nr_polaczenia] == null) {
                polaczenia[nr_polaczenia] = new Polaczenie();
            }
            return polaczenia[nr_polaczenia];
        }

        public char get(int indeks) {
            return baza.tab[indeks];
        }

        public void set(int indeks, char c) {
            baza.tab[indeks] = c;
        }

        public int length() {
            return baza.tab.length;
        }
    }
}


public class Singleton {
    public static void main(String[] args) {

        //
        IPolaczenie polaczenia[] = new IPolaczenie[4];
        polaczenia[0] = Baza.getPolaczenie(); //baza 1
        polaczenia[1] = Baza.getPolaczenie(); //baza 2
        polaczenia[2] = Baza.getPolaczenie(); //baza 3
        polaczenia[3] = Baza.getPolaczenie(); //baza 1

        // to moge sobie odpuscic
//        polaczenia[0].set(0,'a');
//        polaczenia[1].set(1,'b');
//        polaczenia[2].set(2,'c');
//        polaczenia[3].set(3,'d');
//
//        System.out.println(polaczenia[0].get(0));
//        System.out.println(polaczenia[1].get(1));
//        System.out.println(polaczenia[0].get(2));
//        System.out.println(polaczenia[0].get(3));

        System.out.println(polaczenia[0] == polaczenia[1]);
        System.out.println(polaczenia[0] == polaczenia[2]);
        System.out.println(polaczenia[1] == polaczenia[2]);
        System.out.println(polaczenia[0] == polaczenia[3]);
    }
}
