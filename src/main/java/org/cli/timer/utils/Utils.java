package org.cli.timer.utils;

import java.io.*;

public class Utils {
    private static final File log = new File("log.txt");

    private static final BufferedWriter consoleWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final BufferedWriter logWriter;

    static {
        try {
            if ( !log.createNewFile() ) new FileWriter(log).close();
            logWriter = new BufferedWriter(new FileWriter(log, true));
        } catch (IOException e) { throw new RuntimeException(e); }
    }

    public static void print(String _string) {
        try {
            consoleWriter.write(_string + '\n');
            consoleWriter.flush();
        } catch (NullPointerException e) { Utils.log("exception: tried to print null value. message: " + e.getMessage()); }
        catch (IOException e) { Utils.log("exception: IO exception occurred; " +  e.getMessage()); }
    }

    public static void log(String _string) {
        try {
            logWriter.write(_string + '\n');
            logWriter.flush();
        } catch (NullPointerException | IOException e) { e.printStackTrace(); }
    }
}
