package cn.findix.tms.config;

import cn.findix.tms.controller.AuthController;
import cn.findix.tms.model.Demo;
import cn.findix.tms.controller.IndexController;
import cn.findix.tms.model.Student;
import cn.findix.tms.model.Teacher;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

/**
 * API引导式配置
 */
public class Config extends JFinalConfig {

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        // 加载少量必要配置，随后可用getProperty(...)获取值
        loadPropertyFile("a_little_config.txt");
        me.setDevMode(getPropertyToBoolean("devMode", false));
        me.setViewType(ViewType.JSP);
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);    // 第三个参数为该Controller的视图存放路径
        me.add("/auth", AuthController.class, "/");            // 第三个参数省略时默认与第一个参数值相同
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        // 配置C3p0数据库连接池插件
        C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
        me.add(c3p0Plugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
        me.add(arp);

        //映射数据库Model
        arp.addMapping("Student","sid", Student.class);    // 映射 Student 表到 Student 模型
        arp.addMapping("Teacher","tid", Teacher.class);
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {

    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {

    }

}
