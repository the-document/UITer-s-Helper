/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.NganhHocAccess;
import DTO.NganhHoc;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class NganhHocBLL {
    
    public List<NganhHoc> GetAllNganhHoc() throws SQLException{
        NganhHocAccess nganhHocAccess=new NganhHocAccess();
        return nganhHocAccess.GetAllNganhHoc();
    }
}
