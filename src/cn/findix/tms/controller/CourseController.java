package cn.findix.tms.controller;

import cn.findix.tms.model.Course;
import com.jfinal.core.Controller;

/**
 * Created by Sean on 2014/12/1.
 */
public class CourseController extends Controller {

    public void info() {
        setAttr("courses", Course.DAO.findAll());
        render("course_info.jsp");
    }

    public void edit(){
        render("course_edit.jsp");
    }

    public void saveAndAdd() {
        if (Course.DAO.isExisted(getPara("course.cid"))) {
            getModel(Course.class).update();
            renderText("SUCCESS");
        }
        }


    public void delete() {
        getModel(Course.class).delete();
        renderText("SUCCESS");
    }

}

