package cn.findix.tms.controller;

import cn.findix.tms.interceptor.NoLoginInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.Controller;

/**
 * IndexController
 */
@ClearInterceptor
public class IndexController extends Controller {

    private boolean noAuth = false;

    public void index() {
        if (noAuth) {
            setSessionAttr("id", "2000000001");
            setSessionAttr("name", "测试");
            setSessionAttr("type", 1);
        }
        if (getSessionAttr("id") != null) {
            render("index.jsp");
        } else {
            render("login.jsp");
        }
    }

    public void temp() {
        redirect("templates/admin/index.html");
    }

}





