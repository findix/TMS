package cn.findix.tms.controller;

import com.jfinal.core.Controller;

/**
 * Created by Sean on 2014/12/1.
 */
public class SyllabusController extends Controller {

    public void index(){
        render("syllabus.jsp");
    }

    public void check(){
        render("syllabus_check.jsp");
    }

    public void distribution(){
        render("syllabus_distribution。jsp");
    }

    public void write(){
        render("syllabus_write.jsp");
    }
}
