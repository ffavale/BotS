package bots_test;

import bots.Main;
import bmath_test.*;
import logg_test.*;
import bots_test.simulation_test.*;

public class TestMain{

    /* Tests start here */
    public static void main(String args[])
    {
        BmathTest xbar = new BmathTest();
        xbar.meanOfDoubleArrayTest();
        xbar.sdOfDoubleArrayTest();

        System.out.println("TESTING SPLASH SCREEN");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("=====================================================================================");
        Main.SplashScreen();
        System.out.println("=====================================================================================");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("---------------------");
        System.out.println("");
        System.out.println("");

        TestLogg.doLogMessageTest();

        TestLogg.intensiveWriteTest();

        TestLogg.logWithBetterCallerId();

        TestSimulation.populationInfoTest();
    }
}
