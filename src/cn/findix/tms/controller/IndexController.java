package cn.findix.tms.controller;

import com.jfinal.core.Controller;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

import javax.servlet.http.HttpSession;

/**
 * IndexController
 */
public class IndexController extends Controller {

    private boolean noAuth = true;

    public void index() {
        if (noAuth) {
            setSessionAttr("id", "2000000001");
            setSessionAttr("name", "测试");
            setSessionAttr("type", 1);
        }
        if (getSessionAttr("id") != null) {
            int userType = getSessionAttr("type");
            switch (userType) {
                case 0:
                    render("/jsp/character/student.jsp");
                    break;
                case 1:
                    render("/jsp/character/admin.jsp");
                    break;
                case 2:
                    render("/jsp/character/supervisor.jsp");
                    break;
                case 3:
                    render("/jsp/character/dept_admin.jsp");
                    break;
                case 4:
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





