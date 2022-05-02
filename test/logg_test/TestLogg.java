package logg_test;

import logg.*;

public class TestLogg
{
    public static void setVerbosityTest()
    {
        System.out.println("Testing verbosity setting...");
        Logg log = new Logg("testlogfile.txt", "setVerbosityTest");
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

    public static void doLogMessageTest()
    {
        System.out.println("Testing writing a log at verbosity 1...");
        Logg testlog = new Logg("testlogfile", "setVerbosityTest");
        testlog.logMessage("Hello World!");
        System.out.println("Setting verbosity to 0 and logging a message...");
        testlog.setVerbosity(0);
        testlog.logMessage("This is a non verbose message");
        System.out.println("Setting verbosity to 2 and logging a message...");
        testlog.setVerbosity(2);
        testlog.logMessage("This is a very verbose message");

        System.out.println("");
        System.out.println("");
    }

    public static void intensiveWriteTest()
    {
        System.out.println("Testing the ability for the program to deal with a slow disk...");
        Logg testlog = new Logg("testlogfile_intensive", "intensiveWriteTest");
        testlog.setVerbosity(2);
        int n2 = 24;
        // int n2 = 100;
        for (int i = 0; i < n2; i++)
        {
            testlog.logQuietMessage("This is the log message for i = " + String.valueOf(i));

            for (int j = 0; j < n2; j++)
            {
                testlog.logQuietMessage("This is the log message for j = " + String.valueOf(j));

                for (int k = 0; k < n2; k++)
                {
                    testlog.logQuietMessage("This is the log message for k = " + String.valueOf(k));
                }
            }
        }
        System.out.println("Finished instructing the log");
        System.out.println("Async logging works (checked with floppy)");

        System.out.println("");
        System.out.println("");
    }

    public static void logWithBetterCallerId()
    {
        System.out.println("Calling the log but by changing caller id");

        Logg testlog = new Logg("testlogfile_callerIdSwitch", "logWithBetterCallerId");
        testlog.setVerbosity(2);

        testlog.logMessage("This is a log with the standard callerId");
        testlog.logQuietMessage("This is a quiet log with the standard callerId");

        testlog.logMessage("This is a log with the \"newCallerId\" callerId", "newCallerId");
        testlog.logQuietMessage("This is a quiet log with the \"newCallerId\" callerId", "newCallerId");

        testlog.logMessage("This is a log with the standard callerId checking if it has returned to normal");
        testlog.logQuietMessage("This is a quiet log with the standard callerId checking if it has returned to normal");

        System.out.println("");
        System.out.println("");
    }
}
