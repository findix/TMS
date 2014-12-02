<%--
  Created by IntelliJ IDEA.
  User: Sean
  Date: 2014/11/26
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-sidebar-wrapper">
    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
    <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
    <div class="page-sidebar navbar-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="200">
            <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
            <li class="sidebar-toggler-wrapper">
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                <div class="sidebar-toggler">
                </div>
                <!-- END SIDEBAR TOGGLER BUTTON -->
            </li>
            <!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
            <li class="sidebar-search-wrapper">
                <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
                <!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
                <!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->
                <form class="sidebar-search " action="extra_search.html" method="POST">
                    <a href="javascript:;" class="remove">
                        <i class="icon-close"></i>
                           </a>
                           <div class="input-group">
                                <input type="text" class="form-control" placeholder="搜索...">
                                    <span class="input-group-btn">
                                    <a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
                                    </span>
                            </div>
                        </form>
                        <!-- END RESPONSIVE QUICK SEARCH FORM -->
            </li>

            <li class="start active open">
                <a href="javascript:;">
                    <i class="icon-home"></i>
                    <span class="title">主页</span>
                    <span class="arrow open"></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="/">
                            <i class="icon-bar-chart"></i>
                            主页</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="icon-basket"></i>
                    <span class="title">课程信息管理</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="/course/info">
                            <i class="icon-home"></i>
                            查看课程信息</a>
                    </li>
                    <li>
                        <a href="/course/upload">
                            <i class="icon-basket"></i>
                            上传课程信息</a>
                    </li>

                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="icon-rocket"></i>
                    <span class="title">教师信息管理</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="/teacher/info">
                           查看教师信息</a>
                    </li>
                    <li>
                        <a href="/teacher/upload">
                            上传教师信息</a>
                    </li>
                </ul>
            </li>
            <!-- BEGIN FRONTEND THEME LINKS -->
            <li>
                <a href="javascript:;">
                    <i class="icon-star"></i>
					<span class="title">
					教学大纲 </span>
					<span class="arrow">
					</span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="/syllabus/index">
							查看教学大纲
                        </a>
                    </li>
                    <li >
                        <a href="/syllabus/distribution">
							分配教学大纲
                        </a>
                    </li>
                    <li >
                        <a href="/syllabus/write">
							填写教学大纲
                        </a>
                    </li>
                    <li>
                        <a href="/syllabus/check">
							审核教学大纲
                        </a>
                    </li>
                </ul>
            </li>
            <!-- END FRONTEND THEME LINKS -->
            <li>
                <a href="javascript:;">
                    <i class="icon-diamond"></i>
                    <span class="title">教学进度表</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="/schedule/index">
                            查看教学进度表</a>
                    </li>
                    <li>
                        <a href="/schedule/distribution">
                           分配教学进度表</a>
                    </li>
                    <li>
                        <a href="/schedule/write">
                            填写教学进度表</a>
                    </li>
                    <li>
                        <a href="/schedule/check">
                            审核教学进度表</a>
                    </li>

                </ul>
            </li>
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
</div>
