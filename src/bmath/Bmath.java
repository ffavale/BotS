package bmath;

import bots.simulation.*;
import java.util.ArrayList;

public class Bmath {
    public static double meanOfDoubleArray (double[] arrayofdouble) {
        double sum = 0;
        int i ;
        for (i = 0; i < arrayofdouble.length; i++){
            sum = sum + arrayofdouble[i];
        }
        double mean = sum/i;
        return mean;
    }
    public static double standardDeviation (double[] arrayofdouble){
        double mean = meanOfDoubleArray(arrayofdouble);
        int i;
        double var = 0;
        for (i = 0; i < arrayofdouble.length; i++){
            var = var + Math.pow((arrayofdouble[i]-mean),2);
        }
        double sd = Math.sqrt(var/(i-1));
        return sd;
    }

    public static ArrayList<Simulation.Snapshot> sampleGenerator(ArrayList<Simulation.Snapshot> bigData){
        ArrayList<Simulation.Snapshot> samples = new ArrayList<>();
        for (int i = (int) Math.round((0.375)* bigData.size()); i < (int) Math.round((0.3875)* bigData.size()); i++){
            samples.add(bigData.get(i));
        }
        for (int i = (int) Math.round((0.6375)* bigData.size()); i < (int) Math.round((0.6875)* bigData.size()); i++){
            samples.add(bigData.get(i));
        }
        for (int i = (int) Math.round((0.8125)* bigData.size()); i < bigData.size(); i++){
            samples.add(bigData.get(i));
        }
        return samples;
    }

    public static ArrayList<Integer> sampleGeneratorTester(ArrayList<Integer> bigData){
        ArrayList<Integer> samples = new ArrayList<>();
        for (int i = (int) Math.round((0.375)* bigData.size()); i < (int) Math.round((0.3875)* bigData.size()); i++){
            samples.add(i);
        }
        for (int i = (int) Math.round((0.6375)* bigData.size()); i < (int) Math.round((0.6875)* bigData.size()); i++){
            samples.add(i);
        }
        for (int i = (int) Math.round((0.8125)* bigData.size()); i < bigData.size(); i++){
            samples.add(i);
        }
        return samples;
    }

}
