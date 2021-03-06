package cn.findix.tms.controller;

import cn.findix.tms.interceptor.StudentInterceptor;
import cn.findix.tms.interceptor.TeacherInterceptor;
import cn.findix.tms.model.Teacher;
import cn.findix.tms.server.ExcelPOI;
import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;

import java.io.File;

/**
 * Created by Sean on 2014/12/1.
 */

@Before({StudentInterceptor.class, TeacherInterceptor.class})
public class TeacherController extends Controller {

    @ClearInterceptor
    public void info() {
        setAttr("teachers", Teacher.DAO.findMyAll());
        render("teacher_info.jsp");
    }

    public void upload() {
        render("teacher_upload.jsp");
    }

    /**
     * Ajax API to save teacher info
     */
    public void saveAndAdd() {
        if (Teacher.DAO.isExisted(getPara("teacher.tid"))) {
            getModel(Teacher.class).update();
            renderText("SUCCESS");
        } else {
            getModel(Teacher.class).set("password", "123456").save();
            renderText("SUCCESS");
        }
    }

    public void delete() {
        getModel(Teacher.class).delete();
        renderText("SUCCESS");
    }

    public void uploadFile() {
        UploadFile uploadFile = getFile();
        File file = uploadFile.getFile();
        ExcelPOI.readTeacherContent(file);
        renderText("SUCCESS");
    }

    public void downloadInfo() {
        File downloadFile = new File(PathKit.getWebRootPath() + "/doc/teacher_info.xls");
        renderFile(downloadFile);
    }
}
