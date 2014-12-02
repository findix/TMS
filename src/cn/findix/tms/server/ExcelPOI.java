package cn.findix.tms.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import cn.findix.tms.model.Teacher;
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
        int type;
        String aname;

        List typeList = new ArrayList(){{
            add("学生");
            add("校教务处管理员");
            add("教学督导组");
            add("院管理员");
            add("教师");
        }};

        Map<String,String> department = new HashMap<String,String>(){{
            put("上海电力学院","00");
            put("计算机科学与技术学院","01");
            put("电子与信息工程学院","02");
            put("自动化工程学院","03");
            put("外国语学院","04");
            put("电气工程学院","05");
            put("能源与机械工程学院","06");
            put("环境与化学工程学院","07");
            put("经济与管理学院","08");
            put("国际交流学院","09");
            put("数理学院","10");
        }};

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
                did=department.get(dname);
                type=typeList.indexOf(aname);

                if (!Teacher.DAO.isExisted(tid)) {
                    new Teacher().set("tid", tid).set("tname", tname).set("did", did).set("type", type).set("password","123456").save();
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