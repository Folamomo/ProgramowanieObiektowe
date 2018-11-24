package zadania;

import javaIn.*;

public class LoginProgram {
    static private Login l1 = new Login("malik1234","hunter2");

    public static void main(String[] args) {
        System.out.println("Podaj login: ");
        String login = JIn.getString();
        System.out.println("Podaj hasło: ");
        String haslo = JIn.getString();
        if(l1.check(login, haslo)){
            System.out.println("Poprawne");
        } else {
            System.out.println("Niepoprawne");

        }

    }

}
