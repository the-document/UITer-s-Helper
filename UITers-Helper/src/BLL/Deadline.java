/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.time.LocalDateTime;

/**
 *
 * @author mirushi
 */
public class Deadline {
    public String DeadlineID;
    public String DeadLineName;
    public LocalDateTime DeadLineDate;
    public Deadline()
    {
        DeadLineName = DeadlineID = "";
        DeadLineDate = null;
    }
    public Deadline(String _idDL, String _tenDL, LocalDateTime _ngayDL)
    {
        DeadlineID = _idDL;
        DeadLineName = _tenDL;
        DeadLineDate = _ngayDL;
    }
}
