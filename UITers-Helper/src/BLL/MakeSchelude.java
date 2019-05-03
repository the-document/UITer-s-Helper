/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.LopHoc;
import DTO.MonHoc;
import GUI.controller.CreateTimetableNowController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class MakeSchelude extends Thread{
 
    private void maKeSchedule(){
        List<String> lsMaMon = new ArrayList<>();

        lsMaMon.clear();
        for (MonHoc m : Global.lsMonHocSelected.values()) {
            lsMaMon.add(m.getMaMonHoc());
            System.out.print(m.getMaMonHoc() + ", ");
        }

        ThuatToanTaoTKB.NapDanhSachMaMonHoc(lsMaMon);
        ThuatToanTaoTKB.SetHeDaoTao("CQUI");

        try {
            ThuatToanTaoTKB.init();
        } catch (SQLException ex) {
            Logger.getLogger(CreateTimetableNowController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        ThuatToanTaoTKB.Try(0);
        System.out.println("=========LIST TKB RECOMEND FOR YOU  ( " + ThuatToanTaoTKB.countCase + " ) ============\n");
        ThuatToanTaoTKB.listTimeTables.forEach((table) -> {
            table.Export();
        });

        if (!ThuatToanTaoTKB.dsMaMonNotFound.isEmpty()) {
           // lb_SubjectNotFound.setText("Những môn học không mở: " + ThuatToanTaoTKB.dsMaMonNotFound.toString());
            System.out.println("Subject not open in this semeter: " + ThuatToanTaoTKB.dsMaMonNotFound.size() + " - " + ThuatToanTaoTKB.dsMaMonNotFound.toString());
        }
    }
    
    private void filterAndMakeSchedule(){
        List<String> lsMaMon = new ArrayList<>();

        lsMaMon.clear();
        for (MonHoc m : Global.lsMonHocSelected.values()) {
            lsMaMon.add(m.getMaMonHoc());
            System.out.print(m.getMaMonHoc() + ", ");
        }

        ThuatToanTaoTKB.NapDanhSachMaMonHoc(lsMaMon);
        ThuatToanTaoTKB.SetHeDaoTao("CQUI");

        try {
            ThuatToanTaoTKB.init();
        } catch (SQLException ex) {
            Logger.getLogger(CreateTimetableNowController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        //filter dayof with select dayof for list course current.
        HashSet <String> mySet=new HashSet<>();
        for (LopHoc lopDayoff : Global.lsDayOff) {
            for (LopHoc lopHienCo : ThuatToanTaoTKB.dsFilterLT) {
                if(lopDayoff.isOverlap(lopHienCo))
                {
                    ThuatToanTaoTKB.dsFilterLT.remove(lopHienCo);
//                    for (LopHoc lopTH : ThuatToanTaoTKB.dsFilterTH) {
//                        if((lopHienCo.getMaLop()+".1")
//                        {
//                            break;
//                        }
//                    }
                  break;
                }
                mySet.add(lopHienCo.getmaMonHoc());
            }
            
        }
        
        //check couse co du de tao hay k
        if(mySet.size()<ThuatToanTaoTKB.NumberOfCouseFound)
        {
            System.out.println("Can't create, please choose other dayoff");
        }
        else
        {
            ThuatToanTaoTKB.Try(0);
        }
        
        System.out.println("=========LIST TKB RECOMEND FOR YOU  ( " + ThuatToanTaoTKB.countCase + " ) ============\n");
        ThuatToanTaoTKB.listTimeTables.forEach((table) -> {
            table.Export();
        });

        if (!ThuatToanTaoTKB.dsMaMonNotFound.isEmpty()) {
           // lb_SubjectNotFound.setText("Những môn học không mở: " + ThuatToanTaoTKB.dsMaMonNotFound.toString());
            System.out.println("Subject not open in this semeter: " + ThuatToanTaoTKB.dsMaMonNotFound.size() + " - " + ThuatToanTaoTKB.dsMaMonNotFound.toString());
        }
    }
    
    @Override
    public void run(){
        
        if(Global.MeThodCreateSchedule==Global.MeThodCreate.RANDOM)
        this.maKeSchedule();
        else
            if(Global.MeThodCreateSchedule==Global.MeThodCreate.DAYOF)
                filterAndMakeSchedule();
    }
}
