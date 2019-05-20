/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.time.LocalTime;

/**
 *
 * @author Admin
 */
public class LichTrinh {

    public LocalTime time;
    public String location;
    public String desc;

    public LichTrinh() {
        time = java.time.LocalTime.now();
        location = "";
        desc = "";
    }

    public LichTrinh(LocalTime _time, String _location, String _desc) {
        time = _time;
        location = _location;
        desc = _desc;
    }
}
