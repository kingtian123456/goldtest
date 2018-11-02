$(function () {   
	$.ajax({
        url: "../getLabsPageInfo",
        type: "post",
        data: null,
        success: function (data) {
            var str = JSON.parse(data);
            for (var i = 0; i < str.pageData.length; i++) {
            	var ver = str.pageData[i].labsValidation  == 1?'已验证':'未验证';
            	var dist = str.pageData[i].labsSend == 1?'已派发':'未派发';
            	var dait = str.pageData[i].labsValidation  == 1?getLocalTime(str.pageData[i].labsActtime):'';
            	var user = str.pageData[i].labsUser == null?'':str.pageData[i].labsUser;
                var resultset = "<tr><td>" + str.pageData[i].labsId + "</td><td>" + str.pageData[i].labsNumber + "</td><td>" + user + "</td><td>" + ver + "</td><td>" + dist + "</td><td>" + dait + 
                "</td><td><i class='fa fa-edit'  onclick=\"SelectLabs('"+str.pageData[i].labsId+"','"+str.pageData[i].labsSend+"')\"></i>&nbsp;&nbsp;" +
                "<i class='fa fa-trash' onclick=\"DeleteLabs('"+str.pageData[i].labsId+"','"+str.pageData[i].labsValidation+"','"+str.pageData[i].labsSend+"')\"></i></td><tr>";
                $("#mytab").append(resultset);
            }

            $("#page").paging({
                pageNo: str.curPage,
                totalPage: str.totalPages,
                totalSize: str.totalRecords,
                callback: function (num) {
                    $.ajax({
                        url: "../getLabsPageInfo",
                        type: "post",
                        data: {"curPage":num},
                        success: function (data) {
                            var str = JSON.parse(data);
                            $("#mytab tbody").html("");
                            for (var i = 0; i < str.pageData.length; i++) {
                            	var ver = str.pageData[i].labsValidation  == 1?'已验证':'未验证';
                            	var dist = str.pageData[i].labsSend == 1?'已派发':'未派发';
                            	var dait = str.pageData[i].labsValidation  == 1?getLocalTime(str.pageData[i].labsActtime):'';
                            	var user = str.pageData[i].labsUser == null?'':str.pageData[i].labsUser;
                                var resultset = "<tr><td>" + str.pageData[i].labsId + "</td><td>" + str.pageData[i].labsNumber + "</td><td>" + user + "</td><td>" + ver + "</td><td>" + dist + "</td><td>" + dait + 
                                "</td><td><i class='fa fa-edit'  onclick=\"SelectLabs('"+str.pageData[i].labsId+"','"+str.pageData[i].labsSend+"')\"></i>&nbsp;&nbsp;" +
                                "<i class='fa fa-trash' onclick=\"DeleteLabs('"+str.pageData[i].labsId+"','"+str.pageData[i].labsValidation+"','"+str.pageData[i].labsSend+"')\"></i></td><tr>";
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
});

//显示新增弹框
function Show(){
	$("#editModal").modal("show");
}

//提交批量新增序列码功能
function InsertLabs(){
	var num = $('[name="num"]').val();
	if(num > 100){
		$.ajax({
			url:"../InsertLabsInfo",
			type:"post",
			data:{"num":num},
			success: function (date) {
				var  reuslt = JSON.parse(date);
				if(reuslt){
					window.location.reload();
				}else{
					alert("新增序列码失败！！！");
				}
			}
		});
	}else{
		alert("批量插入的数量必要为大于100，而且能被10整除的正整数！！！！");
	}
}

//删除分类
function DeleteLabs(labsId,labsValidation,labsSend){
	if(window.confirm('是否确定删除序列号？删除序列号后，序列号将从平台消失，并且不可恢复。请谨慎操作.')){
		if(labsValidation == 0 && labsSend == 1){
			alert("序列号处于派送还没有验证，不能删除序列号");
		}else{
			$.ajax({
				url:"../deleteLabsOne",
				type:"post",
				data:{"labsId":labsId},
				success:function(date){
					var  reuslt = JSON.parse(date);
					if(reuslt){
						window.location.reload();
					}else{
						alert("删除分类失败！！！");
					}
				}
			});
		}
		
	}
}

//填充修改框
function SelectLabs(labsId,state){
	if(state == 0){
		$('[name="labsId"]').val(labsId);
		$("#editModals").modal("show");
	}else{
		alert("该序列号已经是派送状态，不能进行修改！！！");
	}
}

//提交修改框
function UpdateLabs(){
	var labsId = $('[name="labsId"]').val();
	var labsSend = $('input[name="labsSend"]:checked').val();
	$.ajax({
		url:"../updateLabsOne",
		type:"post",
		data:{"labsId":labsId,"state":labsSend},
		success:function(date){
			var str = JSON.parse(date);
            if(str){
            	$('#UpdateLabs')[0].reset();
            	$("#editModals").modal('hide');
            	location.reload();
            }else{
            	alert('修改状态失败');
            }
		}
	});
}

function getLocalTime(nS) {     
    return new Date(parseInt(nS)).toLocaleString().substr(0,20)
}
