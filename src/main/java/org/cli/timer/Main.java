package org.cli.timer;

import java.util.Arrays;

// -t (time): time in seconds. default is 600
// -d (delay): defines how many seconds before running again. default is -1 (do not run again)
// -s (silent): weather to show a popup when finished. default is false
// example: -t 3600 -d 600 -s

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        System.out.println("");
    }
}