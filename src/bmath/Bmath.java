package bmath;

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
}
