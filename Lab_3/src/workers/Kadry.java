package workers;

import java.util.LinkedList;
import java.util.List;

public class Kadry {
    private List<Pracownik> pracownicy= new LinkedList<>();
    public Pracownik znajdzPracownika(String PESEL){
        for (Pracownik p :
                pracownicy) {
            if (p.PESEL.equals(PESEL)){
                return p;
            }
        }
        return null;
    }

    public boolean usunPracownika(String PESEL){
        for (Pracownik p :
                pracownicy) {
            if (p.PESEL.equals(PESEL)){
                pracownicy.remove(p);
                return true;
            }
        }
        return false;
    }

    public Pracownik dodajPracownikaEtatowego(String PESEL){
        Pracownik p = new PracownikEtatowy();
        p.PESEL = PESEL;
        pracownicy.add(p);
        return p;
    }
    public Pracownik dodajStuenta(String PESEL){
        Pracownik p = new Student();
        p.PESEL = PESEL;
        pracownicy.add(p);
        return p;
    }
    public boolean ustawWynagrodzenieBrutto(String PESEL, double noweWynagrodzenie){
        Pracownik p = znajdzPracownika(PESEL);
        if (p == null) {
            return false;
        }
        else {
            p.wynagrodzenieBrutto = noweWynagrodzenie;
            return true;
        }
    }
    public double pobierzWynagrodzenieBrutto(String PESEL){
        Pracownik p = znajdzPracownika(PESEL);
        if (p == null) {
            return -1;
        }
        else {
            return p.wynagrodzenieBrutto;
        }
    }
    public double pobierzWynagrodzenieNetto(String PESEL){
        Pracownik p = znajdzPracownika(PESEL);
        if (p == null) {
            return -1;
        }
        else {
            return p.wynagrodzenieNetto();
        }
    }
}
