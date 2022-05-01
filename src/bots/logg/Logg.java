package bots.logg;

import java.io.*;

public class Logg{

    private int verbosity;
    protected String targetLogFileName;

    public Logg(String i_targetLogFileName)
    {
        // set the verbosity to 1 as default
        this.setVerbosity(1);
        this.targetLogFileName = i_targetLogFileName;
    }

    public int setVerbosity(int i_int)
    {
        // change verbosity if it is within an allowable range
        if (0 <= i_int && i_int <= 2)
            this.verbosity = i_int;

        return this.verbosity;
    }

    public int logMessage(String[] i_message)
    {
        switch (this.verbosity)
        {
            case 0:
            {

                break;
            }
            case 1:
            {

                break;
            }
            case 2:
            {

                break;
            }
        }
        return 0;
    }
}
