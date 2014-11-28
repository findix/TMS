package cn.findix.tms.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by Sean on 2014/11/27.
 */
public class Student extends Model<Student> {
    public static final Student dao = new Student();

    public boolean isExisted(String id, String password) {
        String sql = "select sid,sname from Student where sid=? and password=?";
        if (dao.findFirst(sql,id,password) == null) {
            return false;
        } else {
            return true;
        }
    }
}
