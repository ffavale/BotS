package logg;

import java.io.*;
import java.time.*;
import java.time.format.*;

public class Logg{

    private int verbosity;
    private String targetLogFileName;
    private String callerId;
    private static final boolean timeStampFileName = true;

    public Logg(String i_targetLogFileName, String i_callerId)
    {
        // set the verbosity to 1 as default
        this.setVerbosity(2);
        this.callerId = i_callerId;
        this.targetLogFileName = i_targetLogFileName + ".log";
        if (Logg.timeStampFileName)
        {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss_SSSS");
            String formattedDateTime = dateTime.format(dateTimeFormat);
            this.targetLogFileName = i_targetLogFileName + "-" + formattedDateTime + ".log";
        }
    }

    public int setVerbosity(int i_int)
    {
        // change verbosity if it is within an allowable range
        if (0 <= i_int && i_int <= 2)
            this.verbosity = i_int;

        return this.verbosity;
    }

    public void logMessage(String i_message, String i_callerId)
    {
        String tempCallerId = this.callerId;
        this.callerId = i_callerId;

        this.logMessage(i_message);

        this.callerId = tempCallerId;
    }

    public void logQuietMessage(String i_message, String i_callerId)
    {
        String tempCallerId = this.callerId;
        this.callerId = i_callerId;

        this.logQuietMessage(i_message);

        this.callerId = tempCallerId;
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
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
                String formattedDateTime = dateTime.format(dateTimeFormat);
                // compile message
                String message = "[" + formattedDateTime.toString() + "] " + i_message;
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
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
                String formattedDateTime = dateTime.format(dateTimeFormat);
                // compile message
                String message = "[" + formattedDateTime.toString() + "](" + callerId + ") " + i_message;
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
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
                String formattedDateTime = dateTime.format(dateTimeFormat);
                // compile message
                String message = "[" + formattedDateTime.toString() + "] " + i_message;
                // detatch thread to write message into file
                writer.setString(message);
                writer.start();
                break;
            }
            case 2:
            {
                // get LocalDateTime
                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
                String formattedDateTime = dateTime.format(dateTimeFormat);
                // compile message
                String message = "[" + formattedDateTime.toString() + "](" + callerId + ") " + i_message;
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
