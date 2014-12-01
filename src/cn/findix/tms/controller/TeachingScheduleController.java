package cn.findix.tms.controller;

import com.jfinal.core.Controller;

/**
 * Created by Sean on 2014/12/1.
 */
public class TeachingScheduleController extends Controller {
    public void index(){
        render("teaching_schedule.jsp");
    }

    public void check(){
        render("teaching_schedule_check.jsp");
    }

    public void distribution(){
        render("teaching_schedule_distribution.jsp");
    }

    public void write(){
        render("teaching_schedule_write.jsp");
    }
}
