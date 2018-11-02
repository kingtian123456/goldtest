//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('editor');
var str = window.location.search;
var newsId =  str.substring(str.indexOf("newsId")+"newsId".length+1);
var math;    	
//取模版数据
$(function(){
	$.ajax({
		url:"../selectBySortSubclass",
		type:"post",
		data:{"parentId":"1"},
		success:function(date){
			var sort = JSON.parse(date);
	    	for (var i = 0; i < sort.length; i++) {
	    		var resultset = "<option value='"+sort[i].sortId+"'>"+sort[i].sortName+"</option>";
	    		$('#selecttext').append(resultset);
			}
    	}
	});
});
readg(newsId);
function readg(newsid){

	$.ajax({
		url:"../getSingleNews",
        type:"post",
        data:{"newsId":newsid},
        success: function(date){
        	var news = JSON.parse(date);
        	$("[name='title']").val(news.newsTitle);
        	$("[name='occupation']").val(news.newsKeyword);
        	$("#selecttext").val(news.sortId);
        	$("[name='datepicker']").val(timestampToTime(news.newsCreatetime));
        	$("[name='membership']").prop('checked','checked');
        	$("[name='markdown']").val(news.newsIntro);
        	ue.setContent(news.newsContent);
        	math = news.newsId;
        }
	});
	
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
			if(str.user_authority == 1){
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
};	

//时间戳转日期
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = (date.getDate() < 10 ? '0'+(date.getDate()) : date.getDate()) + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    
    return Y+M+D;
}

//表单修改功能
function Toview(){
	var sortid = $('#selecttext').val();
    var title = $("[name='title']").val();
    var intro = $("[name='markdown']").val();
    var content = ue.getContent();
    var keyword = $("[name='occupation']").val();
    var passed = $("[name='membership']").val();
    var time = $("[name='datepicker']").val();
    
    		
    $.ajax({
    	url:"../putNewsById",
    	data:{"newsId":math,"sortid":sortid,"title":title,"intro":intro,"content":content,"keyword":keyword,"passed":passed,"time":time},
    	type:"post",
    	success:function(data){
    		if(data){
    			window.location.href = "devblog.html";
    		}else{
    			$('#form_sample_3')[0].reset();
    		}
    	}
    });
};