package logg;

import java.io.*;
import java.time.*;
import java.time.format.*;

public class Logg{

    private String targetLogFileName;
    private String callerId;

    public Logg(String i_targetLogFileName, String i_callerId)
    {
        // TODO - Implement a buffered writing system to avoid waiting for disk
        this.callerId = i_callerId;
        this.targetLogFileName = i_targetLogFileName + ".log";
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss_SSSS");
        String formattedDateTime = dateTime.format(dateTimeFormat);
        this.targetLogFileName = i_targetLogFileName + "-" + formattedDateTime + ".log";
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
        // get LocalDateTime
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        String formattedDateTime = dateTime.format(dateTimeFormat);
        // compile message
        String message = "[" + formattedDateTime.toString() + "](" + callerId + ") " + i_message;
        System.out.println(message);
        // detatch thread to write message into file
        AsyncWriter writer = new AsyncWriter(message, this.targetLogFileName);
        writer.start();
    }

    public void logQuietMessage(String i_message)
    {
        // get LocalDateTime
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        String formattedDateTime = dateTime.format(dateTimeFormat);
        // compile message
        String message = "[" + formattedDateTime.toString() + "](" + callerId + ") " + i_message;
        // detatch thread to write message into file
        AsyncWriter writer = new AsyncWriter(message, this.targetLogFileName);
        writer.start();
    }
}

class AsyncWriter extends Thread
{
    private String printString = "";
    private String targetLogFileName = "orphanLog.txt";
    private static int logWrites = 0;
    private static final int maxLogWrites=2048;

    public AsyncWriter(String i_message, String i_targetLogFileName)
    {
        this.printString = i_message;
        this.targetLogFileName = i_targetLogFileName;
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
