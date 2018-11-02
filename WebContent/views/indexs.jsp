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
        <title>产品首页-ETENIOT LABS-智城慧商</title>
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
        <link href="../assets1/css/layui.css" rel="stylesheet" media="all">
        <link href="../assets/mydoc/css/style.css" rel="stylesheet" type="text/css" />
        <!-- END THEME LAYOUT STYLES -->
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
                                    <a href="../deleteSession">
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
        	<div class="page-sidebar-wrapper"> 
                <div class="page-sidebar navbar-collapse collapse"> 
                    <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px"> 
                        <li class="sidebar-toggler-wrapper hide">
                            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                            <div class="sidebar-toggler">
                                <span></span>
                            </div>
                            <!-- END SIDEBAR TOGGLER BUTTON -->
                        </li>
                        <li class="nav-item start">
                            <a href="index.html" class="nav-link nav-toggle">
                                <i class="icon-home"></i>
                                <span class="title">用户入门</span>
                                <span class="selected"></span>
                            </a>
                        </li>
                        <li class="nav-item start">
                            <a href="../getCommodityInfos" class="nav-link nav-toggle">
                                <i class="fa fa-camera-retro"></i>
                                <span class="title">实验模块</span>
                                <span class=""></span>
                            </a>
                        </li>    
						<li class="nav-item start">
                            <a href="devblog.html" class="nav-link nav-toggle">
                                <i class="fa fa-camera-retro"></i>
                                <span class="title">开发博客</span>
                                <span class=""></span>
                            </a>
                        </li>
                        <li class="nav-item start nav-item-index   active open">
                            <a href="indexs.jsp" >
                           		<i class="fa fa-camera-retro"></i>
                                <span class="title">产品首页</span>
                                <span class=""></span> 
                            </a>
                        </li>						
                        <li class="nav-item start">
                            <a href="about.html" class="nav-link nav-toggle">
                                <i class="fa fa-suitcase"></i>
                                <span class="title">关于 ETENIOT LABS</span>
                                <span class=""></span>
                            </a>
                        </li>
                        <c:if test="${sessionScope.USER.user_authority == 1}">
                        <li class="nav-item start">
                            <a href="management.jsp" class="nav-link nav-toggle">
                                <i class="fa fa-suitcase"></i>
                                <span class="title">分类管理</span>
                                <span class=""></span>
                            </a>
                        </li>
                        </c:if>
                        <c:if test="${sessionScope.USER.user_authority == 1}">
                        <li class="nav-item start">
                            <a href="activation.jsp" class="nav-link nav-toggle">
                                <i class="fa fa-suitcase"></i>
                                <span class="title">序列码管理</span>
                                <span class=""></span>
                            </a>
                        </li>
                        </c:if>
                        <li class="nav-item start">
                            <a href="datadownload.html" class="nav-link nav-toggle">
                                <i class="fa fa-suitcase"></i>
                                <span class="title">资料下载区</span>
                                <span class=""></span>
                            </a>
                        </li>
                    </ul>
                    <!-- END SIDEBAR MENU --> 
                </div>
                <!-- END SIDEBAR -->
            </div>
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <!-- BEGIN PAGE HEADER-->
                    
                    <!-- BEGIN PAGE BAR -->
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
                            <li>
                                <a href="#">首页</a>
                            </li>
                            
                        </ul>
                        
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title">
                        <small><a href="#" onclick="InPoroductshow()" class="btn green btn-outline btn-circle btn-md active pull-right">新增产品</a></small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </h3>
                    <!-- END PAGE TITLE-->
                    
                    <!-- row begin -->
                    <div class="row" id="mytab">
                    </div>
                    
                    <!-- row end -->
                    <!-- 分页 -->
             		<div class="page_div" id="page"></div>
             		
            <!-- editModal模态框 end 新增-->
        	<div class="modal fade bs-example-modal-md" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="editModal">
                <div class="modal-dialog modal-md" role="document" style="margin: auto;top: 50%;transform: translate(0,-50%);">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">新增产品<span class="small"></span></h4>
                        </div>
                        <div class="modal-body">
							<form class="form-horizontal" role="form" id="insertmaxlikeChat">
                                        <div class="form-body">
                                            <div class="m-heading-1 border-green m-bordered"><h3>产品信息</h3></div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">产品名称</label>
                                                <div class="col-md-8">
                                                    <input type="text" name="pro_name" class="form-control"  placeholder="请输入产品名称"  value="">
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">开发板类别</label>
                                                <div class="col-md-8">
                                                    <select name="pro_devb" id="labelprodevb" class="form-control">
                                                        
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">当前产品进度</label>
                                                <div class="col-md-8">
                                                    <div class="mt-radio-inline">
                                                        <label class="mt-radio">
                                                            <input type="radio" name="pro_model" id="" value="1" > 有创意想法
                                                            <span></span>
                                                        </label>
                                                        <label class="mt-radio">
                                                            <input type="radio" name="pro_model" id="" value="2" > 有产品原型
                                                            <span></span>
                                                        </label>
                                                        <label class="mt-radio">
                                                            <input type="radio" name="pro_model" id="" value="3"> 有工程样机
                                                            <span></span>
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="m-heading-1 border-green m-bordered"><h3>技术参数</h3></div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">操作系统</label>
                                               <div class="col-md-8">
                                                    <div class="mt-radio-inline">
                                                        <label class="mt-radio">
                                                            <input type="radio" name="system" id="" value="option1" checked=""> Linux
                                                            <span></span>
                                                        </label>
                                                        <label class="mt-radio">
                                                            <input type="radio" name="system" id="" value="option2"> Android
                                                            <span></span>
                                                        </label>
                                                        <label class="mt-radio">
                                                            <input type="radio" name="system" id="" value=""> 其他
                                                            <span></span>
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">接入方式</label>
                                                <div class="col-md-8">
                                                    <div class="mt-radio-inline" id="groupRadio">
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9">
                                                    <button type="button" class="btn btn-primary" onclick="InsertPorduct()">提交</button>
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                        </div>
                        
                    </div>
                </div>
            </div>
            <!-- editModal模态框 end -->
            
            <!-- updateModal模态框 end 修改-->
        	<div class="modal fade bs-example-modal-md" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="updateModal">
                <div class="modal-dialog modal-md" role="document" style="margin: auto;top: 50%;transform: translate(0,-50%);">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">修改产品信息<span class="small"></span></h4>
                        </div>
                        <div class="modal-body">
							<form class="form-horizontal" role="form" id="updatemaxlikeChat">
                                        <div class="form-body">
                                            <div class="m-heading-1 border-green m-bordered"><h3>产品信息</h3></div>
                                            <div class="form-group">
                                            	<input type="hidden" name="pro_id" value="">
                                            	
                                                <label class="col-md-3 control-label">产品名称</label>
                                                <div class="col-md-8">
                                                    <input type="text" name="pro_name1" class="form-control" placeholder="请输入产品名称" id="sitename" value="">
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">开发板类别</label>
                                                <div class="col-md-8">
                                                    <select name="pro_devb1" id="labelprodevb1" class="form-control">
                                                        
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">当前产品进度</label>
                                                <div class="col-md-8">
                                                    <div class="mt-radio-inline">
                                                        <label class="mt-radio">
                                                            <input type="radio" name="pro_model1"  value="1" > 有创意想法
                                                            <span></span>
                                                        </label>
                                                        <label class="mt-radio">
                                                            <input type="radio" name="pro_model1"  value="2" > 有产品原型
                                                            <span></span>
                                                        </label>
                                                        <label class="mt-radio">
                                                            <input type="radio" name="pro_model1"  value="3"> 有工程样机
                                                            <span></span>
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="m-heading-1 border-green m-bordered"><h3>技术参数</h3></div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">操作系统</label>
                                               <div class="col-md-8">
                                                    <div class="mt-radio-inline">
                                                        <label class="mt-radio">
                                                            <input type="radio" name="system" id="" value="option1" checked=""> Linux
                                                            <span></span>
                                                        </label>
                                                        <label class="mt-radio">
                                                            <input type="radio" name="system" id="" value="option2"> Android
                                                            <span></span>
                                                        </label>
                                                        <label class="mt-radio">
                                                            <input type="radio" name="system" id="" value=""> 其他
                                                            <span></span>
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">接入方式</label>
                                                <div class="col-md-8">
                                                    <div class="mt-radio-inline" id="groupRadio1">
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9">
                                                    <button type="button" class="btn btn-primary" onclick="UpdatesPorduct()">提交</button>
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                        </div>
                        
                    </div>
                </div>
            </div>
            <!-- updateModal模态框 end -->

                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
          </div>  
        </div>
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->
        <div class="page-footer">
            <div class="page-footer-inner">版权信息 2018 湖南智城慧商信息技术有限公司
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
        <script src="../layer/layer.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="../assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="../assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        <!-- BEGIN echarts插件 -->
        <!-- <script src="../assets/mydoc/js/echarts.common.min.js" type="text/javascript"></script> -->
        <!-- END echarts插件 -->
        <!-- BEGIN 自定义JS -->
        <script src="../assets/mydoc/js/index.js" type="text/javascript" defer></script>
        <!-- end 自定义JS -->
    </body>

</html>