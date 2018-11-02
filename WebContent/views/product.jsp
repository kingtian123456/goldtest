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
        <title>产品详情-ETENIOT LABS-智城慧商</title>
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
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/mydoc/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css">
        <link href="assets/mydoc/css/style.css" rel="stylesheet" type="text/css" />
        <!-- END THEME LAYOUT STYLES -->
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
                <!-- BEGIN TOP NAVIGATION MENU -->
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
                        <li class="nav-item start   active open">
                            <a href="${pageContext.request.contextPath}/SelectProductOne" class="nav-link nav-toggle">
                                <i class="fa fa-camera-retro"></i>
                                <span class="title">产品概况</span>
                                <span class="selected"></span>
                            </a>
                        </li>
                        <li class="nav-item start">
                            <a href="${pageContext.request.contextPath}/selectProductDevicesinfo" class="nav-link nav-toggle">
                                <i class="fa fa-cogs"></i>
                                <span class="title">设备管理</span>
                                <span class=""></span>
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
                                <a href="">产品概况</a>
                            </li>
                        </ul>
                        
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title">
                        <small></small>
                    </h3>
                    <!-- END PAGE TITLE-->
                    
                    <!-- row begin -->
                    <div class="row">
                        <div class="col-md-12 col-sm-12">
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-cursor font-dark hide"></i>
                                        <span class="caption-subject font-dark bold uppercase">${product.pro_name}</span>
                                    </div>
                                    <div class="actions">
                                        <a href="javascript:;" id="proMsgBtn" class="btn blue btn-outline btn-circle btn-sm active">
                                            <i class="fa fa-camera-retro"></i> 产品详情
                                        </a>
                                        <a onclick="Inshow()" class="btn  blue btn-outline btn-circle btn-sm">
                                            <i class="fa fa-edit"></i> 编辑
                                        </a>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <p class="li-top"></p>
                                    <div class="pro-li">
                                        <p>产品ID：<span>${product.pro_id}</span></p>
                                        <p>设备接入协议：<span>${product.ament.at_name}</span></p>
                                        <p>创建时间：<span><fmt:formatDate value="${product.pro_intime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
                                        <p>用户编号 (user_code)：<span>${product.user_code}</span></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- row end -->

                    <h3>产品概要分析</h3>
                    <!-- BEGIN ROW -->
                    <div class="row widget-row">
                        <div class="col-md-3">
                            <!-- BEGIN WIDGET THUMB -->
                            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
                                <h4 class="widget-thumb-heading">在线设备:<span id="zaixian"></span>台</h4>
                                <div class="widget-thumb-wrap">
                                    <i class="widget-thumb-icon bg-green icon-bulb"></i>
                                    <div class="widget-thumb-body">
                                        <span class="widget-thumb-subtitle">接入设备总数（台）</span>
                                        <a href="${pageContext.request.contextPath}/selectProductDevicesinfo">
                                        	<span class="widget-thumb-body-stat" data-counter="counterup" data-value="7,644">${product.dvg_devnum}</span>
                                    	</a>
                                    </div>
                                </div>
                            </div>
                            <!-- END WIDGET THUMB -->
                        </div>
                        <div class="col-md-3">
                            <!-- BEGIN WIDGET THUMB -->
                            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
                                <h4 class="widget-thumb-heading">今日新增:<span id="xinzeng"></span>条</h4>
                                <div class="widget-thumb-wrap">
                                    <i class="widget-thumb-icon bg-blue icon-bar-chart"></i>
                                    <div class="widget-thumb-body">
                                        <span class="widget-thumb-subtitle">数据点数（条）</span>
                                        <span class="widget-thumb-body-stat" id="zongnum" data-counter="counterup" data-value="7,644"></span>
                                    </div>
                                </div>
                            </div>
                            <!-- END WIDGET THUMB -->
                        </div>
                    </div>
                    <!-- END ROW -->
                    <h3>设备趋势分析</h3>
                    <!-- BEGIN ROW -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="portlet light portlet-fit bordered">
                                <div class="portlet-title">
                                    <form class="form-inline" role="form" id="minlikeChat"> 
                                        <div style="float:left;margin-right: 20px;margin-bottom: 15px;">
                                            <input type="text" class="form-control" name="begin" id="uworkstart"> -
                                            <input type="text" class="form-control" name="end" id="uworkfinish">
                                        </div>
                                        
                                        <button type="button" class="btn green-haze btn-outline btn-circle btn-md active" id="maxchat">查询</button>
                                        <div class="btn-group  pull-right">
                                            <button type="button" onclick="AddShow()" class="btn green btn-outline btn-circle btn-md active">添加设备</button>
                                        </div>
                                    </form>
                                </div>
                                <div class="portlet-body">
                                    <ul id="myTab" class="nav nav-tabs">
                                        <li class="active">
                                            <a href="#alldata" data-toggle="tab">
                                                 总数据
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#newdata" data-toggle="tab">新增数据</a>
                                        </li>
                                    </ul>
                                    <div id="myTabContent" class="tab-content">
                                        <div class="tab-pane fade in active" id="alldata">
                                           <div id="dateCharts1" style="width: 100%; height: 300px;"></div>
                                        </div>
                                        <div class="tab-pane fade" id="newdata">
                                            <div id="dateCharts2" style="width: 100%; height: 300px;"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END ROW -->
                    <h3>数据点上传趋势</h3>
                    <!-- BEGIN ROW -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="portlet light portlet-fit bordered">
                                <div class="portlet-title">
                                    <form class="form-inline" role="form" id="maxlikeChat">
                                        <div style="float:left;margin-right: 20px;margin-bottom: 15px;">
                                            <input type="text" class="form-control" id="uworkstart2" name="begin"> -
                                            <input type="text" class="form-control" id="uworkfinish2" name="end">
                                        </div>
                                        <button type="button" class="btn green-haze btn-outline btn-circle btn-md active" id="minchat">查看</button>
                                    </form>
                                </div>
                                <div class="portlet-body">
                                    <div id="dateCharts3" style="width: 100%; height: 300px;"></div>
                                    <div class="box-footer">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END ROW -->
                </div>
                <!-- 产品详情 模态框 begin -->
                <div class="modal fade bs-example-modal-md" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="proMsgModal">
                <div class="modal-dialog modal-md" role="document" style="margin: auto;top: 50%;transform: translate(0,-50%);">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">产品详情</h4>
                        </div>
                        <div class="modal-body">
                            
                            <form class="form-horizontal" role="form">
                                <div class="form-body">
                                    <h4>产品信息</h4>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">产品名称：</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">${product.pro_name}</p>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">产品ID：</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">${product.pro_id}</p>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">开发板的型号:</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">${product.fiy.clas_name}</p>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <!--  
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">产品简介：</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">抓到耗子报警</p>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    -->
                                    <hr style="background-color:#EFEFEF;height:1px;border:none;">

                                    <h4>技术参数</h4>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">操作系统：</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">Windows</p>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">联网方式：</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">NB-IoT</p>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">设备接入方式：</label>
                                        <div class="col-md-8">
                                            <p class="form-control-static">${product.ament.at_name}</p>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn blue" data-dismiss="modal">关闭</button>
                                </div>
                            </form>
                        </div>
                        
                    </div>
                </div>
            </div>
            <!-- 产品详情 模态框 end -->
               <!-- addDevice 模态框 begin -->
                <div class="modal fade bs-example-modal-md" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="addDeviceModal">
                <div class="modal-dialog modal-md" role="document" style="margin: auto;top: 50%;transform: translate(0,-50%);">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">接入设备<span class="small">${product.ament.at_name}协议</span></h4>
                        </div>
                        <div class="modal-body">
                            
                            <form class="form-horizontal" id="productmodel" role="form">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">设备名称</label>
                                        <div class="col-md-8">
                                            <input type="text" name="dev_name" class="form-control" placeholder="请输入设备名称">
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-3 control-label">设备IMSI</label>
                                        <div class="col-md-8">
                                            <input type="text" name="dev_vnum" class="form-control" placeholder="字符或者数字组成的字符串，最多不超过16个字符。">
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">设备的类型</label>
                                        <div class="col-md-8">
                                            <input type="text" name="dev_type" class="form-control" placeholder="请填写这是什么类型的设备">
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">转发的IP</label>
                                        <div class="col-md-8">
                                            <input type="text" name="dev_forward" class="form-control" placeholder="请填写IP地址">
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">转发的端口</label>
                                        <div class="col-md-8">
                                            <input type="text" name="dev_forport" class="form-control" placeholder="请填写端口号">
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button type="button" class="btn btn-primary" onclick="InsertSensor()">提交
                                    </button>
                                </div>
                            </form>
                        </div>
                        
                    </div>
                </div>
            </div>
            <!-- addDevice  模态框 end -->
            
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
                                            	<input type="hidden" name="pro_id" value="${product.pro_id}">
                                            	
                                                <label class="col-md-3 control-label">产品名称</label>
                                                <div class="col-md-8">
                                                    <input type="text" name="pro_name" class="form-control" placeholder="请输入产品名称" id="sitename" value="${product.pro_name}">
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">开发板类别</label>
                                                <div class="col-md-8">
                                                    <select name="pro_devb" id="labelprodevb1" class="form-control">
                                                        <option value="1">BC95开发板</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">当前产品进度</label>
                                                <div class="col-md-8">
                                                    <div class="mt-radio-inline">
                                                        <label class="mt-radio">
                                                            <input type="radio" name="pro_model" <c:if test="${product.pro_model == 1}">checked="checked"</c:if>  value="1" > 有创意想法
                                                            <span></span>
                                                        </label>
                                                        <label class="mt-radio">
                                                            <input type="radio" name="pro_model" <c:if test="${product.pro_model == 2}">checked="checked"</c:if> value="2" > 有产品原型
                                                            <span></span>
                                                        </label>
                                                        <label class="mt-radio">
                                                            <input type="radio" name="pro_model" <c:if test="${product.pro_model == 3}">checked="checked"</c:if>  value="3"> 有工程样机
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
                                                            <input type="radio" name="system" id="" value="option1" checked="checked"> Linux
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
                                                    	<label class="mt-radio">
                                                    		<input type="radio"  name="pro_contype" <c:if test="${product.ament.at_id == 1}">checked="checked"</c:if> value="1">UDP
                                                    		<span></span>
                                                    	</label>
                                                    	<label class="mt-radio">
                                                    		<input type="radio"  name="pro_contype" <c:if test="${product.ament.at_id == 2}">checked="checked"</c:if>  value="2">COAP
                                                    		<span></span>
                                                    	</label>
                                                    	<label class="mt-radio">
                                                    		<input type="radio"  name="pro_contype" <c:if test="${product.ament.at_id == 3}">checked="checked"</c:if>  value="3">电信平台
                                                    		<span></span>
                                                    	</label>
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
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->
        <div class="page-footer">
            <div class="page-footer-inner">版权信息 2017 湖南智城慧商信息技术有限公司
            </div>
            <div class="scroll-to-top">
                <i class="icon-arrow-up"></i>
                .
            </div>
        </div>
        <!-- END FOOTER -->
        <!-- BEGIN CORE PLUGINS -->
        <script src="assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="layer/layer.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        <script src="assets/mydoc/js/jquery.datetimepicker.full.min.js" type="text/javascript"></script>
        <!-- BEGIN echarts插件 -->
        <script src="assets/mydoc/js/echarts.common.min.js" type="text/javascript"></script>
        <!-- END echarts插件 -->
        <!-- BEGIN 自定义JS -->
        <script src="assets/mydoc/js/product.js" type="text/javascript" defer></script>
        <!-- end 自定义JS -->
    </body>

</html>