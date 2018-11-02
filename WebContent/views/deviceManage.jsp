<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <title>设备管理-ETENIOT LABS-智城慧商</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet"
          type="text/css"/>
    <link href="assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN THEME GLOBAL STYLES -->
    <link href="assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css"/>
    <link href="assets/global/css/plugins.min.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME GLOBAL STYLES -->
    <!-- BEGIN THEME LAYOUT STYLES -->
    <link href="assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/mydoc/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css">
    <link href="assets/mydoc/css/style.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME LAYOUT STYLES -->
    <style>
        .table-manage > div > div > a {
            display: inline-block;
            width: 32px;
            text-align: center;
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
                <img src="assets/layouts/layout/img/logo.png" alt="logo" class="logo-default"/> </a>
            <div class="menu-toggler sidebar-toggler">
                <span></span>
            </div>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse"
           data-target=".navbar-collapse">
            <span></span>
        </a>
        <!-- END RESPONSIVE MENU TOGGLER -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <div class="top-menu">

            <ul class="nav navbar-nav pull-right">

                <!-- BEGIN USER LOGIN DROPDOWN -->
                <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                <li class="dropdown dropdown-user">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                       data-close-others="true">
                        <img alt="" class="img-circle" src="assets/layouts/layout/img/avatar3_small.jpg"/>
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
<div class="clearfix"></div>
<!-- END HEADER & CONTENT DIVIDER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <!-- BEGIN SIDEBAR -->
    <div class="page-sidebar-wrapper">
        <!-- BEGIN SIDEBAR -->
        <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
        <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
        <div class="page-sidebar navbar-collapse collapse">
            <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true"
                data-slide-speed="200" style="padding-top: 20px">
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
                <small></small>
            </h3>
            <!-- END PAGE TITLE-->

            <!-- row end -->
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet light portlet-fit bordered">
                        <div class="portlet-title">
                            <div class="device-font">
                                <label class="device-font-title">设备总数量:</label>
                                <label class="device-font-content"><span
                                        class="device-total-num">${product.dvg_devnum}</span>个</label>
                                <label class="device-font-title margin-left-title">设备接入协议:</label>
                                <label class="device-font-content">${product.ament.at_name}</label>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <form class="form-inline" role="form" method="get"
                                  action="${pageContext.request.contextPath}/selectProductDevicesinfo">
                                <input type="text" class="form-control" name="logtxt" placeholder="输入设备ID或者设备名称"
                                       id="username">
                                <button type="submit" class="btn blue">查询</button>
                                <a href="javascript:;" class="btn green btn-outline btn-circle btn-md active pull-right"
                                   onclick="addShow()">添加设备</a>
                            </form>
                            <div class="table-scrollable ">
                                <table class="table table-striped table-hover" id="devtab">
                                    <c:forEach var="dl" items="${devicelist}">
                                        <tbody>
                                        <tr>
                                            <td class="devtd">
                                                <p class="devname">${dl.dev_name}
                                                    <c:if test="${dl.dev_ispassca == 1}">(在线)</c:if>
                                                    <c:if test="${dl.dev_ispassca == 0}">(离线)</c:if>
                                                </p>
                                                <p class="devmsg">设备编码:${dl.dev_code}</p>
                                                <p class="devmsg">创建时间:<fmt:formatDate value="${dl.dev_intime}"
                                                                                       pattern="yyyy-MM-dd HH:mm:ss"/></p>
                                            </td>
                                            <td class="table-manage" style="vertical-align: middle;">
                                                <div>
                                                    <div>
                                                        <a>
                                                            <i class="fa fa-edit"
                                                               onclick="test('${dl.dev_name}','${dl.dev_vnum}','${dl.dev_type}','${dl.dev_code}','${dl.dev_forward}','${dl.dev_forport}')"></i>
                                                        </a>
                                                        <a>
                                                            <i class="fa fa-trash"
                                                              onclick="math('${dl.dev_id}','${dl.dev_code}','${dl.dev_sumnum}')"></i>
                                                        </a>
                                                        <a>
                                                            <i class="fa fa-info"
                                                               onclick="kiths('${dl.dev_name}','${dl.dev_vnum}','${dl.dev_type}','${dl.dev_forward}','${dl.dev_forport}')"></i>
                                                        </a>
                                                        <a style="opacity: 0;">&nbsp;&nbsp;</a>
                                                    </div>
                                                    <div>
                                                        <a href="${pageContext.request.contextPath}/views/deviceData.jsp?dev_code=${dl.dev_code}"><i
                                                                class="fa fa-columns"></i></a>
                                                        <c:if test="${dl.dev_ispassca == 1}">
                                                            <a>
                                                                <i class="fa fa-angle-right"
                                                                   onclick="Lower('${dl.dev_code}')"></i>
                                                            </a>
                                                        </c:if>
                                                        <a href="${pageContext.request.contextPath}/Communication?dev_code=${dl.dev_code}"><i
                                                                class="fa fa-mortar-board"></i></a>
                                                        <a style="opacity: 0;">&nbsp;&nbsp;</a>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </c:forEach>
                                </table>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- END CONTENT BODY -->
        <!-- 分页 -->
        <div class="text-center">
            <ul class="pagination">
                <c:choose>
                    <c:when test="${bmpage == 1}">
                        <li><a href="#">«</a></li>
                    </c:when>
                    <c:otherwise>
                        <%-- 点击到首页--%>
                        <li><a href="${pageContext.request.contextPath}/selectProductDevicesinfo">«</a></li>
                    </c:otherwise>
                </c:choose>
                <%--中间页--%>
                <%--显示6页中间页[begin=起始页,end=最大页]--%>
                <%--总页数没有6页--%>
                <c:choose>
                    <c:when test="${zbmpage <= 6}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="${zbmpage}"/>
                    </c:when>
                    <%--页数超过了6页--%>
                    <c:otherwise>
                        <c:set var="begin" value="${bmpage - 1}"/>
                        <c:set var="end" value="${bmpage + 3}"/>
                        <%--如果begin减1后为0,设置起始页为1,最大页为6--%>
                        <c:if test="${begin -1 <= 0}">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="6"/>
                        </c:if>
                        <%--如果end超过最大页,设置起始页=最大页-5--%>
                        <c:if test="${end > zbmpage}">
                            <c:set var="begin" value="${zbmpage - 5}"/>
                            <c:set var="end" value="${zbmpage}"/>
                        </c:if>
                    </c:otherwise>
                </c:choose>

                <c:forEach var="j" begin="${begin}" end="${end}">
                    <%--当前页,选中--%>
                    <c:choose>
                        <c:when test="${j == bmpage}">
                            <li class="active"><a href="#">${j}</a></li>
                        </c:when>
                        <%--不是当前页--%>
                        <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/selectProductDevicesinfo?page=${j}">${j}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${bmpage == zbmpage}">
                        <li><a href="#">»</a></li>
                    </c:when>
                    <c:otherwise>
                        <%-- 点击到尾页--%>
                        <li><a href="${pageContext.request.contextPath}/selectProductDevicesinfo?page=${zbmpage}">»</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>


        </div>
    </div>


    <!-- END CONTENT -->
    <!-- editModal模态框 begin -->
    <div class="modal fade bs-example-modal-md" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
         id="editModal">
        <div class="modal-dialog modal-md" role="document" style="margin: auto;top: 50%;transform: translate(0,-50%);">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title">接入设备<span class="small">${product.ament.at_name}协议</span></h4>
                </div>
                <div class="modal-body">

                    <form class="form-horizontal" id="productmodelssi" role="form">
                        <input type="hidden" name="dev_code" value="">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">设备名称</label>
                                <div class="col-md-8">
                                    <input type="text" name="dev_name1" class="form-control" placeholder="请输入设备名称">
                                </div>
                                <div class="clearfix"></div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">设备IMSI</label>
                                <div class="col-md-8">
                                    <input type="text" name="dev_vnum1" class="form-control"
                                           placeholder="字符或者数字组成的字符串，最多不超过16个字符。">
                                </div>
                                <div class="clearfix"></div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">设备的类型</label>
                                <div class="col-md-8">
                                    <input type="text" name="dev_type1" class="form-control" placeholder="请填写这是什么类型的设备">
                                </div>
                                <div class="clearfix"></div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">转发的IP</label>
                                <div class="col-md-8">
                                    <input type="text" name="dev_forward1" class="form-control" placeholder="请填写IP地址">
                                </div>
                                <div class="clearfix"></div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">转发的端口</label>
                                <div class="col-md-8">
                                    <input type="text" name="dev_forport1" class="form-control" placeholder="请填写端口号">
                                </div>
                                <div class="clearfix"></div>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="UpdateDevice()">提交</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
    <!-- editModal模态框 end -->
    <!-- infoModal begin -->
    <div class="modal fade bs-example-modal-md" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
         id="infoModal">
        <div class="modal-dialog modal-md" role="document" style="margin: auto;top: 50%;transform: translate(0,-50%);">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title">查看设备<span class="small">${product.ament.at_name}协议</span></h4>
                </div>
                <div class="modal-body">

                    <form class="form-horizontal" role="form">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">设备名称</label>
                                <div class="col-md-8">
                                    <input type="text" name="dev_name2" class="form-control">
                                </div>
                                <div class="clearfix"></div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">设备IMSI</label>
                                <div class="col-md-8">
                                    <input type="text" name="dev_vnum2" class="form-control">
                                </div>
                                <div class="clearfix"></div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">设备的类型</label>
                                <div class="col-md-8">
                                    <input type="text" name="dev_type2" class="form-control">
                                </div>
                                <div class="clearfix"></div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">转发的IP</label>
                                <div class="col-md-8">
                                    <input type="text" name="dev_forward2" class="form-control" placeholder="请填写IP地址">
                                </div>
                                <div class="clearfix"></div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">转发的端口</label>
                                <div class="col-md-8">
                                    <input type="text" name="dev_forport2" class="form-control" placeholder="请填写端口号">
                                </div>
                                <div class="clearfix"></div>
                            </div>

                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
    <!-- infoModal模态框 end -->
    <!-- trashModal begin -->
    <div class="modal fade bs-example-modal-md" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
         id="trashModal">
        <div class="modal-dialog modal-md" role="document" style="margin: auto;top: 50%;transform: translate(0,-50%);">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title">下发提示</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" value="" id="xiafalinux">
                    <p>下发内容:<input type="text" style="width:500px" name="value" id="lowerss"/></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" onclick="deletedevice()">确定</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <!-- trashModal模态框 end -->
    
    <!-- 添加设备 模态框 begin -->
    <!-- addDevice 模态框 begin -->
    <div class="modal fade bs-example-modal-md" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
         id="addDevModal">
        <div class="modal-dialog modal-md" role="document" style="margin: auto;top: 50%;transform: translate(0,-50%);">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
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
                                    <input type="text" name="dev_vnum" class="form-control"
                                           placeholder="字符或者数字组成的字符串，最多不超过16个字符。">
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
                            <button type="button" class="btn btn-primary" onclick="AddDevice()">提交
                            </button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
    <!-- 添加设备  模态框 end -->
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
<!--[if lt IE 9]>
<script src="assets/global/plugins/respond.min.js"></script>
<script src="assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
<!-- BEGIN CORE PLUGINS -->
<script src="assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="layer/layer.js" type="text/javascript"></script>
<script src="assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="assets/global/scripts/app.min.js" type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<script src="assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
<!-- END THEME LAYOUT SCRIPTS -->
<script src="assets/mydoc/js/jquery.datetimepicker.full.min.js" type="text/javascript"></script>
<!-- BEGIN echarts插件 -->
<!-- <script src="assets/mydoc/js/echarts.common.min.js" type="text/javascript"></script> -->
<!-- END echarts插件 -->
<!-- BEGIN 自定义JS -->
<script src="assets/mydoc/js/deviceManage.js" type="text/javascript" defer></script>
<!-- end 自定义JS -->
</body>

</html>