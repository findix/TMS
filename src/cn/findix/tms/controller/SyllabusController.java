package cn.findix.tms.controller;

import cn.findix.tms.model.Course;
import cn.findix.tms.model.Teacher;
import com.jfinal.core.Controller;

/**
 * Created by Sean on 2014/12/1.
 */
public class SyllabusController extends Controller {

    public void index(){
        setAttr("courses", Course.DAO.findAll());
        setAttr("teachers", Teacher.DAO.findAll());
        render("syllabus.jsp");
    }

    public void check(){
        render("syllabus_check.jsp");
    }

    public void distribution(){
        render("syllabus_distribution.jsp");
    }

    public void write(){
        render("syllabus_write.jsp");
    }

    public void edit(){

    }

    public void view(){}

    public void download(){}
}
