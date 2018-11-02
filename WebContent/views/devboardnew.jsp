<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>添加商品-ETENIOT LABS-智城慧商</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES --> 
        <link href="../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="../assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css" rel="stylesheet" type="text/css" />
        <link href="../assets/global/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="../assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="../assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="../assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="../assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="../assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" /> </head>
    <!-- END HEAD -->

    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
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
                                <span class=""></span>
                            </a>
                        </li>
                        <li class="nav-item start nav-item-index active open">
                            <a href="../getCommodityInfos" class="nav-link nav-toggle">
                                <i class="fa fa-camera-retro"></i>
                                <span class="title">实验模块</span>
                                <span class="selected"></span>
                            </a>
                        </li>
                        <li class="nav-item start">
                            <a href="devblog.html" class="nav-link nav-toggle">
                                <i class="fa fa-camera-retro"></i>
                                <span class="title">开发博客</span>
                                <span class=""></span>
                            </a>
                        </li>
                        <li class="nav-item start">
                            <a href="indexs.jsp" class="nav-link nav-toggle">
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
                            <a href="views/datadownload.html" class="nav-link nav-toggle">
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
                                <a href="index.html">添加新内容</a> 
                            </li> 
                        </ul> 
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title">         </h3>
                    <!-- END PAGE TITLE-->
                    <!-- END PAGE HEADER-->
                    <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN VALIDATION STATES-->
                            <div class="portlet light portlet-fit portlet-form bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-settings font-dark"></i>
                                        <span class="caption-subject font-dark sbold uppercase">正在产生一篇新的内容</span>
                                    </div> 
                                </div>
                                <form enctype="multipart/form-data" action="../commodityUploads" method="post" id="Commodtiyform" class="form-horizontal">
                                <div class="portlet-body">
                                    <!-- BEGIN FORM-->
                                        <div class="form-body">
                                            <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button> 请检查您录入的内容是否完成。 </div>
                                            <div class="alert alert-success display-hide">
                                                <button class="close" data-close="alert"></button> 验证成功！</div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">标题
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" name="comTitle" data-required="1" class="form-control" /> </div>
                                            </div>
                                            
                                            <div class="form-group">
                                                <label class="control-label col-md-3">关键词
                                                	<span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input name="comKeyword" type="text" class="form-control" /> </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">链接
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" name="comLinkurl" data-required="1" class="form-control" /> </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">栏目
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <select class="form-control select2me" name="sortId" id="selectoptin">
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">简介</label>
                                                <div class="col-md-9">
                                                    <textarea name="comIntro" data-provide="markdown" rows="10" data-error-container="#editor_error"></textarea>
                                                    <div id="editor_error"> </div>
                                                </div>
                                            </div>
                                             
                                            <div class="form-group last">
                                                <label class="control-label col-md-3">产品图片
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-9">
                                                    <!--<textarea class="ckeditor form-control" name="editor2" rows="6" data-error-container="#editor2_error"></textarea>-->
                                                    <input type="file" name="file" data-required="1"/></div>
                                                    <div id="editor2_error"> </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9">
                                                    <button type="submit"  class="btn green">提交</button>
                                                    <button type="button" class="btn default">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <!-- END FORM-->
                                </div>
                                <!-- END VALIDATION STATES-->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT --> 
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->
        <div class="page-footer">
            <div class="page-footer-inner">版权信息 2017-2018 湖南智城慧商信息技术有限公司
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
        <script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="../assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/ckeditor/ckeditor.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/bootstrap-markdown/lib/markdown.js" type="text/javascript"></script>
        <script src="../assets/global/plugins/bootstrap-markdown/js/bootstrap-markdown.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="../assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="../assets/pages/scripts/form-validation.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="../assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="../assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="../assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        <script src="../assets/mydoc/js/devboardnew.js"  type="text/javascript" ></script>
    </body>

</html>