package bots_test;

import bots.Main;
import bots_test.logg_test.*;

public class TestMain{

    /* Tests start here */
    public static void main(String args[])
    {
        System.out.println("TESTING SPLASH SCREEN");
        Main.SplashScreen();
        System.out.println("---------------------");
        System.out.println("");
        System.out.println("");

        TestLogg.setVerbosityTest();
    }
}
