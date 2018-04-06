package com.bugscript.iplimpulse;

public class PrimaryWrite {

    public String user_name, phone_number, support_team;
    public int points, bet;

    public PrimaryWrite() {
    }

    public PrimaryWrite(String user_name, String phone_number, String support_team, int points, int bet) {
        this.user_name = user_name;
        this.phone_number = phone_number;
        this.support_team = support_team;
        this.points = points;
        this.bet = bet;
    }
}
