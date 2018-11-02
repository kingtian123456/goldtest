var str = window.location.search;
var newsId =  str.substring(str.indexOf("newsId")+"newsId".length+1);

$(function(){
	readg(newsId);
	getKeyWord();
	Newspage(newsId);
	RankingList();
});

//获取全部的关键字  集合
function getKeyWord(){
	$.ajax({
		url:"../getNewsKeyWord",
		type:"get",
		data:null,
		success: function(date){
			var keydate = JSON.parse(date);
			$("#devblogKey").html('');
			for (var i = 0; i < keydate.length; i++) {
				var reusltkey = "<li class='uppercase'><a href='javascript:;'>"+keydate[i]+"</a></li>";
				$("#devblogKey").append(reusltkey);
			}
			
		}
	});
}

//留言提交功能
function submitted(){
	var mscontent = $("[name='message']").val();
	var msLayerid = $("[name='layerid']").val();
	$.ajax({
		url:"../insertMessagePost",
		type:"post",
		data:{"mscontent":mscontent,"newsid":newsId,"layerid":msLayerid},
		success: function(date){
			if(date){
				$("[name='layerid']").val("");
				$("#mislt").attr('placeholder','请输入留言的内容');
				$("#messageform")[0].reset();
				Hidden();
				Newspage(newsId);
			}else{
				$("#messageform")[0].reset();
				alert("留言失败，请重新进行留言");
			}
		}
	})
}

//留言展示功能
function Newspage(newsid){
	
	$.ajax({
        url: "../getMessagePage",
        type: "post",
        data: {"newsid":newsid},
        success: function (data) {
            var str = JSON.parse(data);
            $("#mytab").html("");
            for (var i = 0; i < str.pageData.length; i++) {
                var resultset = "<div class='media'>"+
                        "<div class='media-left'>"+
                        	"<a href='#'>"+
                            	"<img class='media-object' alt='' src='../assets/pages/img/avatars/team3.jpg'> </a>"+
                        "</div>"+
                        "<div class='media-body'>"+
                        	"<h4 class='media-heading'>"+
                            	"<a href='#'>"+str.pageData[i].user.user_account+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                	"<span class='c-date'>"+getLocalTime(str.pageData[i].msCretime)+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                	"<span class='c-date' style='color:red' onclick=\"replyMat('"+str.pageData[i].msId+"','"+str.pageData[i].user.user_account+"')\">回复</span>"+
                            "</h4>"+str.pageData[i].msContent+""+
                        "</div>"+
                  "</div>";
                $("#mytab").append(resultset);
                var Aarry = str.pageData[i].list;
                if(Aarry.length > 0){
                	for (var j = 0; j < Aarry.length; j++) {
    					var level = "<div class='media' id='MessageList'>"+
    						"<div class='media-left'>"+
                 					"<a href='#'>"+
                 						"<img class='media-object' alt='' src='../assets/pages/img/avatars/team4.jpg'></a>"+
                 					"</div>"+
                 					"<div class='media-body'>"+
                 						"<h4 class='media-heading'>"+
                 							"<a href='#'>"+Aarry[j].user.user_account+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                 								"<span class='c-date'>"+getLocalTime(Aarry[j].msCretime)+"</span>"+
                 						"</h4><span style='color:red'>@"+str.pageData[i].user.user_account+":</span>"+Aarry[j].msContent+
                 					"</div>"+
    					"</div>";
    					$("#mytab").children(".media").children(".media-body").eq(i).append(level);
    				}
                }
            }

            $("#page").paging({
                pageNo: str.curPage,
                totalPage: str.totalPages,
                totalSize: str.totalRecords,
                callback: function (num) {
                    $.ajax({
                        url: "../getMessagePage",
                        type: "post",
                        data: {"newsid":newsid,"curPage":num},
                        success: function (data) {
                            var str = JSON.parse(data);
                            $("#mytab").html("");
                            for (var i = 0; i < str.pageData.length; i++) {
                                var resultset = "<div class='media'>"+
                                        "<div class='media-left'>"+
                                        	"<a href='#'>"+
                                            	"<img class='media-object' alt='' src='../assets/pages/img/avatars/team3.jpg'> </a>"+
                                        "</div>"+
                                        "<div class='media-body' onclick=\"replyMat('"+str.pageData[i].msId+"','"+str.pageData[i].user.user_account+"')\">"+
                                        	"<h4 class='media-heading'>"+
                                            	"<a href='#'>"+str.pageData[i].user.user_account+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                            	"<span class='c-date'>"+getLocalTime(str.pageData[i].msCretime)+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                            	"<span class='c-date' style='color:red' onclick=\"replyMat('"+str.pageData[i].msId+"','"+str.pageData[i].user.user_account+"')\">回复</span>"+
                                            "</h4>"+str.pageData[i].msContent+""+
                                        "</div>"+
                                  "</div>";
                                $("#mytab").append(resultset);
                                var Aarry = str.pageData[i].list;
                                if(Aarry.length > 0){
                                	for (var j = 0; j < Aarry.length; j++) {
                    					var level = "<div class='media' id='MessageList'>"+
                    								"<div class='media-left'>"+
                                 					"<a href='#'>"+
                                 						"<img class='media-object' alt='' src='../assets/pages/img/avatars/team4.jpg'></a>"+
                                 					"</div>"+
                                 					"<div class='media-body'>"+
                                 						"<h4 class='media-heading'>"+
                                 							"<a href='#'>"+Aarry[j].user.user_account+"</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                 								"<span class='c-date'>"+getLocalTime(Aarry[j].msCretime)+"</span>"+
                                 						"</h4><span style='color:red'>@"+str.pageData[i].user.user_account+":</span>"+Aarry[j].msContent+
                                 					"</div>"+
                                 					"</div>";
                    					$("#mytab").children(".media").children(".media-body").eq(i).append(level);
                    				}
                                }
                            }
                        }
                    })
                }
            })

        },
        error: function (data) {
            alert("异常，请联系管理员!");
        }
    });
}

