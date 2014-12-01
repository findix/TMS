package cn.findix.tms.controller;

import com.jfinal.core.Controller;

/**
 * Created by Sean on 2014/12/1.
 */
public class TeacherController extends Controller {

    public void info() {
        render("teacher_info.jsp");
    }

    public void edit() {
        render("teacher_edit.jsp");
    }
}
