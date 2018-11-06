package Kolokwium1;

import Kolokwium1.appl.StringCalculator;
import Kolokwium1.excp.TooBigNumberException;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class test {
    @Test
    public void TestAdd(){
        StringCalculator s = new StringCalculator("A");
        s.Add("B");
        assertEquals("AB", s.getResult());
    }
    @Test
    public void TestMultiply(){
        StringCalculator s = new StringCalculator("A");
        try {
            s.Multiply(3);
        } catch (TooBigNumberException e) {
            e.printStackTrace();
        }
        assertEquals("AAA", s.getResult());
    }
}
