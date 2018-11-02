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
        <title>实验模块-ETENIOT LABS-智城慧商</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
        <link href="assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        
        <link href="assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="assets/global/plugins/cubeportfolio/css/cubeportfolio.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN PAGE LEVEL STYLES -->
        <link href="assets/pages/css/portfolio.min.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css" />
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
                        <img src="assets/layouts/layout/img/logo.png" alt="logo" class="logo-default" /> </a>
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
               <div class="top-menu">
                    
                    <ul class="nav navbar-nav pull-right">
                        
                        <!-- BEGIN USER LOGIN DROPDOWN -->
                        <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                        <li class="dropdown dropdown-user">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <img alt="" class="img-circle" src="assets/layouts/layout/img/avatar3_small.jpg" />
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
                            <a href="views/index.html" class="nav-link nav-toggle">
                                <i class="fa fa-camera-retro"></i>
                                <span class="title">用户入门</span>
                                <span class=""></span>
                            </a>
                        </li>
                        
                        <li class="nav-item start nav-item-index active open">
                            <a href="${pageContext.request.contextPath}/getCommodityInfos" class="nav-link nav-toggle">
                                <i class="icon-home"></i>
                                <span class="title">实验模块</span>
                                <span class="selected"></span>
                            </a>
                        </li>
                           
						<li class="nav-item start">
                            <a href="views/devblog.html" class="nav-link nav-toggle">
                                <i class="fa fa-camera-retro"></i>
                                <span class="title">开发博客</span>
                                <span class=""></span>
                            </a>
                        </li>
                        <c:if test="${sessionScope.USER.user_auth == 1}">
                        <li class="nav-item start">
                            <a href="views/indexs.jsp" class="nav-link nav-toggle">
                           		<i class="fa fa-camera-retro"></i>
                                <span class="title">产品首页</span>
                                <span class=""></span> 
                            </a>
                        </li>	
                        </c:if> 					
                        <li class="nav-item start">
                            <a href="views/about.html" class="nav-link nav-toggle">
                                <i class="fa fa-suitcase"></i>
                                <span class="title">关于 ETENIOT LABS</span>
                                <span class=""></span>
                            </a>
                        </li>
                        <c:if test="${sessionScope.USER.user_authority == 1}">
                        <li class="nav-item start">
                            <a href="views/management.jsp" class="nav-link nav-toggle">
                                <i class="fa fa-suitcase"></i>
                                <span class="title">分类管理</span>
                                <span class=""></span>
                            </a>
                        </li>
                        </c:if>
                        <c:if test="${sessionScope.USER.user_authority == 1}">
                        <li class="nav-item start">
                            <a href="views/activation.jsp" class="nav-link nav-toggle">
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
                                <a href="">实验模块</a>
                            </li>
                        </ul>                        
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title"> 产品列表
                        <small>物联网开发板及配件</small>
                        <c:if test="${sessionScope.USER.user_authority == 1}">
                        	<small id="devboardUser"><a href="views/devboardnew.jsp" class="btn green btn-outline btn-circle btn-md active pull-right">添加商品</a></small>
                        </c:if>
                    </h3>
                    <!-- END PAGE TITLE-->
                    <!-- END PAGE HEADER-->
                    <div class="portfolio-content portfolio-1" id="devboardComm">
                        <div id="js-filters-juicy-projects" class="cbp-l-filters-button">
                            <div data-filter="*" id="suoyou" class="cbp-filter-item-active cbp-filter-item btn dark btn-outline uppercase"> 所有产品
                                <div class="cbp-filter-counter"></div>
                            </div>
                            <c:forEach var="sort" items="${SortList}">
                            	<div data-filter=".${sort.sortDir}" class="cbp-filter-item btn dark btn-outline uppercase"> ${sort.sortName}
                               		<div class="cbp-filter-counter"></div>
                            	</div>
                            </c:forEach>
                        </div>
                        <div id="js-grid-juicy-projects" class="cbp">
                        <c:forEach var="com" items="${Commodity}">
                        	<div class="cbp-item ${com.sort.sortDir}">
                        		<c:if test="${sessionScope.USER.user_authority == 1}">
                        			<a onclick="DeleteComm(${com.comId})" style="width:100px;height:30px;font-weight: bold;color: #000;">删除</a>
                                	<a href="views/devboardeditor.jsp?comId=${com.comId}" class="" style="width:100px;height:30px;font-weight: bold;color: #000;">编辑</a>
                                </c:if>
                                <div class="cbp-caption">
                                    <div class="cbp-caption-defaultWrap" style="background:white;">
                                        <img src="${com.comPrinturl}" alt="">
                                    </div>
                                    <div class="cbp-caption-activeWrap">
                                        <div class="cbp-l-caption-alignCenter">
                                            <div class="cbp-l-caption-body">
                                                <a href="views/devboardview.html?comurl=${com.comLinkurl}" class="cbp-singlePage cbp-l-caption-buttonLeft btn red uppercase btn red uppercase">购买</a>
                                                <a href="${com.comPrinturl}" class="cbp-lightbox cbp-l-caption-buttonRight btn red uppercase btn red uppercase" data-title="Dashboard<br>by Paul Flavius Nechita">显示</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="cbp-l-grid-projects-title uppercase text-center uppercase text-center">${com.comTitle}</div>
                                <div class="cbp-l-grid-projects-desc uppercase text-center uppercase text-center">${com.comKeyword}</div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>
            <!-- END CONTENT -->            
        </div>
        </div>
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
<script src="assets/global/plugins/respond.min.js"></script>
<script src="assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="assets/global/plugins/cubeportfolio/js/jquery.cubeportfolio.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="assets/pages/scripts/portfolio-1.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <script type="text/javascript">
        	function DeleteComm(comid){
        		if(window.confirm('是否确定删除商品？删除商品后，商品及商品相关的所有数据将从平台消失，并且不可恢复。请谨慎操作.')){
        			$.ajax({
            			url:"deleteCommodityOne",
            			type:"post",
            			data:{"comId":comid},
            			success:function(date){
            				var  reuslt = JSON.parse(date);
            				if(reuslt){
            					window.location.reload();
            				}else{
            					alert("删除文件失败！！！");
            				}
            			}
            		})
        		}
        	}
        </script>
        <!-- END THEME LAYOUT SCRIPTS -->
    </body>

</html>