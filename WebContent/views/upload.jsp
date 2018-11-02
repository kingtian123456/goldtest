<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传界面</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport"
        content="width=device-width, initial-scale=1">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="alternate icon" type="image/png" href="../assets1/i/favicon.png">
  <link rel="stylesheet" href="../assets1/css/amazeui.min.css"/>
  
<script src="../assets1/js/jquery.min.js"></script>
<script src="../assets1/js/amazeui.min.js"></script>
<!-- UM相关资源 -->
<link href="../assets1/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="../assets1/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="../assets1/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="../assets1/umeditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
	<div id="main">
		<!-- 聊天内容展示区域 -->
	<div id="ChatBox" class="am-g am-g-fixed" >
	  <div class="am-u-lg-12" style="height:600px;border:1px solid #999;overflow-y:scroll;">
		<ul id="chatContent" class="am-comments-list am-comments-list-flip">
			<li id="msgtmp" class="am-comment" style="display:none;">
			    <a href="">
			        <img class="am-comment-avatar" src="../assets1/images/other.jpg" alt=""/>
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
	});
</script>
</body>
</html>