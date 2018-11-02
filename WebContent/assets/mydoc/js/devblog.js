$(function () {
         $.ajax({
             url: "../getNewsByInfo",
             type: "post",
             data: null,
             success: function (data) {
                 var str = JSON.parse(data);
                 for (var i = 0; i < str.pageData.length; i++) {
                     var resultset = "<div class='blog-post-lg bordered blog-container'>"+str.pageData[i].newsContent+
                     					"<div class='blog-post-content'> "+
                                        "<h2 class='blog-title blog-post-title'> "+
                                            "<a href='devblogview.html?newsId="+str.pageData[i].newsId+"'>"+str.pageData[i].newsTitle+"</a> "+
                                        "</h2> "+
                                        "<a href='devblogview.html?newsId="+str.pageData[i].newsId+"'><p class='blog-post-desc'>"+str.pageData[i].newsIntro+"</p></a> "+
                                        "<div class='blog-post-foot'> "+
                                            "<ul class='blog-post-tags' id = 'Newskey'> "+
                                            "</ul> "+
                                            "<div class='blog-post-meta'> "+
                                                "<i class='icon-calendar font-blue'></i> "+
                                                "<a href='javascript:;'>"+getLocalTime(str.pageData[i].newsCreatetime)+"</a> "+
                                            "</div> "+
                                            "<div class='blog-post-meta'> "+
                                                "<i class='icon-bubble font-blue'></i> "+
                                                "<a href='javascript:;'>"+str.pageData[i].newsHits+"</a> "+
                                            "</div> "+
                                        "</div> "+
                                    "</div> "+
                                "</div>";
                     $("#blogpaging").append(resultset);
                     
                     var Array = str.pageData[i].list;
                     if(Array.length > 0){
                    	 for (var j = 0; j < Array.length; j++) {
     						var replist = "<li class='uppercase'><a href='javascript:;'>"+Array[j]+"</a></li>";
     						$("#blogpaging").find("div").find(".blog-post-content").find(".blog-post-foot").find(".blog-post-tags").eq(i).append(replist);
                          }
                     }
                 }

                 $("#page").paging({
                     pageNo: str.curPage,
                     totalPage: str.totalPages,
                     totalSize: str.totalRecords,
                     callback: function (num) {
                         $.ajax({
                             url: "../getNewsByInfo",
                             type: "post",
                             data: {"curPage":num},
                             success: function (data) {
                                 var str = JSON.parse(data);
                                 $("#blogpaging").html('');
                                 for (var i = 0; i < str.pageData.length; i++) {
                                	 var resultset = "<div class='blog-post-lg bordered blog-container'>"+str.pageData[i].newsContent+"<div class='blog-post-content'> "+
                                     "<h2 class='blog-title blog-post-title'> "+
                                         "<a href='devblogview.html?newsId="+str.pageData[i].newsId+"'>"+str.pageData[i].newsTitle+"</a> "+
                                     "</h2> "+
                                     "<a href='devblogview.html?newsId="+str.pageData[i].newsId+"'><p class='blog-post-desc'>"+str.pageData[i].newsIntro+"</p></a> "+
                                     "<div class='blog-post-foot'> "+
                                         "<ul class='blog-post-tags' id = 'Newskey'> "+
                                         "</ul> "+
                                         "<div class='blog-post-meta'> "+
                                             "<i class='icon-calendar font-blue'></i> "+
                                             "<a href='javascript:;'>"+getLocalTime(str.pageData[i].newsCreatetime)+"</a> "+
                                         "</div> "+
                                         "<div class='blog-post-meta'> "+
                                             "<i class='icon-bubble font-blue'></i> "+
                                             "<a href='javascript:;'>"+str.pageData[i].newsHits+"</a> "+
                                         "</div> "+
                                     "</div> "+
                                     "</div> "+
                                     "</div>";
                                     $("#blogpaging").append(resultset);
                                     
                                     var Array = str.pageData[i].list;
                                     if(Array.length > 0){
                                    	 for (var j = 0; j < Array.length; j++) {
                     						var replist = "<li class='uppercase'><a href='javascript:;'>"+Array[j]+"</a></li>";
                     						$("#blogpaging").find("div").find(".blog-post-content").find(".blog-post-foot").find(".blog-post-tags").eq(i).append(replist);
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
         
         $.ajax({
        	 url:"../getUserDate",
        	 type:"get",
        	 data:null,
        	 success:function(date){
        		 $("#devblogUser").html('');
        		 var devUser = JSON.parse(date);
        		 var reuslt = "<a href='devblognew.html' class='btn green btn-outline btn-circle btn-md active pull-right'>新增主题</a>";
        		 if(devUser.user_authority > 0){
        			$("#devblogUser").append(reuslt);
        			var view1 =  $("#viewUser1");
         			for (var j = 0; j<view1.length;j++) {
         				view1[j].style.display = "block";
         			};
         			var view2 =  $("#viewUser2");
         			for (var k = 0; k<view2.length;k++) {
         				view2[k].style.display = "block";
         			};
        		 }
        		 if(devUser.user_auth > 0){
        			var divset =  $("#sidebarUser");
          			for (var i = 0; i<divset.length;i++) {
          				   divset[i].style.display = "block";
          			};
        		 }
        	 }
         });
         
});

function getLocalTime(nS) {     
    return new Date(parseInt(nS)).toLocaleString().substr(0,20)
};