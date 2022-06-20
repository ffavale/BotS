package logg;

import java.io.*;
import java.time.*;
import java.time.format.*;

public class Logg{

    private static final int writeBufferSize = 10000;
    private String targetLogFileName;
    private String callerId;
    private int bufferPtr = 0;
    private String[] writeBuffer = new String[writeBufferSize];

    public Logg(String i_targetLogFileName, String i_callerId)
    {
        this.callerId = i_callerId;
        this.targetLogFileName = i_targetLogFileName + ".log";
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss_SSSS");
        String formattedDateTime = dateTime.format(dateTimeFormat);
        this.targetLogFileName = i_targetLogFileName + "-" + formattedDateTime + ".log";
    }

    public void finalize()
    {
        this.forceFlush();
    }

    public void forceFlush()
    {
        String[] smallBuffer = new String[bufferPtr];
        for (int i = 0; i < bufferPtr; i++)
        {
            smallBuffer[i] = this.writeBuffer[i];
        }
        this.writeBuffer = smallBuffer;
        this.flush();
        this.writeBuffer = new String[writeBufferSize];
    }

    private void flush()
    {
        String messageBundle = "";
        for (int i = 0; i < this.bufferPtr; i++)
        {
            messageBundle = messageBundle + writeBuffer[i] + "\n";
        }
        // detatch thread to write message into file
        AsyncWriter writer = new AsyncWriter(messageBundle, this.targetLogFileName);
        writer.start();
        this.bufferPtr = 0;
    }

    private void flushIfFull()
    {
        if (this.bufferPtr == this.writeBuffer.length)
        {
            this.flush();
        }
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

    public synchronized void logMessage(String i_message)
    {
        // get LocalDateTime
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        String formattedDateTime = dateTime.format(dateTimeFormat);
        // compile message
        String message = "[" + formattedDateTime.toString() + "](" + callerId + ") " + i_message;
        System.out.println(message);

        this.writeBuffer[bufferPtr] = message;
        bufferPtr++;

        this.flushIfFull();
    }

    public synchronized void logQuietMessage(String i_message)
    {
        // get LocalDateTime
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        String formattedDateTime = dateTime.format(dateTimeFormat);
        // compile message
        String message = "[" + formattedDateTime.toString() + "](" + callerId + ") " + i_message;

        this.writeBuffer[bufferPtr] = message;
        bufferPtr++;

        this.flushIfFull();
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

            writer.write(printString);
            writer.close();
            AsyncWriter.logWrites--;

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
