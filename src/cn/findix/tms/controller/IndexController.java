package cn.findix.tms.controller;

import com.jfinal.core.Controller;

import javax.servlet.http.HttpSession;

/**
 * IndexController
 */
public class IndexController extends Controller {

    public void index() {
        if (getSessionAttr("id") != null) {
            render("index.jsp");
        } else {
            render("login.jsp");
        }
    }
}





