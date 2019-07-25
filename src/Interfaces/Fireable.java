package Interfaces;

import java.util.Vector;

public interface Fireable
{
    public double fire(double ang, double weight, double v0, double a, double b, Vector<Integer> xc, Vector<Integer> yc);
}
