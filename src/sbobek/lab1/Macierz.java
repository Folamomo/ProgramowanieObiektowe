package sbobek.lab1;

import java.security.InvalidAlgorithmParameterException;

public class Macierz {

    public int [][] data;

    public int wysokosc(){
        return data.length;
    }
    public int szeroskosc(){
        return data[0].length;
    }

    Macierz (int [][] _data){
        data = new int [_data.length][_data[0].length];
        for (int i = 0; i<wysokosc(); ++i){
            System.arraycopy(_data[i], 0, data[i], 0, szeroskosc());
        }
    }
    Macierz (int wysokosc, int szerokosc){
        data = new int [wysokosc][szerokosc];
    }
    Macierz add (Macierz inna){
        if (wysokosc()!=inna.wysokosc()||szeroskosc()!=inna.szeroskosc()){
            System.out.println("Zły wymiar macierzy");
            return new Macierz(1,1);
        }
        Macierz wynik = new Macierz(wysokosc(), szeroskosc());
        for (int i = 0; i<wysokosc(); ++i){
            for (int j = 0; j<szeroskosc(); ++j) {
                wynik.data[i][j]=data[i][j]+inna.data[i][j];
            }
        }
        return wynik;
    }

    Macierz sub (Macierz inna){
        if (wysokosc()!=inna.wysokosc()||szeroskosc()!=inna.szeroskosc()){
            System.out.println("Zły wymiar macierzy");
            return new Macierz(1,1);
        }
        Macierz wynik = new Macierz(data.length, data[0].length);
        for (int i = 0; i<wysokosc(); ++i){
            for (int j = 0; j<szeroskosc(); ++j) {
                wynik.data[i][j]=data[i][j]-inna.data[i][j];
            }
        }
        return wynik;
    }

    Macierz mul (Macierz inna){
        if (szeroskosc()!=inna.wysokosc()){
            System.out.println("Zły wymiar macierzy");
            return new Macierz(1,1);
        }
        Macierz wynik = new Macierz(wysokosc(), inna.szeroskosc());
        for (int i = 0; i<wynik.wysokosc(); ++i){
            for (int j = 0; j<wynik.szeroskosc(); ++j) {
                wynik.data[i][j]=0;
                for (int k=0; k<wysokosc();++k){
                    wynik.data[i][j]+=data[i][k]*inna.data[k][j];
                }
            }
        }
        return wynik;
    }

    Macierz mul (int stala){
        Macierz wynik = new Macierz(wysokosc(), szeroskosc());
        for (int i = 0; i<wysokosc(); ++i){
            for (int j = 0; j<szeroskosc(); ++j) {
                wynik.data[i][j]=data[i][j]*stala;
            }
        }
        return wynik;
    }

    void print (){
        for (int i=0; i<wysokosc();++i){
            System.out.print("[");
            for (int j=0;j<szeroskosc();){
                System.out.print(data[i][j]);
                if (++j!=szeroskosc()){
                    System.out.print("; ");
                }
            }
            System.out.println("]");
        }
    }
}
