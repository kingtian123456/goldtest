var AuthUser;

$(function(){
	AuthSiber();
	datadowninit();
});


//初始加载
function datadowninit(){
	$.ajax({
        url: "../getDownloadInfo",
        type: "GET",
        data: null,
        success: function (data) {
            var str = JSON.parse(data);
            for (var i = 0; i < str.pageData.length; i++) {
            	if(AuthUser > 0){
            		var resultset = "<div class='blog-post-lg bordered blog-container'>"+
                    "<div class='blog-post-content'>"+
                    "<h2 class='blog-title blog-post-title'>"+
                        "<a href='javascript:;'>"+str.pageData[i].doTitle+"</a>"+
                    "</h2>"+
                    "<p class='blog-post-desc'style='font-weight: bold;color: #000;'>"+str.pageData[i].doIntron+"</p>"+
                    "<div class='blog-post-foot'>"+
                    	"<ul class='blog-post-tags'>"+
                        	"<li class='uppercase'>"+
                        		"<a onclick='Edit(\""+str.pageData[i].doId+"\",\""+str.pageData[i].doName+"\",\""+str.pageData[i].doTitle+"\",\""+str.pageData[i].doCode+"\",\""+str.pageData[i].doIntron+"\",\""+str.pageData[i].doLoadurl+"\")'>编辑</a>"+
                        	"</li>"+
                        	"<li class='uppercase'>"+
                           		"<a onclick='deletedownload(\""+str.pageData[i].doId+"\")'>删除</a>"+
                        	"</li>"+
                        	"<li class='uppercase'>"+
                       			"<a href='javascript:;'>资料提取码:<span>"+str.pageData[i].doCode+"</span></a>"+
                       		"</li>"+
                    	"</ul>"+
                        "<div class='blog-post-meta'>"+
                            "<i class='icon-calendar font-blue'></i>"+
                            "<a href='"+str.pageData[i].doLoadurl+"' onclick='load(\""+str.pageData[i].doId+"\",\""+str.pageData[i].doLoadnum+"\")'>下载</a>"+
                        "</div>"+
                        "<div class='blog-post-meta'>"+
                            "<i class='icon-bubble font-blue'></i>"+
                            "<a href='javascript:;'>"+str.pageData[i].doLoadnum+"</a>"+
                        "</div>"+
                    "</div>"+
                "</div>"+
            "</div>";
            	}else{
            		var resultset = "<div class='blog-post-lg bordered blog-container'>"+
                    "<div class='blog-post-content'>"+
                    "<h2 class='blog-title blog-post-title'>"+
                        "<a href='javascript:;'>"+str.pageData[i].doTitle+"</a>"+
                    "</h2>"+
                    "<p class='blog-post-desc' style='font-weight: bold;color: #000;'>"+str.pageData[i].doIntron+"</p>"+
                    "<div class='blog-post-foot'>"+
                    	"<ul class='blog-post-tags'>"+
                        	"<li class='uppercase'>"+
                       			"<a href='javascript:;'>资料提取码:<span>"+str.pageData[i].doCode+"</span></a>"+
                       		"</li>"+
                    	"</ul>"+
                        "<div class='blog-post-meta'>"+
                            "<i class='icon-calendar font-blue'></i>"+
                            "<a href='"+str.pageData[i].doLoadurl+"' onclick='load(\""+str.pageData[i].doId+"\",\""+str.pageData[i].doLoadnum+"\")'>下载</a>"+
                        "</div>"+
                        "<div class='blog-post-meta'>"+
                            "<i class='icon-bubble font-blue'></i>"+
                            "<a href='javascript:;'>"+str.pageData[i].doLoadnum+"</a>"+
                        "</div>"+
                    "</div>"+
                "</div>"+
            "</div>";
            	}
                
                $("#mytab").append(resultset);
            }

            $("#page").paging({
                pageNo: str.curPage,
                totalPage: str.totalPages,
                totalSize: str.totalRecords,
                callback: function (num) {
                    $.ajax({
                        url: "../getDownloadInfo",
                        type: "GET",
                        data: {"curPage":num},
                        success: function (data) {
                            var str = JSON.parse(data);
                            $("#mytab").html("");
                            for (var i = 0; i < str.pageData.length; i++) {
                            	if(AuthUser > 0){
                            		var resultset = "<div class='blog-post-lg bordered blog-container'>"+
                                    "<div class='blog-post-content'>"+
                                    "<h2 class='blog-title blog-post-title'>"+
                                        "<a href='javascript:;'>"+str.pageData[i].doTitle+"</a>"+
                                    "</h2>"+
                                    "<p class='blog-post-desc'>"+str.pageData[i].doIntron+"</p>"+
                                    "<div class='blog-post-foot'>"+
                                    	"<ul class='blog-post-tags'>"+
                                        	"<li class='uppercase'>"+
                                        		"<a onclick='Edit(\""+str.pageData[i].doId+"\",\""+str.pageData[i].doName+"\",\""+str.pageData[i].doTitle+"\",\""+str.pageData[i].doCode+"\",\""+str.pageData[i].doIntron+"\",\""+str.pageData[i].doLoadurl+"\")'>编辑</a>"+
                                        	"</li>"+
                                        	"<li class='uppercase'>"+
                                           		"<a onclick='deletedownload(\""+str.pageData[i].doId+"\")'>删除</a>"+
                                        	"</li>"+
                                        	"<li class='uppercase'>"+
                                       			"<a href='javascript:;'>资料提取码:<span>"+str.pageData[i].doCode+"</span></a>"+
                                       		"</li>"+
                                    	"</ul>"+
                                        "<div class='blog-post-meta'>"+
                                            "<i class='icon-calendar font-blue'></i>"+
                                            "<a href='"+str.pageData[i].doLoadurl+"' onclick='load(\""+str.pageData[i].doId+"\",\""+str.pageData[i].doLoadnum+"\")'>下载</a>"+
                                        "</div>"+
                                        "<div class='blog-post-meta'>"+
                                            "<i class='icon-bubble font-blue'></i>"+
                                            "<a href='javascript:;'>"+str.pageData[i].doLoadnum+"</a>"+
                                        "</div>"+
                                    "</div>"+
                                "</div>"+
                            "</div>";
                            	}else{
                            		var resultset = "<div class='blog-post-lg bordered blog-container'>"+
                                    "<div class='blog-post-content'>"+
                                    "<h2 class='blog-title blog-post-title'>"+
                                        "<a href='javascript:;'>"+str.pageData[i].doTitle+"</a>"+
                                    "</h2>"+
                                    "<p class='blog-post-desc'>"+str.pageData[i].doIntron+"</p>"+
                                    "<div class='blog-post-foot'>"+
                                    	"<ul class='blog-post-tags'>"+
                                        	"<li class='uppercase'>"+
                                       			"<a href='javascript:;'>资料提取码:<span>"+str.pageData[i].doCode+"</span></a>"+
                                       		"</li>"+
                                    	"</ul>"+
                                        "<div class='blog-post-meta'>"+
                                            "<i class='icon-calendar font-blue'></i>"+
                                            "<a href='"+str.pageData[i].doLoadurl+"' onclick='load(\""+str.pageData[i].doId+"\",\""+str.pageData[i].doLoadnum+"\")'>下载</a>"+
                                        "</div>"+
                                        "<div class='blog-post-meta'>"+
                                            "<i class='icon-bubble font-blue'></i>"+
                                            "<a href='javascript:;'>"+str.pageData[i].doLoadnum+"</a>"+
                                        "</div>"+
                                    "</div>"+
                                "</div>"+
                            "</div>";
                            	}
                                $("#mytab").append(resultset);
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

//前端权限控制
function AuthSiber(){
	$.ajax({
		url:"../getUserDate",
	    type:"get",
	    data:null,
	    success:function(date){
	    	$("#devblogUser").html("");
	    	var devUser = JSON.parse(date);
	    	var reuslt = "<a onclick=\"addShow()\" class='btn green btn-outline btn-circle btn-md active pull-right'>新增资料</a>";
	    	AuthUser = devUser.user_authority;
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
}

//显示新增框
function addShow(){
	$("#editModal").modal("show");
};

//删除文件信息
function deletedownload(doId){
	if(window.confirm('是否确定删除分类？删除分类后，分类及分类相关的所有数据将从平台消失，并且不可恢复。请谨慎操作.')){
		$.ajax({
			url:"../deleteDownloadOne",
			type:"post",
			data:{"doId":doId},
			success:function (date) {
				var reuslt = JSON.parse(date);
				if(reuslt){
					window.location.reload();
				}else{
					alert("没有该文件信息，请刷新页面重新在进行删除！！！");
				}
			}
		})
	}
	
}

//显示修改框,填充编辑框
function Edit(doId,doName,doTitle,doCode,doIntron,doLoadurl){
	
	$("[name='doId']").val(doId);
	$("[name='doName']").val(doName);
	$("[name='doTitle']").val(doTitle);
	$("[name='doCode']").val(doCode);
	$("[name='doIntron']").val(doIntron);
	$("[name='doLoadurl']").val(doLoadurl);
	
	$("#editModals").modal("show");
	
}

//提交修改
function  Updatedownload(){
	var form = new FormData(document.getElementById("Updatedownload"));
	$.ajax({
		url: "../putDownloadOne",
        type: "post",
        data: form,
        cache: false,
        processData: false,
        contentType: false,
        success:function (date) {
        	var str = JSON.parse(date);
        	if(str){
        		$("#Updatedownload")[0].reset();
        		$("#editModals").modal("hide");
				window.location.reload();
        	}else{
        		alert("输入信息不符合规定，请重新输入！！！");
        	}
        }
	});
}

//新增提交
function InsertDownload(){
	var doTitle = $("[name='doTitle1']").val();
	var doName = $("[name='doName1']").val();
	var doLoadurl = $("[name='doLoadurl1']").val();
	var doCode = $("[name='doCode1']").val();
	var doIntron = $("[name='doIntron1']").val();
	
	alert(doTitle+"**"+doName);
	
	$.ajax({
		url:"../InsertDownloadOne",
		type:"POST",
		data:{"doTitle":doTitle,"doName":doName,"doLoadurl":doLoadurl,"doCode":doCode,"doIntron":doIntron},
		success: function (date) {
			var str = JSON.parse(date);
			if(str){
				$("#InsertDownload")[0].reset();
				$("#editModal").modal("hide");
				window.location.reload();
			}else{
				alert("新增失败，请检查输入是否合法！！！");
			}
		}
	})
	
};

//下载文件
function load(doId,doLoadnum){
	$.ajax({
		url:"../putDownloadnum",
		type:"POST",
		data:{"doId":doId,"doLoadnum":doLoadnum},
		success: function (date) {
			
		}
	})
};
