package workers;

public class PracownikEtatowy extends Pracownik {
    @Override
    double wynagrodzenieNetto() {
        return wynagrodzenieBrutto*0.6;
    }
}
