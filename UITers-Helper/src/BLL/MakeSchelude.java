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
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class MakeSchelude extends Thread{
    
    List<String> lsMaMon = new ArrayList<>();
    
    private void maKeSchedule(){
        

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

            System.out.println("in list dayof");
            
            int index=0;
            while (index<ThuatToanTaoTKB.dsFilterLT.size()) {                
                while (index<ThuatToanTaoTKB.dsFilterLT.size()&&!ThuatToanTaoTKB.dsFilterLT.get(index).isOverlap(lopDayoff)) {                    
                    mySet.add(ThuatToanTaoTKB.dsFilterLT.get(index).getmaMonHoc());
                    index++;
                }
                if(index<=ThuatToanTaoTKB.dsFilterLT.size()-1)
                    ThuatToanTaoTKB.dsFilterLT.remove(ThuatToanTaoTKB.dsFilterLT.get(index));  
            }
//            for (LopHoc lopHienCo : ThuatToanTaoTKB.dsFilterLT) {
//                if(lopDayoff.isOverlap(lopHienCo))
//                {
//                    System.out.println("overlap"+ lopDayoff.getmaMonHoc());
//                    ThuatToanTaoTKB.dsFilterLT.remove(lopHienCo);
////                    for (LopHoc lopTH : ThuatToanTaoTKB.dsFilterTH) {
////                        if((lopHienCo.getMaLop()+".1")
////                        {
////                            break;
////                        }
////                    }
//                }else
//                mySet.add(lopHienCo.getmaMonHoc());
//            }

            
        }
        
        //check couse co du de tao hay k

        System.out.println("myset size:"+mySet.size());
        System.out.println("Course found:"+ThuatToanTaoTKB.NumberOfCouseFound);
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
    
    private void loadSubjectForHandCreate(){
        //load all subject need for schedule
    }
    @Override
    public void run(){
        
        if(null!=Global.MeThodCreateSchedule)
        switch (Global.MeThodCreateSchedule) {
            case RANDOM:
                this.maKeSchedule();
                break;
            case DAYOF:
                filterAndMakeSchedule();
                break;
        //load subject for user create with hand
            case ADVANCE:
                break;
            default:
                break;
        }
    }
}
