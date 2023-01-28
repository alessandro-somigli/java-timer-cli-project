package org.cli.timer;

import org.cli.timer.utils.Utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class CLI {
    private final int progressBarLength = 50;
    private final Timer timer;

    private static final File startSoundFile = new File("./sound-effects/start-timer.wav").getAbsoluteFile();
    private static final File timerRingFile = new File("./sound-effects/timer-ring.wav").getAbsoluteFile();

    public CLI(Timer _timer) {
        timer = _timer;
            timer.setOnTimerTick(progress -> this.generate(progress, timer.getTime(), timer.getStatus()));
            timer.setOnDelayTick(progress -> this.generate(progress, timer.getDelay(), timer.getStatus()));

            timer.setOnPlayStartSound( () -> new Thread(() -> this.sound(startSoundFile)).start() );

            timer.setOnPlayRingSound(() -> new Thread(() -> this.sound(timerRingFile)).start() );
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

        Utils.print("Timer CLI"     + "\n\n" +
                _timerProgressLine  + '\n' +
                _timerProgressBar   + '\n'
        );
    }

    public void sound(File _file) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(_file));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (IOException | InterruptedException |
                 LineUnavailableException | UnsupportedAudioFileException e) { Utils.log(e.getMessage()); }
    }

    public void start() {
        timer.start();
    }

}
