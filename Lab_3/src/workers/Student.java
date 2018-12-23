package workers;

public class Student extends Pracownik {
    @Override
    double wynagrodzenieNetto() {
        return 0; //student jest biedny.
    }
}
