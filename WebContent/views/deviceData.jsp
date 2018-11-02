<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>设备数据-ETENIOT LABS-智城慧商</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="../assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="../assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="../assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="../assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/mydoc/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css">
         <link href="../assets/mydoc/css/style.css" rel="stylesheet" type="text/css" />
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" /> 
        <style type="text/css">
        .page_div {
            margin-top: 20px;
            margin-bottom: 20px;
            font-size: 15px;
            font-family: "microsoft yahei";
            color: #666666;
            margin-right: 10px;
            padding-left: 20px;
            box-sizing: border-box;
            text-align: center;
        }

        .page_div a {
            min-width: 30px;
            height: 28px;
            border: 1px solid #dce0e0 !important;
            text-align: center;
            margin: 0 4px;
            cursor: pointer;
            line-height: 28px;
            color: #666666;
            font-size: 13px;
            display: inline-block;
        }

        .page_div .current {
            background-color: #0073A9;
            border-color: #0073A9;
            color: #FFFFFF;
        }
        .table>tbody>tr>td{word-break: break-word !important;}
    </style>
    </head>
    <!-- END HEAD -->

    <body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-content-white">
        <!-- BEGIN HEADER -->
        <div class="page-header navbar navbar-fixed-top">
            <!-- BEGIN HEADER INNER -->
            <div class="page-header-inner ">
                <!-- BEGIN LOGO -->
                <div class="page-logo">
                    <a href="#">
                        <img src="../assets/layouts/layout/img/logo.png" alt="logo" class="logo-default" /> </a>
                    <div class="menu-toggler sidebar-toggler">
                        <span></span>
                    </div>
                </div>
                <!-- END LOGO -->
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
                    <span></span>
                </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN TOP NAVIGATION MENU -->
                <div class="top-menu">
                    
                    <ul class="nav navbar-nav pull-right">
                        
                        <!-- BEGIN USER LOGIN DROPDOWN -->
                        <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                        <li class="dropdown dropdown-user">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <img alt="" class="img-circle" src="../assets/layouts/layout/img/avatar3_small.jpg" />
                                <span class="username username-hide-on-mobile">管理员</span>
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-default">
                                <li>
                                    <a href="${pageContext.request.contextPath}/deleteSession">
                                        <i class="icon-key"></i>退出登录</a>
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
        <!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <div class="page-sidebar-wrapper">
                <!-- BEGIN SIDEBAR -->
                <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                <div class="page-sidebar navbar-collapse collapse">
                    <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
                        <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
                        <li class="sidebar-toggler-wrapper hide">
                            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                            <div class="sidebar-toggler">
                                <span></span>
                            </div>
                            <!-- END SIDEBAR TOGGLER BUTTON -->
                        </li>
                        
                        <li class="nav-item start nav-item-index ">
                            <a href="${pageContext.request.contextPath}/views/indexs.jsp" class="nav-link nav-toggle">
                                <i class="icon-home"></i>
                                <span class="title">产品首页</span>
                                <span class=""></span>
                            </a>
                        </li>
                        <li class="nav-item start  ">
                            <a href="${pageContext.request.contextPath}/SelectProductOne" class="nav-link nav-toggle">
                                <i class="fa fa-camera-retro"></i>
                                <span class="title">产品概况</span>
                                <span class=""></span>
                            </a>
                        </li>
                        <li class="nav-item start active open">
                            <a href="${pageContext.request.contextPath}/selectProductDevicesinfo" class="nav-link nav-toggle">
                                <i class="fa fa-cogs"></i>
                                <span class="title">设备管理</span>
                                <span class="selected"></span>
                            </a>
                        </li>
                        <!--  
                        <li class="nav-item start">
                            <a href="appManage.html" class="nav-link nav-toggle">
                                <i class="fa fa-suitcase"></i>
                                <span class="title">应用管理</span>
                                <span class=""></span>
                            </a>
                        </li>
                        -->
                    </ul>
                    <!-- END SIDEBAR MENU -->
                    <!-- END SIDEBAR MENU -->
                </div>
                <!-- END SIDEBAR -->
            </div>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <!-- BEGIN PAGE HEADER-->
                    
                    <!-- BEGIN PAGE BAR -->
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
                            <li>
                                <a href="">设备管理</a>
                            </li>
                        </ul>
                        
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title">设备管理
                        <a href="javascript:history.go(-1)" class="btn green btn-outline btn-circle btn-md active pull-right">返回</a>
                    </h3>
                    <!-- END PAGE TITLE-->
     
                    <!-- row end -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="portlet light portlet-fit bordered">
                                <div class="portlet-body">
                                    <div class="table-scrollable ">
                                        <table class="table table-striped table-hover" id="mytab" TABLE-LAYOUT: fixed;>
                                        	<thead>
	                                        	<tr>
	                                        		<th>数据状态</th><th>数据ID</th><th class="col-md-6">数据内容</th><th>设备编号</th><th>用户编号</th><th>时间</th>
	                                        	</tr>  	
                                        	</thead>
                                        	<tbody>
                                    		<tbody>
                                        </table>
                                    </div>
                                    <div class="page_div" id="page"></div>
                                </div>
                                
                            </div>
                        </div>
                    </div> 
                </div>
                <!-- END CONTENT BODY -->

            </div>
        </div>   
        <div class="page-footer">
            <div class="page-footer-inner">版权信息 2017 湖南智城慧商信息技术有限公司
            </div>
            <div class="scroll-to-top">
                <i class="icon-arrow-up"></i>
                .
            </div>
        </div>
        <!-- END FOOTER -->
        <!--[if lt IE 9]>
        <script src="../assets/global/plugins/respond.min.js"></script>
        <script src="../assets/global/plugins/excanvas.min.js"></script> 
        <![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="../assets/mydoc/js/paging.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="../assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="../assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        <script src="../assets/mydoc/js/jquery.datetimepicker.full.min.js" type="text/javascript"></script>
        <!-- BEGIN echarts插件 -->
        <!-- <script src="../assets/mydoc/js/echarts.common.min.js" type="text/javascript"></script> -->
        <!-- END echarts插件 -->
        <!-- BEGIN 自定义JS -->
        <script src="../assets/mydoc/js/deviceData.js" type="text/javascript" defer></script>
        <!-- end 自定义JS -->
    </body>

</html>