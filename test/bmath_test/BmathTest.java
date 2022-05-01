package bmath_test;

import bots.bmath.Bmath;

public class BmathTest {
    public static void meanOfDoubleArrayTest(){
        double[] arrayofdouble = {1.0,1.0,1.0};
        double mean = Bmath.meanOfDoubleArray(arrayofdouble);
        System.out.println(mean);
        arrayofdouble = new double[]{1.0,3.0,5.0,7.0};
        mean = Bmath.meanOfDoubleArray(arrayofdouble);
        System.out.println(mean);
    }
    public static void sdOfDoubleArrayTest(){
        double[] arrayofdouble = {1.0,3.0,5.0,7.0};
        double sd = Bmath.standardDeviation(arrayofdouble);
        System.out.println(sd);
    }
}
