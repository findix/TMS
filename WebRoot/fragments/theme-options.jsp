<%--
  Created by IntelliJ IDEA.
  User: Sean
  Date: 2014/11/27
  Time: 8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="theme-panel hidden-xs hidden-sm">
    <div class="toggler">
    </div>
    <div class="toggler-close">
    </div>
    <div class="theme-options">
        <div class="theme-option theme-colors clearfix">
						<span>
						主题颜色 </span>
            <ul>
                <li class="color-default current tooltips" data-style="default" data-container="body"
                    data-original-title="default">
                </li>
                <li class="color-darkblue tooltips" data-style="darkblue" data-container="body"
                    data-original-title="Dark Blue">
                </li>
                <li class="color-blue tooltips" data-style="blue" data-container="body"
                    data-original-title="Blue">
                </li>
                <li class="color-grey tooltips" data-style="grey" data-container="body"
                    data-original-title="Grey">
                </li>
                <li class="color-light tooltips" data-style="light" data-container="body"
                    data-original-title="Light">
                </li>
                <li class="color-light2 tooltips" data-style="light2" data-container="body" data-html="true"
                    data-original-title="Light 2">
                </li>
            </ul>
        </div>
        <div class="theme-option">
						<span>
						布局 </span>
            <select class="layout-option form-control input-small">
                <option value="fluid" selected="selected">宽屏</option>
                <option value="boxed">普屏</option>
            </select>
        </div>
        <div class="theme-option">
						<span>
						头部 </span>
            <select class="page-header-option form-control input-small">
                <option value="fixed" selected="selected">固定</option>
                <option value="default">默认</option>
            </select>
        </div>
        <div class="theme-option">
						<span>
						边栏模式</span>
            <select class="sidebar-option form-control input-small">
                <option value="default" selected="selected">默认</option>
                <option value="fixed">固定</option>
            </select>
        </div>
        <div class="theme-option">
						<span>
						边栏菜单 </span>
            <select class="sidebar-menu-option form-control input-small">
                <option value="accordion" selected="selected">展开</option>
                <option value="hover">二级菜单</option>
            </select>
        </div>
        <div class="theme-option">
						<span>
						边栏样式 </span>
            <select class="sidebar-style-option form-control input-small">
                <option value="default" selected="selected">默认</option>
                <option value="light">浅色</option>
            </select>
        </div>
        <div class="theme-option">
						<span>
						边栏位置 </span>
            <select class="sidebar-pos-option form-control input-small">
                <option value="left" selected="selected">左</option>
                <option value="right">右</option>
            </select>
        </div>
        <div class="theme-option">
						<span>
						页底 </span>
            <select class="page-footer-option form-control input-small">
                <option value="default" selected="selected">默认</option>
                <option value="fixed">固定</option>
            </select>
        </div>
    </div>
</div>