package org.cli.timer;

import org.cli.timer.utils.Utils;
import org.jetbrains.annotations.NotNull;

// -t (time): time in seconds. default is 600
// -d (delay): defines how many seconds before running again. default is -1 (do not run again)
// -s (silent): weather to show a popup when finished. default is false
// example: -t 3600 -d 600 -s

public class Main {
    public static void main(String @NotNull [] args) {
        int time = 600;
        int delay = -1;
        boolean silent = false;

        // parse arguments
        String error = "";
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-t" -> {
                    try {
                        time = Integer.parseInt(args[++i]);
                    } catch (Exception e) { error = "error: " + args[i-1] + " requires a number."; }
                }
                case "-d" -> {
                    try {
                        delay = Integer.parseInt(args[++i]);
                    } catch (Exception e) { error = "error: " + args[i-1] + " requires a number."; }
                }
                case "-s" -> silent = true;
                default -> error = "error: " + args[i] + " is not a valid argument.";
            }
        }

        if (error.equals("")) new CLI( new Timer(time, delay, silent) ).start();
        else Utils.print(error);
    }
}