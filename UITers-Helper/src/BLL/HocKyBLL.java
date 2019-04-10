/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.HocKyAccess;
import DTO.HocKy;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class HocKyBLL {
    
    public List<HocKy> GetAllHocKy() throws SQLException
    {
        HocKyAccess hkAccess=new HocKyAccess();
        return hkAccess.GetAllHocKy();
    }
}
