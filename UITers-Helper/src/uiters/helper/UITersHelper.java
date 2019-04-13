/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiters.helper;

import BLL.LopHocBLL;
import BLL.MonHocBLL;
import BLL.MyFile;
import BLL.ThuatToanTaoTKB;
import static BLL.ThuatToanTaoTKB.Try;
import static BLL.ThuatToanTaoTKB.init;
import DTO.LopHoc;
import DTO.MonHoc;
import DTO.TimeTable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Hong Phuc
 */
public class UITersHelper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        // TODO code application logic here
        
        MyFile myFile=new  MyFile("C:\\Users\\Nguyen Hong Phuc\\Downloads\\tkb_khdt_25-01-2019_1548381246_hk_2_nh2018.xlsx");
        myFile.ReadAllDataFromSheet(0);
        myFile.ReadAllDataFromSheet(1);
        //myFile.ExportConsole(myFile.GetListCourseTheoryOfEducationProgram("*"));
        
       
//        LopHocBLL lhbll=new LopHocBLL();
//        lhbll.DeleteAllData();
//        
//        lhbll.SaveAllLopHoc(myFile.GetListCourseTheoryOfEducationProgram("*"));
//        lhbll.SaveAllLopHoc(myFile.GetListCoursePracticeOfEducationProgram("*"));
        //lhbll.InsertLopHoc(new LopHoc("TEST.J00","CSDL","Nguyễn Hồng Phúc","12/04/2012","13/5/2018","12345","5","B114","CQUI","LT"));
//      //lhbll.DeleteAllData();

//        List<String> dsMaLop = new ArrayList<>();
//        dsMaLop.add("IT002");
//        dsMaLop.add("IT003");
//        dsMaLop.add("MA004");
//        dsMaLop.add("MA005");
//        dsMaLop.add("EN005");
//        dsMaLop.add("PE002");
//
//        ThuatToanTaoTKB.NapDanhSachMaMonHoc(dsMaLop);
//        ThuatToanTaoTKB.SetHeDaoTao("CQUI");
//
//        ThuatToanTaoTKB.init();
//        ThuatToanTaoTKB.Try(0);
//
//        System.out.println("=========LIST TKB RECOMEND FOR YOU  ( " + ThuatToanTaoTKB.countCase + " ) ============\n");
//        for (TimeTable table : ThuatToanTaoTKB.listTimeTables) {
//            table.Export();
//            System.out.println("===============================================\n");
//            System.out.println("===============================================\n");
//        }

//        MonHocBLL mhbll=new MonHocBLL();
//        List<MonHoc> dsMonHocGoiY=new ArrayList<MonHoc>();
//        dsMonHocGoiY= mhbll.GetAllMonHoc("18520951", "KTPM", 1) ;
//        
//        for (MonHoc monHoc : dsMonHocGoiY) {
//            System.out.println(monHoc.toString());
//        }

        LopHoc l1=new LopHoc("IT002.J21.2", "IT002", "gv1", "1", "1", "67890", "2", "a11", "abc", "LT");
        LopHoc l2=new LopHoc("MA005.J22", "MA005", "gv2", "1", "1", "678", "2", "a11", "abc", "LT");
        System.out.println(l1.isOverlap(l2));
    }
}
