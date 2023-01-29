package org.cli.timer;

import org.cli.timer.utils.AsciiArt;
import org.cli.timer.utils.Utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class CLI {
    private final int progressBarLength = 100;
    private final Timer timer;

    private static final File startSoundFile = new File("./sound-effects/start-timer.wav").getAbsoluteFile();
    private static final File timerRingFile = new File("./sound-effects/timer-ring.wav").getAbsoluteFile();

    public CLI(Timer _timer) {
        timer = _timer;
            timer.setOnTimerTick(progress -> this.template(progress, timer.getTime(), timer.getStatus()));
            timer.setOnDelayTick(progress -> this.template(progress, timer.getDelay(), timer.getStatus()));

            timer.setOnPlayStartSound( () -> new Thread(() -> this.sound(startSoundFile)).start() );

            timer.setOnPlayRingSound(() -> new Thread(() -> this.sound(timerRingFile)).start() );
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

    // width: 54
    public void template(int _progress, int _max, String _status) {
        // cls
        try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); }
        catch (InterruptedException | IOException e) { Utils.log(e.getMessage()); }

        String title = AsciiArt.toString( AsciiArt.getTitle() );

        String mode = ((_status.equals("TIMER"))?
                AsciiArt.toString( AsciiArt.getModeTimer() ):
                AsciiArt.toString( AsciiArt.getModePause() ) );

        String progress = AsciiArt.toString( AsciiArt.appendString(
            this.generatePercentage( (int)(( (double)_progress/_max ) * 100) ),
            this.generateTimer(_progress, _max)
        ) );

        String bar = AsciiArt.toString( generateProgressBar(_progress, _max) );

        Utils.print(
                title +
                mode  +
                progress +
                bar
        );
    }

    public String[] generatePercentage(int _number) {
        String[] title = AsciiArt.getProgress();

        String[] numbers = new String[title.length];
        Arrays.fill(numbers, "");
        do {
            String[] asciiNumber = AsciiArt.getNumber(_number % 10);
            numbers = AsciiArt.appendString(asciiNumber, numbers);
            _number = _number / 10;
        } while (_number > 0);
        AsciiArt.appendString(title, numbers);
        AsciiArt.appendString(title, AsciiArt.getPercent());

        return title;
    }

    public String[] generateTimer(int _progress, int _max) {
        String[] title = new String[6];
        Arrays.fill(title, "  ");

        AsciiArt.appendString(title, AsciiArt.getOpenBrackets());

        String[] progressNumber = new String[title.length];
        Arrays.fill(progressNumber, "");
       do {
            String[] asciiNumber = AsciiArt.getNumber(_progress % 10);
            progressNumber = AsciiArt.appendString(asciiNumber, progressNumber);
            _progress = _progress / 10;
        }  while (_progress > 0);

        String[] maxNumber = new String[title.length];
        Arrays.fill(maxNumber, "");
        do {
            String[] asciiNumber = AsciiArt.getNumber(_max % 10);
            maxNumber = AsciiArt.appendString(asciiNumber, maxNumber);
            _max = _max / 10;
        } while (_max > 0);

        AsciiArt.appendString(title, progressNumber);
        AsciiArt.appendString(title, AsciiArt.getOf());
        AsciiArt.appendString(title, maxNumber);

        AsciiArt.appendString(title, AsciiArt.getCloseBrackets());

        return title;
    }

    public String[] generateProgressBar(int _progress, int _max) {
        int progressChars = (int)(( (double)_progress/_max ) * progressBarLength);
        String timerProgressBarFill = new String(new char[progressChars]).replace('\0', '█');
        String timerProgressBarEmpty = new String(new char[progressBarLength-progressChars]).replace('\0', ' ');

        String[] title = new String[6];

        Arrays.fill(title, timerProgressBarFill + timerProgressBarEmpty);
        title = AsciiArt.appendString(AsciiArt.getOpenBrackets(), title);
        AsciiArt.appendString(title, AsciiArt.getCloseBrackets());

        return title;
    }
}
