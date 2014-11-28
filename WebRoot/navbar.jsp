<%--
  Created by IntelliJ IDEA.
  User: Sean
  Date: 2014/11/26
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-header navbar navbar-fixed-top">
    <!-- BEGIN HEADER INNER -->
    <div class="page-header-inner">
        <!-- BEGIN LOGO -->
        <div class="page-logo">
            <a href="/">
                <img src="assets/admin/layout/img/logo.png" alt="logo" class="logo-default"/>
            </a>

            <div class="menu-toggler sidebar-toggler hide">
                <!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
            </div>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse"
           data-target=".navbar-collapse">
        </a>
        <!-- END RESPONSIVE MENU TOGGLER -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <div class="top-menu">
            <ul class="nav navbar-nav pull-right">
                <!-- BEGIN USER LOGIN DROPDOWN -->
                <li class="dropdown dropdown-user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                       data-close-others="true">
					<span class="username username-hide-on-mobile"><span class="glyphicon glyphicon-user"></span> 欢迎你，
					${sessionScope["name"]} </span>
                        <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a data-toggle="modal" href="#changeNameModal">
                                <i class="icon-lock"></i> 修改密码 </a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a href="/auth/logout">
                                <i class="icon-key"></i> 安全退出 </a>
                        </li>
                    </ul>
                </li>
                <!-- END USER LOGIN DROPDOWN -->
            </ul>
        </div>
        <!-- END TOP NAVIGATION MENU -->
    </div>
    <!-- END HEADER INNER -->
</div>
<div id="changeNameModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h3 class="modal-title" id="myModalLabel">修改密码</h3>
            </div>
            <div class="modal-body">
                <form class="form-inline" action="" method="post">
                    <input name="password" type="text" class="form-control" placeholder="请输入新密码"/>
                    <input id="changePasswd" value="修改" class="btn btn-success" style="margin-left: 10px;"
                           type="button" onclick="changePassword()"/>
                    <button data-dismiss="modal" class="btn btn-danger" style="margin-left: 10px">取消
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>