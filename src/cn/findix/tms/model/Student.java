package cn.findix.tms.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by Sean on 2014/11/27.
 */
public class Student extends Model<Student> {
    public static final Student DAO = new Student();

    public boolean isExisted(String id, String password) {
        String sql = "select sname from Student where sid=? and password=?";
        return DAO.findFirst(sql, id, password) != null;
    }
}
