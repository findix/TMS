package test.cn.findix.tms.model; 

import cn.findix.tms.model.Student;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Student Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮһ�� 27, 2014</pre> 
* @version 1.0 
*/ 
public class StudentTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: isExisted(String username, String password) 
* 
*/ 
@Test
public void testIsExisted() throws Exception {
    String sql="select sid,sname from Student where sid=20113092 and password=123456";
    Student s = Student.dao.findFirst(sql);
    System.out.println(s.getStr("sname"));
} 


} 
