package org.cli.timer;

import org.cli.timer.utils.Utils;

import java.io.IOException;

public class CLI {
    private final int progressBarLength = 50;
    private final Timer timer;

    public CLI(Timer _timer) {
        timer = _timer;

        timer.setOnTimerTick(progress -> this.generate(progress, timer.getTime(), timer.getStatus()));
        timer.setOnDelayTick(progress -> this.generate(progress, timer.getDelay(), timer.getStatus()));
    }

    public void generate(int _progress, int _max, String _status) {
        String timerProgressLine = ((_status.equals("TIMER"))? "Timer":"Delay") + " progress: " + _progress + " of " + _max;

        int progressChars = (int)(( (double)_progress/_max ) * progressBarLength);
        String timerProgressBarFill = new String(new char[progressChars]).replace('\0', '█');
        String timerProgressBarEmpty = new String(new char[progressBarLength-progressChars]).replace('\0', ' ');

        String timerProgressBar = '[' + timerProgressBarFill + timerProgressBarEmpty + ']';

        template(timerProgressLine, timerProgressBar);
    }

    // width: 54
    public void template(String _timerProgressLine, String _timerProgressBar) {
        // cls
        try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); }
        catch (InterruptedException | IOException e) { Utils.log(e.getMessage()); }

        Utils.print("Timer CLI"     + '\n' +
                _timerProgressLine  + '\n' +
                _timerProgressBar   + '\n'
        );
    }

    public void start() {
        timer.start();
    }

}
