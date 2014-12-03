package cn.findix.tms.controller;

import cn.findix.tms.model.Course;
import cn.findix.tms.model.Teacher;
import com.jfinal.core.Controller;

/**
 * Created by Sean on 2014/12/1.
 */
public class SyllabusController extends Controller {

    public void index() {
        setAttr("courses", Course.DAO.find("SELECT course.cid, course.cname, course.cenglish, course.credit, course.mid, course.did, course.`status`, course.tid, teacher.tname FROM course INNER JOIN teacher ON course.tid = teacher.tid"));
        setAttr("teachers", Teacher.DAO.findAll());
        render("syllabus.jsp");
    }

    public void check() {
        setAttr("courses", Course.DAO.find("SELECT course.cid, course.cname, course.cenglish, course.credit, course.mid, course.did, course.`status`, course.tid, teacher.tname FROM course INNER JOIN teacher ON course.tid = teacher.tid"));
        setAttr("teachers", Teacher.DAO.findAll());
        render("syllabus_check.jsp");
    }

    public void distribution() {
        setAttr("courses", Course.DAO.findAll());
        setAttr("teachers", Teacher.DAO.findAll());
        render("syllabus_distribution.jsp");
    }

    public void write() {
        render("syllabus_write.jsp");
    }

    public void appoint() {
        String cid = getPara("cid");
        String tid = getPara("tid");
        Course.DAO.findById(cid).set("tid", Teacher.DAO.findFirstBy("tid=?", tid).get("tid")).update();
        Course.DAO.findById(cid).set("status", "已指派").update();
        renderText("SUCCESS");
    }

    public void status() {
        String cid = getPara("cid");
        String status = getPara("status");
        Course.DAO.findById(cid).set("status", status).update();
        renderText("SUCCESS");
    }

    public void edit() {
    }

    public void view() {
    }

    public void download() {
    }
}
