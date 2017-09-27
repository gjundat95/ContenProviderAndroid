package com.jundat95.contactandroid.shared.util;

/**
 * Created by tinhngo on 27/09/2017.
 */

public class TimeUtil {

    private long timeStamp;

    public TimeUtil(String timeStamp) {
        this.timeStamp = Integer.parseInt(timeStamp);
    }

    public TimeUtil(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTimeFromDuration() {
        StringBuffer finalTimeString = new StringBuffer();
        StringBuffer secondsString = new StringBuffer();
        StringBuffer minutesString = new StringBuffer();

        int hours = (int) (this.timeStamp / ( 1000 * 60 * 60));
        int minutes = (int) ((this.timeStamp % ( 1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) ((this.timeStamp % ( 1000 * 60 * 60)) % ( 1000 * 60) / 1000);

        if(hours > 0){
            finalTimeString.append(hours + ":");
        }

        if(minutes < 10) {
            minutesString.append("0" + minutes);
        } else {
            minutesString.append(minutes);
        }

        if(seconds < 10) {
            secondsString.append("0" + seconds);
        } else {
            secondsString.append(seconds);
        }

        return finalTimeString.append(minutesString+":" + secondsString).toString();
    }
}
