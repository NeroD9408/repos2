<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>--%>
<!-- 页面头部 -->
<header class="main-header">
    <!-- Logo -->
    <a href="all-admin-index.html" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>数据</b></span> <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>数据</b>后台管理</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas"
           role="button"> <span class="sr-only">Toggle navigation</span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">

                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <c:if test="${empty admin}">
                            <img src="${pageContext.request.contextPath}/img/user2-160x160.jpg" class="user-image"
                                 alt="User Image">
                        </c:if>
                        <c:if test="${not empty admin}">
                            <img src="${pageContext.request.contextPath}/headImg/${admin.imgpath}" class="user-image"
                                 alt="User Image">
                        </c:if>

                        <span class="hidden-xs">
							<security:authentication property="principal.username"/>
					</span>

                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <c:if test="${empty admin}">
                                <img src="${pageContext.request.contextPath}/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                            </c:if>
                            <c:if test="${not empty admin}">
                                <img src="${pageContext.request.contextPath}/headImg/${admin.imgpath}" class="img-circle" alt="User Image">
                            </c:if>
                        </li>

                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="${pageContext.request.contextPath}/pages/update.jsp"
                                   class="btn btn-default btn-flat">修改头像</a>
                            </div>
                            <div class="pull-right">
                                <a href="${pageContext.request.contextPath}/admin/logout"
                                   class="btn btn-default btn-flat">注销</a>
                            </div>
                        </li>
                    </ul>
                </li>

            </ul>
        </div>
    </nav>
</header>
<!-- 页面头部 /-->