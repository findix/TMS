package cn.findix.tms.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by Sean on 2014/11/27.
 */
public class Teacher extends Model<Teacher> {
    public static final Teacher dao=new Teacher();

    public boolean isExisted(String id, String password) {
        String sql = "select ,tname from Teacher where tid=? and password=?";
        if (dao.findFirst(sql,id,password) == null) {
            return false;
        } else {
            return true;
        }
    }
}
