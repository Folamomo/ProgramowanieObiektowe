package Kolokwium1.appl;

import Kolokwium1.Calculator;
import Kolokwium1.excp.BadInputException;
import Kolokwium1.excp.NothingToSubtractException;
import Kolokwium1.excp.TooBigNumberException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StringCalculator extends Calculator implements Cloneable{
    public static void main(String[] Args){
        StringCalculator calculator = new StringCalculator("Lorem");
        calculator.Add(" impsum dolor sit amet ");
        try {
            calculator.Multiply(7);
        } catch (TooBigNumberException e) {
            e.printStackTrace();
        }
        try {
            calculator.Multiply(3);
        } catch (TooBigNumberException e) {
            e.printStackTrace();
        }

        try {
            calculator.Subtract("Nie ma takiego slowa");
        } catch (NothingToSubtractException e) {
            e.printStackTrace();
        } catch (BadInputException e) {
            e.printStackTrace();
        }
        calculator.SaveToFile("x", "1.txt");
        try {
            calculator.Subtract("Lorem impsum dolor sit amet");
        } catch (NothingToSubtractException e) {
            e.printStackTrace();
        } catch (BadInputException e) {
            e.printStackTrace();
        }

        try {
            calculator.Subtract("Result powinien być pusty");
        } catch (NothingToSubtractException e) {
            e.printStackTrace();
        } catch (BadInputException e) {
            e.printStackTrace();
        }

        calculator.SaveToFile("x", "2.txt");
    }



    private String result = new String();

    public StringCalculator(String wPoczatkowa){
        result = wPoczatkowa;
    }
    public StringCalculator(){}
    public  void SaveToFile(String x, String filename){
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  void Add (String s){
        result=result.concat(s);
    }
    public  void Subtract (String s1) throws NothingToSubtractException, BadInputException {
        if (result.equals("") )throw new NothingToSubtractException("result jest pusty");
        if(!result.contains(s1)) throw new BadInputException("result nie zawiera: ".concat(s1));
        result = result.replaceAll(s1, "");
    }
    public  void Multiply (int x) throws TooBigNumberException {
        if (x>5) throw new TooBigNumberException("5<x");
        String tmp = result;
        for (int i=0; i<x; ++i){
            result = result.concat(tmp);
        }
    }

}
