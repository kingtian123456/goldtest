<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta>
  <title>智慧聊天室</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport"
        content="width=device-width, initial-scale=1">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="alternate icon" type="image/png" href="assets1/i/favicon.png">
  <link rel="stylesheet" href="assets1/css/amazeui.min.css"/>
  
<script src="assets1/js/jquery.min.js"></script>
<script src="assets1/js/amazeui.min.js"></script>
<!-- UM相关资源 -->
<link href="assets1/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="assets1/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="assets1/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="assets1/umeditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<header class="am-topbar am-topbar-fixed-top">
	  <div class="am-container">
	    <h1 class="am-topbar-brand">
	      <a href="#">设备调试</a>
	    </h1>
	    <div class="am-collapse am-topbar-collapse" id="collapse-head">
	      <ul class="am-nav am-nav-pills am-topbar-nav">
	        <li class="am-active"><a href="#">首页</a></li>
	      </ul>
	      <div class="am-topbar-right">
	        <a href="javascript:history.go(-1)"><button class="am-btn am-btn-secondary am-topbar-btn am-btn-sm">返回</button></a>
	      </div>
	    </div>
	  </div>
</header>
	
	<div id="main">
		<!-- 聊天内容展示区域 -->
	<div id="ChatBox" class="am-g am-g-fixed" >
	  <div class="am-u-lg-12" style="height:600px;border:1px solid #999;overflow-y:scroll;">
		<ul id="chatContent" class="am-comments-list am-comments-list-flip">
			<li id="msgtmp" class="am-comment" style="display:none;">
			    <a href="">
			        <img class="am-comment-avatar" src="assets1/images/other.jpg" alt=""/>
			    </a>
			    <div class="am-comment-main" >
			        <header class="am-comment-hd">
			            <div class="am-comment-meta">
			              <a ff="nickname" href="#link-to-user" class="am-comment-author">某人</a>
			              <time ff="msgdate" datetime="" title="">2014-7-12 15:30</time>
			            </div>
			        </header>
			     <div ff="content" class="am-comment-bd">此处是消息内容</div>
			    </div>
			</li>
		</ul>
	  </div>
	</div>
	<!-- 聊天内容发送区域 -->
	<div id="EditBox" class="am-g am-g-fixed">
	<!--style给定宽度可以影响编辑器的最终宽度-->
	<script type="text/plain" id="myEditor" style="width:100%;height:140px;"></script>
	<button id="send" type="button" class="am-btn am-btn-primary am-btn-block">发送</button>
	</div>
  	<div><input type="hidden" name="toUser" id="toUser" value="${devost.dev_vnum}"/><br/><input type="hidden" name="sendUser" id="sendUser" value="${devost.dev_code}"/></div>
</div>

<script type="text/javascript">

$(function(){
	//实例化编辑器
    var um = UM.getEditor('myEditor',{
    	initialContent:"请输入聊天信息...",
    	autoHeightEnabled:false,
    	toolbar:[
            'source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |',
            'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize' ,
            '| justifyleft justifycenter justifyright justifyjustify |',
            'link unlink | emotion image video  | map'
        ]
    });
    
    
    var nickname = document.getElementById("sendUser").value;
    
   	var socket = new WebSocket("ws://42.51.38.179:80/chatDemo/" + nickname);
    //var socket = new WebSocket("ws://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/websocket");
    //接收服务器的消息
    socket.onmessage=function(ev){
    	var obj = eval("("+ev.data+")");
    	addMessage(obj);
    }
   $("#send").click(function(){
    	if (!um.hasContents()) {  // 判断消息输入框是否为空
            // 消息输入框获取焦点
            um.focus();
            // 添加抖动效果
            $('.edui-container').addClass('am-animation-shake');
            setTimeout("$('.edui-container').removeClass('am-animation-shake')", 1000);
        } else {
        	//获取对方的标识
        	var toUser = $('#toUser').val();
        	//获取输入框的内容
        	var message = um.getContent();

        	//var jsonMsg = {"sendUser":sendUser,"toUser":toUser,"message":message};
        	//构建一个标准格式的JSON对象
        	var obj = JSON.stringify({
	    		nickname:nickname,
	    		toUser:toUser,
	    		content:message
	    	});
            // 发送消息
            socket.send(obj);
            //ajax 下发命令
            $.ajax({  
                type : "post",  
                 url : "Lowerrxiafa",  
                 data : {"value":message,"code":nickname},  
                 success : function(date){ 
                	 var reuslt = JSON.parse(date);
                	 if(reuslt){
                		 alert("发送成功");
                	 }
                 }  
            });
            // 清空消息输入框
            um.setContent('');
            // 消息输入框获取焦点
            um.focus();
        }
    	
    });
});

//人名nickname，时间date，是否自己isSelf，内容content
function addMessage(msg){

	var box = $("#msgtmp").clone(); 	//复制一份模板，取名为box
	box.show();							//设置box状态为显示
	box.appendTo("#chatContent");		//把box追加到聊天面板中
	box.find('[ff="nickname"]').html(msg.nickname); //在box中设置昵称
	box.find('[ff="msgdate"]').html(msg.date); 		//在box中设置时间
	box.find('[ff="content"]').html(msg.content); 	//在box中设置内容
	box.addClass(msg.isSelf? 'am-comment-flip':'');	//右侧显示
	box.addClass(msg.isSelf? 'am-comment-warning':'am-comment-success');//颜色
	box.css((msg.isSelf? 'margin-left':'margin-right'),"20%");//外边距
	
	$("#ChatBox div:eq(0)").scrollTop(999999); 	//滚动条移动至最底部
	
}
</script>

</body>
</html>
