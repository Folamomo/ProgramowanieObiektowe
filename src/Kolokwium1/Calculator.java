package Kolokwium1;

import Kolokwium1.excp.BadInputException;
import Kolokwium1.excp.NothingToSubtractException;
import Kolokwium1.excp.TooBigNumberException;

public abstract class Calculator {
    public abstract void SaveToFile(String x, String filename);
    public abstract void Add (String s);
    public abstract void Subtract (String s1) throws NothingToSubtractException, BadInputException;
    public abstract void Multiply (int x) throws TooBigNumberException;
}
