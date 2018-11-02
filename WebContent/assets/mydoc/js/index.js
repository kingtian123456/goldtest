//点击打开模态框
//$('i.icon-trash').click(function(){
	//$(".modal").modal('show');
//});
$(function(){
	$.ajax({
        url: "../getProductinfos",
        type: "post",
        data: null,
        success: function (data) {
            var str = JSON.parse(data);
            for (var i = 0; i < str.pageData.length; i++) {
                var resultset = "<div class='col-md-6 col-sm-6'>" +
                		"<div class='portlet light'>" +
                			"<div class='portlet-title' style='border-bottom: none;margin-bottom: 0;'>" +
                				"<div class='caption'>" +
                					"<span class='caption-subject font-dark bold uppercase'>" +
                						"<a href='../SelectProductOne?pro_id="+str.pageData[i].pro_id+"'>"+str.pageData[i].pro_name+"</a>" +
                					"</span>" +
                				"</div>" +
                				"<div class='actions'>" +
                             	 	"<a class='btn btn-circle btn-icon-only btn-default' onclick='updateProduct(\""+str.pageData[i].pro_id+"\",\""+str.pageData[i].pro_name+"\",\""+str.pageData[i].pro_model+"\",\""+str.pageData[i].pro_contype+"\",\""+str.pageData[i].pro_devb+"\")'>" +
                             	 		"<i class='fa fa-edit'></i>" +
                             	 	"</a>" +
                             	 	"<a class='btn btn-circle btn-icon-only btn-default' onclick='delectProduct("+str.pageData[i].pro_id+","+str.pageData[i].dvg_devnum+")'>" +
                             	 		"<i class='icon-trash'></i>" +
                             	 	"</a>" +
                             	"</div>" +
                             "</div>" +
                             "<div class='clearfix'>" +
                                   "<span>设备接入方式:"+str.pageData[i].ament.at_name+"</span><br>" +
                                   "<span>创建时间:"+getLocalTime(str.pageData[i].pro_intime)+"</span>" +
                             "</div>" +
                             "<div class='portlet-body'>" +
                                   "<div class='number-info'>" +
                                        "<div class='param'>" +
                                           "<span class='icon-access access'><i class='fa fa-university'></i></span>" +
                                           "<p class='access-number'>" +
                                                "<span>"+str.pageData[i].dvg_devnum+"</span>台" +
                                           "</p>" +
                                           "<p class='access-equ'>接入设备</p>" +
                                        "</div>" +
                                   "</div>" +
                                     "<div class='clearfix'></div>" +
                               "</div>" +
                            "</div>" +
                        "</div>";
                $("#mytab").append(resultset);
            }

            $("#page").paging({
                pageNo: str.curPage,
                totalPage: str.totalPages,
                totalSize: str.totalRecords,
                callback: function (num) {
                    $.ajax({
                        url: "../getProductinfos",
                        type: "post",
                        data: {"curPage":num},
                        success: function (data) {
                            var str = JSON.parse(data);
                            $("#mytab").html("");
                            for (var i = 0; i < str.pageData.length; i++) {
                                var resultset = "<div class='col-md-6 col-sm-6'>" +
                        		"<div class='portlet light'>" +
                    			"<div class='portlet-title' style='border-bottom: none;margin-bottom: 0;'>" +
                    				"<div class='caption'>" +
                    					"<span class='caption-subject font-dark bold uppercase'>" +
                    						"<a href='../SelectProductOne?pro_id="+str.pageData[i].pro_id+"'>"+str.pageData[i].pro_name+"</a>" +
                    					"</span>" +
                    				"</div>" +
                    				"<div class='actions'>" +
                    				"<a onclick='updateProduct(\""+str.pageData[i].pro_id+"\",\""+str.pageData[i].pro_name+"\",\""+str.pageData[i].pro_model+"\",\""+str.pageData[i].pro_contype+"\",\""+str.pageData[i].pro_devb+"\")'>"+str.pageData[i].pro_name+"</a>" +
                                 	 		"<i class='fa fa-edit'></i>" +
                                 	 	"</a>" +
                                 	 	"<a class='btn btn-circle btn-icon-only btn-default' onclick='delectProduct("+str.pageData[i].pro_id+","+str.pageData[i].dvg_devnum+")'>" +
                                 	 		"<i class='icon-trash'></i>" +
                                 	 	"</a>" +
                                 	"</div>" +
                                 "</div>" +
                                 "<div class='clearfix'>" +
                                       "<span>设备接入方式:"+str.pageData[i].ament.at_name+"</span><br>" +
                                       "<span>创建时间:"+getLocalTime(str.pageData[i].pro_intime)+"</span>" +
                                 "</div>" +
                                 "<div class='portlet-body'>" +
                                       "<div class='number-info'>" +
                                            "<div class='param'>" +
                                               "<span class='icon-access access'><i class='fa fa-university'></i></span>" +
                                               "<p class='access-number'>" +
                                                    "<span>"+str.pageData[i].dvg_devnum+"</span>台" +
                                               "</p>" +
                                               "<p class='access-equ'>接入设备</p>" +
                                            "</div>" +
                                       "</div>" +
                                         "<div class='clearfix'></div>" +
                                   "</div>" +
                                "</div>" +
                            "</div>";
                                $("#mytab").append(resultset);
                            }
                        }
                    })
                }
            })

        },
        error: function (data) {
        	layer.msg("异常，请联系管理员!");
        }
    });
	
	$.ajax({
		url:"../addproduct",
		type:"get",
		data:null,
		success: function (data) {
			var str = JSON.parse(data);
			var date = str.classfiy;
			var xia = '<option  value="" >请选择</option>';
			for (var i = 0; i < date.length; i++) {
				xia += '<option  value="'+date[i].clas_id+'" >'+date[i].clas_name+'</option>';
			}
			var jier = str.agreemenet;
			var bttont = '';
			var bttont1 = '';
			for (var j = 0; j < jier.length; j++) {
				bttont += '<label class="mt-radio"><input type="radio"  name="pro_contype" value="'+jier[j].at_id+'">'+jier[j].at_name+'<span></span></label>';
				bttont1 += '<label class="mt-radio"><input type="radio" checked = ""  name="pro_contype1" value="'+jier[j].at_id+'">'+jier[j].at_name+'<span></span></label>';
			}
			
			$('#labelprodevb').append(xia);
			$('#groupRadio').append(bttont);
			$('#labelprodevb1').append(xia);
			$('#groupRadio1').append(bttont1);
		}
	});
})

