package bots_test.logg_test;

import bots.logg.*;

public class TestLogg
{
    public static void setVerbosityTest()
    {
        System.out.println("Testing verbosity setting...");
        Logg log = new Logg("");
        System.out.println("Log verbosity defaults to " + log.setVerbosity(-1));
        log.setVerbosity(2);
        System.out.println("Changed log verbosity to " + log.setVerbosity(-1));
        log.setVerbosity(0);
        System.out.println("Changed log verbosity to " + log.setVerbosity(-1));
        log.setVerbosity(1);
        System.out.println("Changed log verbosity to " + log.setVerbosity(-1));
        System.out.println("Expected output: 1, 2, 0, 1");
        System.out.println("");
        System.out.println("");
    }
}
