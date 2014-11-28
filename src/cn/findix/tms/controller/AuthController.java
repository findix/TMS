package cn.findix.tms.controller;

import cn.findix.tms.model.Student;
import cn.findix.tms.model.Teacher;
import com.jfinal.core.Controller;

/**
 * Created by Sean on 2014/11/27.
 */
public class AuthController extends Controller {


    public void login() {
        String id = getPara("id");
        String password = getPara("password");
        if (id == null || password == null) {
            renderText("ERROR");
        } else {
            switch (id.length()) {
                case 8:
                    if (Student.dao.isExisted(id, password)) {
                        setSessionAttr("id", id);
                        setSessionAttr("name", Student.dao.findById(id).getStr("sname"));
//                    redirect("/");
                        renderText("SUCCESS");
                    }
                    break;
                case 10:
                    if (Teacher.dao.isExisted(id, password)) {
                        setSessionAttr("id", id);
                        setSessionAttr("name", Student.dao.findById(id));
//                    redirect("/");
                        renderText("SUCCESS");
                    }
                    break;
                default:
                    renderText("ERROR");
            }
        }
    }

    public void logout() {
        removeSessionAttr("id");
        removeSessionAttr("name");
        renderText("退出");
        redirect("/");
    }

    public void changePassword() {
        String password = getPara("password");
        String id = getSessionAttr("id");
        if (id == null || password == null || password.length() == 0) {
            renderText("ERROR");
        } else {
            switch (id.length()) {
                case 8:
                    Student.dao.findById(id).set("password", password).update();
                    renderText("SUCCESS");
                    break;
                case 10:
                    Teacher.dao.findById(id).set("password", password).update();
                    renderText("SUCCESS");
                    break;
                default:
                    renderText("ERROR");
            }
        }
    }
}
