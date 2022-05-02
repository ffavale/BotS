package logg;

import java.io.*;
import java.time.*;

public class Logg{

    private int verbosity;
    protected String targetLogFileName;
    private String callerId;

    public Logg(String i_targetLogFileName, String i_callerId)
    {
        // set the verbosity to 1 as default
        this.setVerbosity(1);
        this.callerId = i_callerId;
        this.targetLogFileName = i_targetLogFileName;
    }

    public int setVerbosity(int i_int)
    {
        // change verbosity if it is within an allowable range
        if (0 <= i_int && i_int <= 2)
            this.verbosity = i_int;

        return this.verbosity;
    }

    public void logMessage(String i_message)
    {
        AsyncWriter writer = new AsyncWriter(this.targetLogFileName);
        switch (this.verbosity)
        {
            case 0:
            {
                // simply dump the message into the file
                writer.setString(i_message);
                System.out.println(i_message);
                writer.start();
                break;
            }
            case 1:
            {
                // get LocalDateTime
                LocalDateTime dateTime = LocalDateTime.now();
                // compile message
                String message = "[" + dateTime.toString() + "] " + i_message;
                System.out.println(message);
                // detatch thread to write message into file
                writer.setString(message);
                writer.start();
                break;
            }
            case 2:
            {
                // get LocalDateTime
                LocalDateTime dateTime = LocalDateTime.now();
                // compile message
                String message = "[" + dateTime.toString() + "](" + callerId + ") " + i_message;
                System.out.println(message);
                // detatch thread to write message into file
                writer.setString(message);
                writer.start();
                break;
            }
        }
    }

    public void logQuietMessage(String i_message)
    {
        AsyncWriter writer = new AsyncWriter(this.targetLogFileName);
        switch (this.verbosity)
        {
            case 0:
            {
                // simply dump the message into the file
                writer.setString(i_message);
                writer.start();
                break;
            }
            case 1:
            {
                // get LocalDateTime
                LocalDateTime dateTime = LocalDateTime.now();
                // compile message
                String message = "[" + dateTime.toString() + "] " + i_message;
                // detatch thread to write message into file
                writer.setString(message);
                writer.start();
                break;
            }
            case 2:
            {
                // get LocalDateTime
                LocalDateTime dateTime = LocalDateTime.now();
                // compile message
                String message = "[" + dateTime.toString() + "](" + callerId + ") " + i_message;
                // detatch thread to write message into file
                writer.setString(message);
                writer.start();
                break;
            }
        }
    }
}

class AsyncWriter extends Thread
{
    private String printString = "";
    private String targetLogFileName = "orphanLog.txt";
    private static int logWrites = 0;
    private static final int maxLogWrites=2048;

    public AsyncWriter(String i_targetLogFileName)
    {
        this.targetLogFileName = i_targetLogFileName;
    }

    public void setString(String i_printString)
    {
        this.printString = i_printString;
    }

    @Override
    public void run()
    {
        try
        {
            while (AsyncWriter.logWrites >= AsyncWriter.maxLogWrites)
            {
                try{
                    Thread.sleep(10);
                } catch (Exception e)
                {}
            }
            AsyncWriter.logWrites++;
            FileWriter writer = new FileWriter(this.targetLogFileName, true);

            writer.write(printString + "\n");
            writer.close();
            AsyncWriter.logWrites--;

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
