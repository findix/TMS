package cn.findix.tms.controller;

import cn.findix.tms.interceptor.DemoInterceptor;
import cn.findix.tms.interceptor.LoginValidator;
import cn.findix.tms.model.Demo;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * DemoController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(DemoInterceptor.class)
public class DemoController extends Controller {
	public void index() {
		setAttr("blogPage", Demo.me.paginate(getParaToInt(0, 1), 10));
		render("blog.html");
	}
	
	public void add() {
	}
	
	@Before(LoginValidator.class)
	public void save() {
		getModel(Demo.class).save();
		redirect("/blog");
	}
	
	public void edit() {
		setAttr("blog", Demo.me.findById(getParaToInt()));
	}
	
	@Before(LoginValidator.class)
	public void update() {
		getModel(Demo.class).update();
		redirect("/blog");
	}
	
	public void delete() {
		Demo.me.deleteById(getParaToInt());
		redirect("/blog");
	}
}


