package bmath_test;

import bmath.Bmath;

import java.util.ArrayList;

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


    public static void sampleTester(int n){
        ArrayList<Integer> test = new ArrayList<>();

        for (int i = 0; i < n; i++){
            test.add(i);
        }

        ArrayList<Integer> res = Bmath.sampleGeneratorTester(test);
        System.out.println("[result function] the size is: " + res.size());

        System.out.print("[result function] and the elements are: ");
        for (int i : res){
            System.out.print(i + " ");
        }
    }
}
