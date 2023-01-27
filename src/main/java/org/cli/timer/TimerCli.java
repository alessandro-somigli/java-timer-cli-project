package org.cli.timer;

import org.cli.timer.utils.Utils;

import java.util.function.Consumer;

public class TimerCli {
    private final int progressBarLength = 100;

    private final int time;
    private final int delay;
    private final boolean silent;

    private int timer;

    public TimerCli(int _time, int _delay, boolean _silent) {
        time = _time;
        delay = _delay;
        silent = _silent;

        timer = time;
    }
    public TimerCli() {
        this(600, -1, false);
    }

    public void start(Consumer<Integer> _onLoop) {
        timer = time;
        while (timer > 0) {
            try {
                long start = System.currentTimeMillis();
                _onLoop.accept(timer);
                long finish = System.currentTimeMillis();
                long elapsed = finish - start;
                Thread.sleep(1000 - elapsed);
            } catch (InterruptedException e) { Utils.log(e.getMessage()); }
            timer--;
        }
    }

    public void run() {
        this.start(_timer -> Utils.print("time: " + _timer));
        if (!silent) {
            // warning
        }
        if (delay != -1) {
            Utils.print("pause of " + delay + " seconds. take a break...");
            this.start(_t -> {});

            Utils.print("timer is starting again...");
            this.run();
        }
    }

    public int getTimer() { return timer; }

    public int getTime() { return time; }
    public int getDelay() { return delay; }
    public boolean getSilent() { return silent; }

    public void setTimer(int _timer) { timer = _timer; }
}
