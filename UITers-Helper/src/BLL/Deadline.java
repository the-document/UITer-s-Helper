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
    private String DeadlineID;
    private String DeadLineName;
    private LocalDateTime DeadLineDate;
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
    
    public String getDeadlineID()
    {
        return DeadlineID;
    }
    
        public String getDeadLineName() {
        return DeadLineName;
    }

    public void setDeadLineName(String DeadLineName) {
        this.DeadLineName = DeadLineName;
    }

    public LocalDateTime getDeadLineDate() {
        return DeadLineDate;
    }

    public void setDeadLineDate(LocalDateTime DeadLineDate) {
        this.DeadLineDate = DeadLineDate;
    }
    
    public void setDeadlineID(String _DeadlineID)
    {
        DeadlineID = _DeadlineID;
    }
    
}