//搜索框,通过title加载文章和评论
function Thetitle(){
	
	var title = $("[name='titleOne']").val();
	$.ajax({
		url:"../getNewsOneClick",
		type:"post",
		data:{"title":title},
		success:function(date){
			var news = JSON.parse(date);
			if(news != null){
				$("#viewtitle").html(news.newsTitle);
	        	$("#viewdate").html(getLocalTime(news.newsCreatetime));
	        	$("#viewcontent").html(news.newsContent);
	        	$("#NewsOnekey").html('');
	        	var keys = news.list;
	        	for (var i = 0; i < keys.length; i++) {
					var reusltkey = "<li class='uppercase'><a href='javascript:;'>"+keys[i]+"</a></li>";
					$("#NewsOnekey").append(reusltkey);
				}
	        	
	        	Newspage(news.newsId);
	        	
	        	newsId = news.newsId;
	        	devblogAuth();
			}else{
				alert("您搜索的文章不存在！！！");
			}
			
		}
	})
}

//初始化加载排行榜
function RankingList(){
	$.ajax({
		url:"../getNewsArticle",
		type:"post",
		data:null,
		success:function(date){
			var article = JSON.parse(date);
			for (var i = 0; i < article.length; i++) {
				var reuslt = "<li onclick=\"Reload('"+article[i].newsId+"')\" ><a href='javascript:;'>"+article[i].newsTitle+"</a></li>";
				$("#Ranking").append(reuslt);
			}
		}
	})
}

//点击排行榜加载功能
function Reload(newsid){
	readg(newsid);
	Newspage(newsid);
	newsId = newsid;
};

//通过newsID加载文章内容
function readg(newsid){

	$.ajax({
		url:"../getSingleNews",
        type:"post",
        data:{"newsId":newsid},
        success: function(date){
        	var news = JSON.parse(date);
        	$("#viewtitle").html(news.newsTitle);
        	$("#viewdate").html(getLocalTime(news.newsCreatetime));
        	$("#viewcontent").html(news.newsContent);
        	$("#NewsOnekey").html('');
        	var keys = news.list;
        	for (var i = 0; i < keys.length; i++) {
				var reusltkey = "<li class='uppercase'><a href='javascript:;'>"+keys[i]+"</a></li>";
				$("#NewsOnekey").append(reusltkey);
			}
        }
	});
	
	devblogAuth();
}

//前端权限控制
function devblogAuth(){
	$.ajax({
		url:"../getUserDate",
		type:"get",
		data:null,
		success:function(date){
			var str = JSON.parse(date);
			if(str.user_auth == 1){
				var divset =  $("#sidebarUser");
    			for (var i = 0; i<divset.length;i++) {
    				   divset[i].style.display = "block";
    			};
			}
			if(str.user_authority > 0){
				$("#operation").html('');
				var dele = "<a href='devblogeditor.html?newsId="+newsId+"' class='btn green btn-outline btn-circle btn-md active pull-right'>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick='deleteNews("+newsId+")' class='btn green btn-outline btn-circle btn-md active pull-right' href='javascript:;'>删除</a>";
				$("#operation").append(dele);
				var view1 =  $("#viewUser1");
     			for (var j = 0; j<view1.length;j++) {
     				view1[j].style.display = "block";
     			};
     			var view2 =  $("#viewUser2");
     			for (var k = 0; k<view2.length;k++) {
     				view2[k].style.display = "block";
     			};
			}
			
		}
	});
}

//删除文章功能
function deleteNews(newsid){
	if(window.confirm('是否确定删除文章？删除文章后，文章内容及文章相关的所有留言数据将从平台消失，并且不可恢复。请谨慎操作.')){
		if(newsid != null && newsid != ''){
			$.ajax({
				url:"../deleteNewsMessageID",
				type:"post",
				data:{"newsId":newsid},
				success:function(date){
					var str = JSON.parse(date);
					if(str){
						window.location.href = "devblog.html";
					}else{
						alert("删除错误！！！");
					}
				}
			})
		}
	}
	
}

//显示隐藏的留言框
function Show(){
	var divset =  $(".Theparcel");
	for (var i = 0; i<divset.length;i++) {
		   divset[i].style.display = "block";
	};
	$("[name='layerid']").val("");
	$("#mislt").attr('placeholder','请输入留言的内容');
}

//隐藏的留言框
function Hidden(){
	var divset =  $(".Theparcel");
	for (var i = 0; i<divset.length;i++) {
		   divset[i].style.display = "none";
	};
}

//@功能实现
function replyMat(msLayerid,account){
	Show();
	$("#mislt").attr('placeholder','@'+account);
	$("[name='layerid']").val(msLayerid);
}

//时间戳转String
function getLocalTime(nS) {     
	return new Date(parseInt(nS)).toLocaleString().substr(0,20)
}