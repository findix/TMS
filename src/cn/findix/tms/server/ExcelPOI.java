package cn.findix.tms.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import cn.findix.tms.model.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/**
 * 操作Excel表格的功能类
 *
 * @version 1.0
 * @author：hnylj
 */
public class ExcelPOI {


    /**
     * 读取Excel表格表头的内容
     *
     * @param InputStream
     * @return String 表头内容的数组
     *
     */

    /**
     * 读取Excel数据内容
     *
     * @param file
     * @return Map 包含单元格数据内容的Map对象
     */

    public static boolean readTeacherContent(File file) {
        String tid;
        String tname;
        String dname;
        String did;
        String type;
        String aname;

        try {
            HSSFRow row;
            InputStream is = new FileInputStream(file);
            POIFSFileSystem fs = new POIFSFileSystem(is);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            // 得到总行数
            int rowNum = sheet.getLastRowNum();
            row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells();
            // 正文内容应该从第二行开始,第一行为表头的标题
            for (int i = 1; i <= rowNum; i++) {
                row = sheet.getRow(i);
                // int j = 0;
                // while (j<colNum) {}
                tid = getStringCellValue(row.getCell(0)).trim();
                tname = getStringCellValue(row.getCell(1)).trim();
                dname = getStringCellValue(row.getCell(2)).trim();
                aname = getStringCellValue(row.getCell(3)).trim();
                did= Department.DAO.findFirstBy("dname=?",dname).get("did");
                type= Auth.DAO.findFirstBy("aname=?",aname).get("type");

                if (!Teacher.DAO.isExisted(tid)) {
                    new Teacher().set("tid", tid).set("tid", tname).set("did", did).set("type", type).set("password","123456").save();
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读取Excel数据内容
     *
     * @param file
     * @return Map 包含单元格数据内容的Map对象
     */
    public static boolean readCourseContent(File file) {
        String cid;
        String cname;
        String cenglish;
        String credit;
        String did;
        String mid;
        String dname;
        String mname;

        try {
            HSSFRow row;
            InputStream is = new FileInputStream(file);
            POIFSFileSystem fs = new POIFSFileSystem(is);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            // 得到总行数
            int rowNum = sheet.getLastRowNum();
            row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells();
            // 正文内容应该从第二行开始,第一行为表头的标题
            for (int i = 1; i <= rowNum; i++) {
                row = sheet.getRow(i);
                // int j = 0;
                // while (j<colNum) {}
                cid = getStringCellValue(row.getCell(0)).trim();
                cname = getStringCellValue(row.getCell(1)).trim();
                cenglish = getStringCellValue(row.getCell(2)).trim();
                credit = getStringCellValue(row.getCell(3)).trim();
                dname = getStringCellValue(row.getCell(4)).trim();
                mname = getStringCellValue(row.getCell(5)).trim();
                did= Department.DAO.findFirstBy("dname=?",dname).get("did");
                mid= Major.DAO.findFirstBy("mname=?",mname).get("mid");

                if (!Course.DAO.isExisted(cid)) {
                    new Course().set("cid", cid).set("cname", cname).set("cenglish",cenglish).set("credit",credit).set("did", did).set("mid", mid).save();
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private static String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }
}