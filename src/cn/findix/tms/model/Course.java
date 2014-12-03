package cn.findix.tms.model;

import cn.dreampie.web.model.Model;

import java.util.List;

/**
 * Created by Sean on 2014/11/28.
 */
public class Course extends Model<Course> {
    public static final Course DAO = new Course();

    public String getDepartmentName() {
        return Department.DAO.findById(get("did")).get("dname");
    }

    public String getMajorName() {
        return Major.DAO.findById(get("mid")).get("mname");
    }

    public boolean isExisted(String id) {
        String sql = "select cname from Course where cid=?";
        return DAO.findFirst(sql, id) != null;
    }

    public List<Course> findMyAll() {
        return find("SELECT course.cid, course.cname, course.cenglish, course.credit, course.did, department.dname, course.mid,major.mname FROM course, department, major WHERE course.did = department.did AND course.mid=major.mid ORDER BY course.cid ASC");
    }

    public List<Course> findStudentAll(String sid) {
        return find("SELECT course.cid, course.cname, course.cenglish, course.credit, course.mid, course.did, course.`status`, course.tid, department.dname FROM course INNER JOIN department ON course.did = department.did, student WHERE course.mid = student.mid AND student.sid = ?", sid);
    }
}
