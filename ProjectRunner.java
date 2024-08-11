package prj5;

import java.io.IOException;

// -------------------------------------------------------------------------
/**
 * This class runs the project
 * 
 * @author jdevi
 * @version Nov 16, 2023
 */
public class ProjectRunner
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    /**
     * This method takes an args string uses it if there are argumentsF inside
     * throws an io exception if needed
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args)
        throws IOException
    {
        @SuppressWarnings("unused")
        InputFileReader fileReader;
        if (args.length > 0)
        {
            fileReader = new InputFileReader(args[0]);
        }
        else
        {
            fileReader = new InputFileReader("SampleInput1_2023.csv");
        }

    }
}
