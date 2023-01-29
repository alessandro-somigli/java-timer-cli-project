package org.cli.timer.utils;

import org.cli.timer.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

    public static File getFile(String _path, String _directory, String _filename) {
        try {
            Files.createDirectories(Paths.get(_directory));
            InputStream inputStream = Main.class.getResourceAsStream(_path);
            File file = new File(_directory + _filename);

            if (inputStream != null) Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return file;
        } catch (IOException e) { Utils.log(e.getMessage()); }
        return null;
    }
}
