$(function () { 
	$(".appitem-box").on('click','.app-content-div',function(e){
	    var btn = $(e.target);
	    if(btn.hasClass("icon-settings")){
	    	$('#editModal').modal('show');//显示模态框
	    }else if(btn.hasClass("icon-trash")){
	    	//获取数据并填入模态框，略
	    	$('#delModal').modal('show');//显示模态框
	    }
	}); 
	// 修改设置 添加logo
	$("#file0").change(function(){  
        var objUrl = getObjectURL(this.files[0]) ;  
        if (objUrl) {  
            $("#img0").attr("src", objUrl) ;  
        }  
    }) ;  
    // 创建应用 添加logo
    $("#file1").change(function(){  
        var objUrl = getObjectURL(this.files[0]) ;  
        if (objUrl) {  
            $("#img1").attr("src", objUrl) ;  
        }  
    }) ;  
    //建立一个可存取到file的url  
    function getObjectURL(file) {  
        var url = null;   
        if (window.createObjectURL!=undefined) { // basic  
            url = window.createObjectURL(file);  
        } else if (window.URL!=undefined) { // mozilla(firefox)  
            url = window.URL.createObjectURL(file);  
        } else if (window.webkitURL!=undefined) { // webkit or chrome  
            url = window.webkitURL.createObjectURL(file);  
        }  
        return url;  
    }  

    // 创建应用
	$("#addAppBtn").click(function(){
		$('#addAppModal').modal('show');//显示模态框
	});

});