package cn.findix.tms.interceptor;

import cn.findix.tms.model.Demo;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * LoginValidator.
 */
public class LoginValidator extends Validator {

	@Override
	protected void validate(Controller controller) {
		validateRequiredString("username", "titleMsg", "请输入Blog标题!");
		validateRequiredString("password", "contentMsg", "请输入Blog内容!");
	}

	@Override
	protected void handleError(Controller controller) {
		controller.keepModel(Demo.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/blog/save"))
			controller.render("add.html");
		else if (actionKey.equals("/blog/update"))
			controller.render("edit.html");
	}
}
