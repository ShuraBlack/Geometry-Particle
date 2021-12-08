package model;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {

    private String time;
    private int hour;
    private int min;
    private int sec;

    private final Timer timer;

    public Clock() {
        LocalDateTime ldt = LocalDateTime.now();
        this.time = ldt.getHour() + ":" + ldt.getMinute() + ":" + ldt.getSecond();
        this.hour = ldt.getHour();
        this.min = ldt.getMinute();
        this.sec = ldt.getSecond();

        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        },0,1000);
    }

    private void update() {
        sec++;
        if (sec == 60) {
            this.sec = 0;
            this.min++;
        }

        if (min == 60) {
            this.min = 0;
            this.hour++;
        }

        if (hour == 24) {
            this.hour = 0;
            this.min = 0;
            this.sec = 0;
        }

        this.time = this.hour + ":" +
                (this.min > 9 ? this.min : "0" + this.min) + ":" +
                (this.sec > 9 ? this.sec : "0" + this.sec);
    }

    public void close() {
        this.timer.cancel();
    }

    public String getTime() {
        return this.time;
    }
}
