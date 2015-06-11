package com.mplayer.web.domain;

public class Number {
    private final int value;
    private final boolean choosed;
    
    public Number(int value, boolean choosed) {
        this.value = value;
        this.choosed = choosed;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public boolean isChoosed() {
        return this.choosed;
    }
}
