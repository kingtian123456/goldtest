<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>产品编辑-ETENIOT LABS-智城慧商</title>
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
         <link href="assets/mydoc/css/style.css" rel="stylesheet" type="text/css" />
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico" /> 
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
                <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                <div class="page-sidebar navbar-collapse collapse">
                    <!--  
                    <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">                        
                    	<li class="sidebar-toggler-wrapper hide">
                            <div class="sidebar-toggler">
                                <span></span>
                            </div>
                        </li>
                        
                        <li class="nav-item start nav-item-index   active open">
                            <a href="#" class="nav-link nav-toggle">
                                <i class="icon-home"></i>
                                <span class="title">首页</span>
                                <span class="selected"></span>
                            </a>
                        </li>
                        <li class="nav-item start">
                            <a href="#" class="nav-link nav-toggle">
                                <i class="fa fa-camera-retro"></i>
                                <span class="title">产品概况</span>
                                <span class=""></span>
                            </a>
                        </li>
                        <li class="nav-item start">
                            <a href="#" class="nav-link nav-toggle">
                                <i class="fa fa-cogs"></i>
                                <span class="title">设备管理</span>
                                <span class=""></span>
                            </a>
                        </li>
                        <li class="nav-item start">
                            <a href="appManage.html" class="nav-link nav-toggle">
                                <i class="fa fa-suitcase"></i>
                                <span class="title">应用管理</span>
                                <span class=""></span>
                            </a>
                        </li>
                    </ul>
                    -->
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
                                <a href="">首页-编辑产品</a>
                            </li>
                        </ul>
                        
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title">编辑产品
                        <small></small>
                    </h3>
                    <!-- END PAGE TITLE-->
                    
                    <!-- row begin -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="portlet light portlet-fit bordered">
                                <div class="portlet-title">
                                    编辑产品
                                    <a href="javascript:history.go(-1);" class="btn green pull-right">返回</a>
                                </div>
                                <div class="portlet-body form">
                                    <form class="form-horizontal" role="form" id= "maxChat" >
                                        <div class="form-body">
                                            <div class="m-heading-1 border-green m-bordered"><h3>产品信息</h3></div>
                                            <input type="hidden" name="pro_id" value="${product.pro_id}"/>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">产品名称</label>
                                                <div class="col-md-8">
                                                    <input type="text" name="pro_name" value="${product.pro_name}" class="form-control" placeholder="" id="sitename">
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">开发板类别</label>
                                                <div class="col-md-2">
                                                    <select name="pro_devb" id="" class="form-control">
                                                    	<c:forEach var="mi" items="${Classfiy}">
                                                    		<c:if test="${product.pro_devb == mi.clas_id}">
                                                    			<option value="${mi.clas_id}" selected="selected">${mi.clas_name}</option>
                                                    		</c:if>
                                                    		<c:if test="${product.pro_devb != mi.clas_id}">
                                                    			<option value="${mi.clas_id}">${mi.clas_name}</option>
                                                    		</c:if>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">当前产品进度</label>
                                                <div class="col-md-8">
                                                    <div class="mt-radio-inline">
                                                        <label class="mt-radio">
                                                            <input type="radio" <c:if test="${product.pro_model == 1}">checked='checked'</c:if> name="pro_model" id="" value="1" > 有创意想法
                                                            <span></span>
                                                        </label>
                                                        <label class="mt-radio">
                                                            <input type="radio" <c:if test="${product.pro_model == 2}">checked='checked'</c:if> name="pro_model" id="" value="2" > 有产品原型
                                                            <span></span>
                                                        </label>
                                                        <label class="mt-radio">
                                                            <input type="radio" <c:if test="${product.pro_model == 3}">checked='checked'</c:if> name="pro_model" id="" value="3"> 有工程样机
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
                                                    <div class="mt-radio-inline">
                                                    <c:forEach var="am" items="${Agreement}">
                                                    	<c:if test="${product.pro_contype == am.at_id}">
                                                    		<label class="mt-radio">
                                                            	<input type="radio" checked='checked' name="pro_contype" id="" value="${am.at_id}">${am.at_name}
                                                            <span></span>
                                                        </label>
                                                    	</c:if>
                                                    	<c:if test="${product.pro_contype != am.at_id}">
                                                    		<label class="mt-radio">
                                                            	<input type="radio" name="pro_contype" id="" value="${am.at_id}">${am.at_name}
                                                            <span></span>
                                                        </label>
                                                    	</c:if>
                                                        
                                                    </c:forEach>
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9">
                                                    <button id="Modify" class="btn green" onclick="InsertPoduct()">提交</button>
                                                    <button type="reset" class="btn default">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- row end -->
                </div>
               

                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
            
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
        <script src="assets/global/plugins/respond.min.js"></script>
        <script src="assets/global/plugins/excanvas.min.js"></script> 
        <![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        <!-- BEGIN echarts插件 -->
        <!-- <script src="assets/mydoc/js/echarts.common.min.js" type="text/javascript"></script> -->
        <!-- END echarts插件 -->
        <!-- BEGIN 自定义JS -->
        <!-- <script src="assets/mydoc/js/editpro.js" type="text/javascript" defer></script> -->
        <!-- end 自定义JS -->
        <script type="text/javascript">
        	function InsertPoduct(){
        		
        		var math = true;
    			
    			if($('[name="pro_name"]').val() == ''){
    				alert("产品名字不能为空");
    				math = false;
    			}else if($('[name="pro_devb"]').val() == ''){
    				alert("开发板必须要选择一个");
    				math = false;
    			}else if($('[name="pro_model"]:checked').val() == undefined){
    				alert('当前进度必须要选择一个');
    				math = false;
    			}else if($('[name="pro_contype"]:checked').val() == undefined){
    				alert('协议必须要选择一个');
    				math = false;
    			}
    			
    			if(math){
    				
    				var form = new FormData(document.getElementById("maxChat"));
        			$.ajax({
        				url:"UpdateProduct",
        				type:"post",
        				data:form,
        				processData:false,
        	            contentType:false,
        	            success:function(date){
        	            	if(date){
        	            		$('#maxChat')[0].reset();
        	            		window.location.href = "${pageContext.request.contextPath}/views/indexs.jsp";
        	            	}else{
        	            		$('#maxChat')[0].reset();
    	                     	alert('输入有误，请重新输入');
        	            	}
        	            }
        			})
    			}	
        	}
        </script>
    </body>

</html>