package org.cli.timer;

import org.cli.timer.utils.Utils;

import java.util.Arrays;

// -t (time): time in seconds. default is 600
// -d (delay): defines how many seconds before running again. default is -1 (do not run again)
// -s (silent): weather to show a popup when finished. default is false
// example: -t 3600 -d 600 -s

public class Main {
    public static void main(String[] args) {
        int time = 600;
        int delay = -1;
        boolean silent = false;

        // parse arguments
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-t" -> time = Integer.parseInt(args[++i]);
                case "-d" -> delay = Integer.parseInt(args[++i]);
                case "-s" -> silent = true;
            }
        }

        new CLI( new Timer(time, delay, silent) ).start();
    }
}