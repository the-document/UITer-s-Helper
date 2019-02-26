/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class Setting {
    String backGround;
    String ringTone;
    int volume;
    String language;
    boolean startWithOS;
    boolean allowNotify;

    public Setting() {
    }

    public Setting(String backGround, String ringTone, int volume, String language, boolean startWithOS, boolean allowNotify) {
        this.backGround = backGround;
        this.ringTone = ringTone;
        this.volume = volume;
        this.language = language;
        this.startWithOS = startWithOS;
        this.allowNotify = allowNotify;
    }

    public String getBackGround() {
        return backGround;
    }

    public void setBackGround(String backGround) {
        this.backGround = backGround;
    }

    public String getRingTone() {
        return ringTone;
    }

    public void setRingTone(String ringTone) {
        this.ringTone = ringTone;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isStartWithOS() {
        return startWithOS;
    }

    public void setStartWithOS(boolean startWithOS) {
        this.startWithOS = startWithOS;
    }

    public boolean isAllowNotify() {
        return allowNotify;
    }

    public void setAllowNotify(boolean allowNotify) {
        this.allowNotify = allowNotify;
    }
    
    
}
