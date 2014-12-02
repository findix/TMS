package cn.findix.tms.controller;

import cn.findix.tms.model.Teacher;
import com.jfinal.core.Controller;

/**
 * Created by Sean on 2014/12/1.
 */
public class TeacherController extends Controller {

    public void info() {
        setAttr("teachers", Teacher.DAO.findAll());
        render("teacher_info.jsp");
    }

    public void edit() {
        render("teacher_edit.jsp");
    }

    /**
     * Ajax API to save teacher info
     */
    public void saveAndAdd() {
        if (Teacher.DAO.isExisted(getPara("teacher.tid"))) {
            getModel(Teacher.class).update();
            renderText("SUCCESS");
        } else {
            getModel(Teacher.class).set("password", "123456").save();
            renderText("SUCCESS");
        }
    }

    public void delete() {
        getModel(Teacher.class).delete();
        renderText("SUCCESS");
    }
}
