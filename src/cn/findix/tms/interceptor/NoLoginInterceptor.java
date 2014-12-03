package cn.findix.tms.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class NoLoginInterceptor implements Interceptor {

    public void intercept(ActionInvocation ai) {
        HttpSession session = ai.getController().getRequest().getSession();
        HttpServletRequest request = ai.getController().getRequest();

        if (session.getAttribute("id") == null) {
            ai.getController().redirect("/");
        } else {
            ai.invoke();
        }
    }
}
