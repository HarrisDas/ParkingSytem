package com.example.harri.parkingsystem.UserActivityPanel;

/**
 * Created by harri on 8/4/2017.
 */

public class Feedback {
   private String Msg;
    private String reply;

    private   String UID;

public Feedback(){


}

    public Feedback(String msg, String reply, String UID) {
        Msg = msg;
        this.reply = reply;
        this.UID = UID;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
