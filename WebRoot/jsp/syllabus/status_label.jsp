<%--
  Created by IntelliJ IDEA.
  User: Sean
  Date: 2014/12/3
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<td id="label">
    <c:if test="${course.status=='未分配'}">
        <span class="label label-sm label-warning">
            ${course.status}
        </span>
    </c:if>
    <c:if test="${course.status=='已指派' || course.status=='已通过'}">
        <span class="label label-sm label-success">
            ${course.status}
        </span>
    </c:if>
    <c:if test="${course.status=='不通过'}">
        <span class="label label-sm label-danger">
            ${course.status}
        </span>
    </c:if>
    <c:if test="${course.status=='待审核'}">
        <span class="label label-sm label-primary">
            ${course.status}
        </span>
    </c:if>
    <c:if test="${course.status!='未分配' && course.status!='已指派' && course.status!='已通过' && course.status!='不通过' && course.status!='待审核'}">
        <span class="label label-sm label-info">
            ${course.status}
        </span>
    </c:if>
</td>
