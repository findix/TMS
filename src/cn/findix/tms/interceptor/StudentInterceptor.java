package cn.findix.tms.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Sean on 2014/12/3.
 */
public class StudentInterceptor implements Interceptor {
    @Override
    public void intercept(ActionInvocation ai) {
        HttpSession session = ai.getController().getRequest().getSession();
        HttpServletRequest request = ai.getController().getRequest();

        String type = session.getAttribute("type") + "";

        if ("0".equals(type) || "2".equals(type)) {
            ai.getController().redirect("/");
        } else {
            ai.invoke();
        }
    }
}
