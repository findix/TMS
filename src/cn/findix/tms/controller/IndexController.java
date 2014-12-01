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

    public void index() {
        if (getSessionAttr("id") != null) {
            int userType = getSessionAttr("type");
            switch (userType){
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

    //以下测试在线编辑office
    public void openword() {
        PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(getRequest());
        poCtrl1.setServerPage("poserver.do"); //此行必须
        // Create custom toolbar
        poCtrl1.addCustomToolButton("保存", "SaveDocument()", 1);
        poCtrl1.addCustomToolButton("-", "", 0);
        poCtrl1.addCustomToolButton("打印", "ShowPrintDlg()", 6);
        poCtrl1.addCustomToolButton("-", "", 0);
        poCtrl1.addCustomToolButton("全屏切换", "SetFullScreen()", 4);
        poCtrl1.addCustomToolButton("-", "", 0);
        poCtrl1.addCustomToolButton("加盖印章", "AddSeal()", 5);
        poCtrl1.addCustomToolButton("手写签批", "AddHandSign()", 5);
        poCtrl1.addCustomToolButton("验证印章", "VerifySeal()", 5);
        poCtrl1.setSaveFilePage("saveword");
        poCtrl1.webOpen("doc/syllabus_template.docx", OpenModeType.docNormalEdit, "FindiX");
        poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须
        render("editword.jsp");
    }

    public void saveword() {
        FileSaver fs = new FileSaver(getRequest(), getResponse());
        getRequest().setAttribute("FileSaver", fs);
        fs.saveToFile(getRequest().getSession().getServletContext().getRealPath("doc/") + "/" + fs.getFileName());
        fs.showPage(300, 300);
        setAttr("message", "显示自定义保存结果。");
        render("savefile.jsp");
    }
}





