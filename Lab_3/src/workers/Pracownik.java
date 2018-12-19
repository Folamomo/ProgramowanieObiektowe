package workers;


public abstract class Pracownik {
    public String PESEL;
    public double wynagrodzenieBrutto;
    abstract double wynagrodzenieNetto();
}
