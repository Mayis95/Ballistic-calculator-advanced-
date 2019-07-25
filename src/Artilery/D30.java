package Artilery;
import Interfaces.Fireable;

import java.util.Vector;

public class D30 implements Fireable
{
    public double fire(double ang, double weight, double v0, double a, double b, Vector<Integer> xc, Vector<Integer> yc)
    {
        double vx,vy,fy,fx,f,t1=0.01,t=0,x=0,y=0,g=9.81;
        double angle=ang*Math.PI/180;
        vx=v0*Math.cos(angle);
        vy=v0*Math.sin(angle);
        do
        {
            f=-a*v0-b*v0*v0*v0;
            fx=f*vx/v0;
            vx=vx+fx*t1/weight;
            x=x+vx*t1;
            fy=f*vy/v0-weight*g;
            vy=vy+fy*t1/weight;
            y=y+vy*t1;
            t=t+t1;
            v0= Math.sqrt(vx * vx + vy * vy);
            xc.add((int)x);
            yc.add((int)y);
        }while (y>=0);
        return t;
    }
}
