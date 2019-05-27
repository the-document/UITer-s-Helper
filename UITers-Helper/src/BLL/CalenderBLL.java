/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.CalenderAccess;
import DTO.Calender;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class CalenderBLL {

    public boolean InsertCalender(Calender calender) throws SQLException {
        CalenderAccess ca = new CalenderAccess();
        return ca.InsertCalender(calender);
    }

    public List<Calender> GetPersonalCalenderInfuture() throws SQLException {
        CalenderAccess ca = new CalenderAccess();
        return ca.GetPersonalCalenderInfuture();
    }

    public List<Calender> GetPersonalCalenderCurrentMonth() throws SQLException {
        CalenderAccess ca = new CalenderAccess();
        return ca.GetPersonalCalenderCurrentMonth();
    }

    public boolean DeleteCalender(String id) throws SQLException {
        CalenderAccess ca = new CalenderAccess();
        return ca.DeleteCalender(id);
    }
}
