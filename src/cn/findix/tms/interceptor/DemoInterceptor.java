package cn.findix.tms.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

/**
 * DemoInterceptor
 * 此拦截器仅做为示例展示，在本 demo 中并不需要
 */
public class DemoInterceptor implements Interceptor {
	
	public void intercept(ActionInvocation ai) {
		System.out.println("Before invoking " + ai.getActionKey());
		ai.invoke();
		System.out.println("After invoking " + ai.getActionKey());
	}
}
