package cn.findix.tms.controller;

import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.Controller;

/**
 * IndexController
 */
public class IndexController extends Controller {

    private boolean noAuth = false;

    @ClearInterceptor(ClearLayer.ALL)
    public void index() {
        if (noAuth) {
            setSessionAttr("id", "2000000001");
            setSessionAttr("name", "测试");
            setSessionAttr("type", 1);
        }
        if (getSessionAttr("id") != null) {
            String userType = getSessionAttr("type");
            switch (userType) {
                case "0":
                    render("/jsp/character/student.jsp");
                    break;
                case "1":
                    render("/jsp/character/admin.jsp");
                    break;
                case "2":
                    render("/jsp/character/supervisor.jsp");
                    break;
                case "3":
                    render("/jsp/character/dept_admin.jsp");
                    break;
                case "4":
                    render("/jsp/character/teacher.jsp");
                    break;
            }
            render("index.jsp");
        } else {
            render("login.jsp");
        }
    }

    public void temp() {
        redirect("templates/admin/index.html");
    }

}





