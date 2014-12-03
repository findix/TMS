package cn.findix.tms.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Sean on 2014/12/3.
 */
public class TeacherInterceptor implements Interceptor {
    @Override
    public void intercept(ActionInvocation ai) {
        HttpSession session = ai.getController().getRequest().getSession();
        HttpServletRequest request = ai.getController().getRequest();

        String type = session.getAttribute("type") + "";

        if ("4".equals(type)) {
            ai.getController().redirect("/");
        } else {
            ai.invoke();
        }
    }
}
