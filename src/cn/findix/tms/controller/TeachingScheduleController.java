package cn.findix.tms.controller;

import cn.findix.tms.interceptor.StudentInterceptor;
import cn.findix.tms.interceptor.TeacherInterceptor;
import cn.findix.tms.model.Course;
import cn.findix.tms.model.Teacher;
import cn.findix.tms.server.FileUtil;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

import java.io.File;
import java.io.IOException;

/**
 * Created by Long on 2014/12/1.
 */
@Before(StudentInterceptor.class)
public class TeachingScheduleController extends Controller {
    @ClearInterceptor
    public void index() {
        String sql = "SELECT course.cid, course.cname, course.cenglish, course.credit, course.mid, course.did, course.`tstatus`, course.tid, teacher.tname FROM course INNER JOIN teacher ON course.ttid = teacher.tid";
        setAttr("courses", Course.DAO.find(sql));
        setAttr("teachers", Teacher.DAO.findAll());
        render("teaching_schedule.jsp");
    }

    @Before(TeacherInterceptor.class)
    public void check() {
        String sql = "SELECT course.cid, course.cname, course.cenglish, course.credit, course.mid, course.did, course.`tstatus`, course.tid, teacher.tname FROM course INNER JOIN teacher ON course.ttid = teacher.tid";
        setAttr("courses", Course.DAO.find(sql));
        setAttr("teachers", Teacher.DAO.findAll());
        render("teaching_schedule_check.jsp");
    }

    @Before(TeacherInterceptor.class)
    public void distribution() {
        setAttr("courses", Course.DAO.findAll());
        setAttr("teachers", Teacher.DAO.findAll());
        render("teaching_schedule_distribution.jsp");
    }

    public void write() {
        String tid=getSessionAttr("id");
        setAttr("courses", Course.DAO.findBy("tid=?",tid));
        render("teaching_schedule_write.jsp");
    }

    @Before(TeacherInterceptor.class)
    public void appoint() {
        String cid = getPara("cid");
        String tid = getPara("tid");
        Course.DAO.findById(cid).set("ttid", Teacher.DAO.findFirstBy("tid=?", tid).get("tid")).update();
        Course.DAO.findById(cid).set("tstatus", "已指派").update();
        renderText("SUCCESS");
    }

    public void status() {
        String cid = getPara("cid");
        String status = getPara("status");
        Course.DAO.findById(cid).set("tstatus", status).update();
        renderText("SUCCESS");
    }

    public void edit() {
        String cid = getPara(0);

        String realPath=getRequest().getSession().getServletContext().getRealPath("doc/");
        String source_name=realPath + "/teaching_schedule.docx";
        String dest_name=realPath + "/teaching_schedule/" + cid + ".docx";

        try {
            FileUtil.copyFile(source_name, dest_name, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(getRequest());
        poCtrl1.setServerPage("/poserver.do"); //此行必须
        // Create custom toolbar
        poCtrl1.addCustomToolButton("保存", "SaveDocument()", 1);
        poCtrl1.addCustomToolButton("-", "", 0);
        poCtrl1.addCustomToolButton("打印", "ShowPrintDlg()", 6);
        poCtrl1.addCustomToolButton("-", "", 0);
        poCtrl1.addCustomToolButton("全屏切换", "SetFullScreen()", 4);
        poCtrl1.setSaveFilePage("../saveword");
        poCtrl1.webOpen("/doc/teaching_schedule/"+cid+".docx", OpenModeType.docNormalEdit, "FindiX");
        poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须
        render("editword.jsp");

    }

    public void saveword() {
        FileSaver fs = new FileSaver(getRequest(), getResponse());
        getRequest().setAttribute("FileSaver", fs);
        fs.saveToFile(getRequest().getSession().getServletContext().getRealPath("doc/") + "/teaching_schedule/" + fs.getFileName());
        fs.showPage(200, 104);
        setAttr("message", "保存成功。");
        render("savefile.jsp");
    }

    @ClearInterceptor
    public void view() {
        String cid = getPara(0);

        String realPath=getRequest().getSession().getServletContext().getRealPath("doc/");
        String source_name=realPath + "/teaching_schedule.docx";
        String dest_name=realPath + "/teaching_schedule/" + cid + ".docx";

        try {
            FileUtil.copyFile(source_name, dest_name, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(getRequest());
        poCtrl1.setServerPage("/poserver.do"); //此行必须
        // Create custom toolbar
        poCtrl1.addCustomToolButton("打印", "ShowPrintDlg()", 6);
        poCtrl1.addCustomToolButton("-", "", 0);
        poCtrl1.addCustomToolButton("全屏切换", "SetFullScreen()", 4);
        poCtrl1.addCustomToolButton("-", "", 0);
        poCtrl1.webOpen("/doc/teaching_schedule/"+cid+".docx", OpenModeType.docNormalEdit, "FindiX");
        poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须
        render("editword.jsp");
    }

    @ClearInterceptor
    public void download() {
        String cid = getPara(0);
        File downloadFile = new File(PathKit.getWebRootPath() + "/doc/teaching_schedule/"+cid+".docx");
        renderFile(downloadFile);
    }
}
