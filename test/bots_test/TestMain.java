package bots_test;
import bots.simulation.*;
import bots.Main;
import bmath_test.*;
import logg_test.*;
import logg.Logg;
import bots_test.simulation_test.*;

public class TestMain{

    /* Tests start here */
    public static void main(String args[])
    {
        Logg log = new Logg("mainTest", "mainTest");
        System.out.println("TESTING SPLASH SCREEN");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("=====================================================================================");
        Main.splashScreen(log);
        System.out.println("=====================================================================================");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("---------------------");
        System.out.println("");
        System.out.println("");

        TestLogg.doLogMessageTest();
        BmathTest xbar = new BmathTest();
        xbar.meanOfDoubleArrayTest();
        xbar.sdOfDoubleArrayTest();
        System.out.println("");
        System.out.println("");

        TestLogg.doLogMessageTest();

        TestLogg.intensiveWriteTest();

        TestLogg.logWithBetterCallerId();

        TestSimulation.populationInfoTest();

        TestEvent.eventCreation();

        TestEvent.universalEventCreation();

        BmathTest.sampleTester(100);
        System.out.println();
        System.out.println();

        TestEvent.testCoupleCreation();

        TestSimulation.universalityTest();
    }
}
