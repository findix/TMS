package cn.findix.tms.controller;

import com.jfinal.core.Controller;

/**
 * Created by Sean on 2014/12/1.
 */
public class CourseController extends Controller {

            public void index(){
                redirect("jsp/course/course_info.jsp");
            }

}