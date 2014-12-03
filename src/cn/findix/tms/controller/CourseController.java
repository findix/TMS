package cn.findix.tms.controller;

import cn.findix.tms.interceptor.StudentInterceptor;
import cn.findix.tms.interceptor.TeacherInterceptor;
import cn.findix.tms.model.Course;
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
@Before({StudentInterceptor.class,TeacherInterceptor.class})
public class CourseController extends Controller {

    @ClearInterceptor
    public void info() {
        setAttr("courses", Course.DAO.findMyAll());
        render("course_info.jsp");
    }

    public void upload() {
        render("course_upload.jsp");
    }

    public void saveAndAdd() {
        if (Course.DAO.isExisted(getPara("course.cid"))) {
            getModel(Course.class).update();
            renderText("SUCCESS");
        } else {
                getModel(Course.class).save();
                renderText("SUCCESS");
            }

        }

    public void delete() {
        getModel(Course.class).delete();
        renderText("SUCCESS");
    }

    public void uploadFile() {
        UploadFile uploadFile = getFile();
        File file = uploadFile.getFile();
        ExcelPOI.readCourseContent(file);
        renderText("SUCCESS");
    }

    public void downloadInfo() {
        File downloadFile = new File(PathKit.getWebRootPath() + "/doc/course_info.xls");
        renderFile(downloadFile);
    }

}

