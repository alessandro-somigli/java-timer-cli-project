package org.cli.timer;

import org.cli.timer.utils.Utils;

import java.util.function.Consumer;

public class Timer {
    private final int time;
    private final int delay;
    private final boolean silent;

    // STOPPED, TIMER, DELAY
    private String status = "STOPPED";

    private Consumer<Integer> onTimerTick = timer -> {};

    private Runnable onTimerStart = () -> {};
    private Runnable onTimerStop = () -> {};

    private Runnable onPlayStartSound = () -> {};
    private Runnable onPlayRingSound = () -> {};

    private Consumer<Integer> onDelayTick = timer -> {};

    public Timer(int _time, int _delay, boolean _silent) {
        time = _time;
        delay = _delay;
        silent = _silent;
    }

    public void start() {
        // timer
        if (!silent) { this.onPlayStartSound.run(); }
        this.status = "TIMER";
        this.onTimerStart.run();
        this.countdown(time, onTimerTick);
        this.onTimerStop.run();
        if (!silent) { this.onPlayRingSound.run(); }

        // delay
        this.status = "DELAY";
        this.countdown(delay, onDelayTick);

        if (delay >= 0) this.start();
    }

    public void countdown(int _time, Consumer<Integer> onTimerTick) {
        int timer = 0;
        while (timer <= _time) {
            long start = System.currentTimeMillis();
            onTimerTick.accept(timer);
            long finish = System.currentTimeMillis();
            long elapsed = finish - start;

            try { Thread.sleep(1000 - elapsed); }
            catch (InterruptedException e) { Utils.log(e.getMessage()); }

            timer++;
        }
    }

    public int getTime() { return time; }
    public int getDelay() { return delay; }
    public boolean getSilent() { return silent; }

    public String getStatus() { return status; }

    public void setOnTimerStart(Runnable _onTimerStart) { onTimerStart = _onTimerStart; }
    public void setOnTimerTick(Consumer<Integer> _onTimerTick) { onTimerTick = _onTimerTick; }
    public void setOnTimerStop(Runnable _onTimerStop) { onTimerStop = _onTimerStop; }

    public void setOnPlayStartSound(Runnable _onPlayStartSound) { onPlayStartSound = _onPlayStartSound; }
    public void setOnPlayRingSound(Runnable _onPlayRingSound) { onPlayRingSound = _onPlayRingSound; }

    public void setOnDelayTick(Consumer<Integer> _onDelayTick) { onDelayTick = _onDelayTick; }
}