function getLocalTime(nS) {     
    return new Date(parseInt(nS)).toLocaleString().substr(0,20);
}

/**显示新增框**/
function InPoroductshow(){
	$('#editModal').modal("show");
}

/**提交新增框**/
function InsertPorduct(){
	var math = true;
	
	if($('[name="pro_name"]').val() == ''){
		layer.msg("产品名字不能为空");
		math = false;
	}else if($('[name="pro_devb"]').val() == ''){
		layer.msg("开发板必须要选择一个");
		math = false;
	}else if($('[name="pro_model"]:checked').val() == undefined){
		layer.msg('当前进度必须要选择一个');
		math = false;
	}else if($('[name="pro_contype"]:checked').val() == undefined){
		layer.msg('协议必须要选择一个');
		math = false;
	}
	
	if(math){
		var form = new FormData(document.getElementById("insertmaxlikeChat"));
		$.ajax({
			url:"../addproductOne",
			type:"post",
			data:form,
			processData:false,
	        contentType:false,
	        success:function(date){
	        	if(date){
	        		$('#insertmaxlikeChat')[0].reset();
	        		$('#editModal').modal("hide");
	        		location.reload();
	        	}else{
	        		layer.msg('输入有误，请重新输入');
	        	}
	        }
		});
	}
	
}
/**修改框信息tian**/
function updateProduct(pro_id,name,model,pro_contype,devb){
	$('[name="pro_id"]').val(pro_id);
	$('[name="pro_name1"]').val(name);
	$('[name="pro_devb1"]').val(devb);
	
	$("input[name='pro_model1']").each(function(){
	    	if($(this).val() == model){
	    		$(this).prop( "checked", true );
	    	}
	});    
    $("input[name='pro_contype1']").each(function(){
    	if($(this).val() == pro_contype){
    		$(this).prop( "checked", true );
    	}
    });
	
	$('#updateModal').modal("show");
}

/**提交修改框**/
function UpdatesPorduct(){
	var maths = true;
	
	if($('[name="pro_name1"]').val() == ''){
	    layer.msg("产品名字不能为空");
		maths = false;
	}else if($('[name="pro_devb1"]').val() == ''){
		layer.msg("开发板必须要选择一个");
		maths = false;
	}else if($('[name="pro_model1"]:checked').val() == undefined){
		layer.msg('当前进度必须要选择一个');
		maths = false;
	}else if($('[name="pro_contype1"]:checked').val() == undefined){
		layer.msg('协议必须要选择一个');
		maths = false;
	}
	
	if(maths){
		var id = $('[name="pro_id"]').val();
		var name = $('[name="pro_name1"]').val();
		var devb = $('[name="pro_devb1"]').val();
		var model = $('[name="pro_model1"]:checked').val();
		var type = $('[name="pro_contype1"]:checked').val();
		
		$.ajax({
			url:"../UpdateProduct",
			type:"post",
			data:{"pro_id":id,"pro_name":name,"pro_devb":devb,"pro_model":model,"pro_contype":type},
			success:function(date){
				if(date){
					$('#updatemaxlikeChat')[0].reset();
	        		$('#updateModal').modal("hide");
	        		location.reload();
				}else{
					layer.msg('输入有误，请重新输入');
				}
			}
		});
	}
	
}

/**删除产品**/
function delectProduct(pro_id,dvg_devnum){
	if(window.confirm('是否确定删除产品？删除产品后，产品设备及设备相关的所有数据将从平台消失，并且不可恢复。请谨慎操作.')){
		$.ajax({
			url:"../delectProductOne",
			type:"post",
			data:{"pro_id":pro_id,"dvg_devnum":dvg_devnum},
			success:function(data){
	            if(data){
	            	location.reload();
	            }
	        }
		});
	}
}
