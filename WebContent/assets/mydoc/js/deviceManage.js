$(function () {   
	//表格操作事件
	$('tbody').on('click', 'i', function (e) {
	    var btn = $(e.target);
	    /*根据i标签class显示对应模态框，列表数据data绑定到模态框title上，传给后台 selectDeviceOne*/
	    if (btn.hasClass("fa-edit")) {//修改信息
	        $("#editModal").modal("show");
	    } else if (btn.hasClass("fa-angle-right")) {//删除用户
	        $('#trashModal').modal('show');
	    } else if (btn.hasClass("fa-info")) {//显示设备信息
	        $('#infoModal').modal('show');//显示模态框
	    }
	});
});

/**提交修改框信息**/
function UpdateDevice(){
	var math = true;
	
	if($('[name="dev_name1"]').val() == ''){
		layer.msg("设备名称不能为空");
		math = false;
	}else if($('[name="dev_vnum1"]').val() == ''){
		layer.msg("设备IMSI不能为空");
		math = false;
	}else if($('[name="dev_type1"]').val() == ''){
		layer.msg('设备的类型不能为空');
		math = false;
	}
	
	if(math){
		var form = new FormData(document.getElementById("productmodelssi"));
		$.ajax({
	        url:"UpdateDeviceOne",
	        type:"post",
	        data:form,
	        processData:false,
	        contentType:false,
	        success:function(data){
	        	var str = JSON.parse(data);
	            if(str){
	            	layer.msg('修改设备信息成功');
	            	$('#productmodelssi')[0].reset();
	            	$("#editModal").modal('hide');
	            	location.reload();
	            }else{
	            	layer.msg('输入不合法,修改设备信息失败');
	            }
	        }
		});
	}
}

/**下发指令接口**/
function deletedevice(){
	var code = $('#xiafalinux').val();
	var connt = $('#lowerss').val();
	if(connt != null && connt != ''){
		$.ajax({
            url:"Lowerrxiafa",
            type:"post",
            async:false,
            data:{"code":code,"value":connt},
            success:function(data){
            	var str = JSON.parse(data);
                if(str){
                	layer.msg('下发命令成功');
                	$('#lowerss').val('');
                	$("#trashModal").modal('hide');
                }else{
                	layer.msg('下发命令失败');
                	$("#trashModal").modal('hide');
                	$('#lowerss').val('');
                }
            }
		});
	}else{
		layer.msg('下发内容不能为空');
	}
}

/**显示新增框**/
function addShow(){
	$('#addDevModal').modal('show');//显示模态框
}

/**提交新增信息框**/
function AddDevice(){
	var math = true;
	
	if($('[name="dev_name"]').val() == ''){
		layer.msg("设备名称不能为空");
		math = false;
	}else if($('[name="dev_vnum"]').val() == ''){
		layer.msg("设备IMSI不能为空");
		math = false;
	}else if($('[name="dev_type"]').val() == ''){
		layer.msg('设备的类型不能为空');
		math = false;
	}
	
	if(math){
		var form = new FormData(document.getElementById("productmodel"));
		$.ajax({
	        url:"addDeviceModal",
	        type:"post",
	        data:form,
	        processData:false,
	        contentType:false,
	        success:function(data){
	        	var str = JSON.parse(data);
	            if(str){
	            	layer.msg('新增设备成功');
	            	$('#productmodel')[0].reset();
	            	$("#addDevModal").modal('hide');
	            	location.reload();
	            }else{
	            	layer.msg('新增设备失败');
	            }
	        }
		});
	}
}

function test(dev_name,dev_vnum,dev_type,dev_code,dev_forward,dev_forport){

	$('[name="dev_name1"]').val(dev_name);
	$('[name="dev_vnum1"]').val(dev_vnum);
	$('[name="dev_type1"]').val(dev_type);
	$('[name="dev_code"]').val(dev_code);
	$('[name="dev_forward1"]').val(dev_forward);
	$('[name="dev_forport1"]').val(dev_forport);
};

function math(coid,dev_code,masht){
	if(window.confirm('是否确定删除设备？删除设备后，设备相关的所有数据将从平台消失，并且不可恢复。请谨慎操作.')){
		$.ajax({
	        url:"deleteDeviceOne",
	        type:"post",
	        data:{"dev_id":coid,"dev_code":dev_code,"dev_sumnum":masht},
	        success:function(data){
	            if(data){
	            	layer.msg('删除设备信息成功');
	            	location.reload();
	            }
	        }
		});	
	}
};

function kiths(dev_name,dev_vnum,dev_type,dev_forward,dev_forport){
	
	$('[name="dev_name2"]').val(dev_name);
	$('[name="dev_vnum2"]').val(dev_vnum);
	$('[name="dev_type2"]').val(dev_type);
	$('[name="dev_forward2"]').val(dev_forward);
	$('[name="dev_forport2"]').val(dev_forport);
};

function Lower(code){
	$('#xiafalinux').val(code);
};