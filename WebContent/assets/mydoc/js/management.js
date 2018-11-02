$(function () {   
	$.ajax({
        url: "../selectBySortZiInfo",
        type: "post",
        data: null,
        success: function (data) {
            var str = JSON.parse(data);
            for (var i = 0; i < str.pageData.length; i++) {
            	var resultset = "<tr><td>" + str.pageData[i].sortId + "</td><td>" + str.pageData[i].sortName + "</td><td>" + str.pageData[i].sortSeachkey + "</td><td>" + str.pageData[i].sortRemark + "</td><td>" + str.pageData[i].sortContent + "</td><td>" + getLocalTime(str.pageData[i].sortUptime) + 
                "</td><td><i class='fa fa-edit' onclick=\"SelectMan('"+str.pageData[i].sortId+"','"+str.pageData[i].sortName+"','"+str.pageData[i].sortSeachkey+"','"+str.pageData[i].sortRemark+"','"+str.pageData[i].sortContent+"','"+str.pageData[i].sortParentid+"','"+str.pageData[i].sortDir+"')\"></i>&nbsp;&nbsp;" +
                "<i class='fa fa-trash' onclick=\"DeleteSort('"+str.pageData[i].sortId+"','"+str.pageData[i].sortParentid+"')\"></i>&nbsp;&nbsp;" +
                "<i class='fa fa-info' onclick=\"SelectKan('"+str.pageData[i].sortName+"','"+str.pageData[i].sortSeachkey+"','"+str.pageData[i].sortRemark+"','"+str.pageData[i].sortContent+"','"+str.pageData[i].sortParentid+"','"+str.pageData[i].sortDir+"')\"></i></td><tr>";
                $("#mytab").append(resultset);
            }

            $("#page").paging({
                pageNo: str.curPage,
                totalPage: str.totalPages,
                totalSize: str.totalRecords,
                callback: function (num) {
                    $.ajax({
                        url: "../selectBySortZiInfo",
                        type: "post",
                        data:{"curPage":num},
                        success: function (data) {
                            var str = JSON.parse(data);
                            $("#mytab tbody").html("");
                            for (var i = 0; i < str.pageData.length; i++) {
                            	var resultset = "<tr><td>" + str.pageData[i].sortId + "</td><td>" + str.pageData[i].sortName + "</td><td>" + str.pageData[i].sortSeachkey + "</td><td>" + str.pageData[i].sortRemark + "</td><td>" + str.pageData[i].sortContent + "</td><td>" + getLocalTime(str.pageData[i].sortUptime) + 
                                "</td><td><i class='fa fa-edit' onclick=\"SelectMan('"+str.pageData[i].sortId+"','"+str.pageData[i].sortName+"','"+str.pageData[i].sortSeachkey+"','"+str.pageData[i].sortRemark+"','"+str.pageData[i].sortContent+"','"+str.pageData[i].sortParentid+"','"+str.pageData[i].sortDir+"')\"></i>&nbsp;&nbsp;" +
                                "<i class='fa fa-trash' onclick=\"DeleteSort('"+str.pageData[i].sortId+"','"+str.pageData[i].sortParentid+"')\"></i>&nbsp;&nbsp;" +
                                "<i class='fa fa-info' onclick=\"SelectKan('"+str.pageData[i].sortName+"','"+str.pageData[i].sortSeachkey+"','"+str.pageData[i].sortRemark+"','"+str.pageData[i].sortContent+"','"+str.pageData[i].sortParentid+"','"+str.pageData[i].sortDir+"')\"></i></td><tr>";
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
	
	fulei();
});

//填充修改框
function SelectMan(sortId,sortName,sortSeachkey,sortRemark,sortContent,sortParentid,sortDir){
	$('[name="sortId"]').val(sortId);
	$('[name="sortName"]').val(sortName);
	$('[name="sortSeachkey"]').val(sortSeachkey);
	$('[name="sortRemark"]').val(sortRemark);
	$('[name="sortContent"]').val(sortContent);
	$('[name="sortParentid"]').val(sortParentid);
	$('[name="sortDir"]').val(sortDir);
}

//填充查看框
function SelectKan(sortName,sortSeachkey,sortRemark,sortContent,sortParentid,sortDir){
	$('[name="sortName2"]').val(sortName);
	$('[name="sortSeachkey2"]').val(sortSeachkey);
	$('[name="sortRemark2"]').val(sortRemark);
	$('[name="sortContent2"]').val(sortContent);
	$('[name="sortParentid2"]').val(sortParentid);
	$('[name="sortDir2"]').val(sortDir);
}

//提交修改框
function UpdateSort(){
	var form = new FormData(document.getElementById("UpdateSort"));
	$.ajax({
        url:"../putBySortOne",
        type:"post",
        data:form,
        processData:false,
        contentType:false,
        success:function(data){
        	var str = JSON.parse(data);
            if(str){
            	$('#UpdateSort')[0].reset();
            	$("#editModals").modal('hide');
            	location.reload();
            }else{
            	alert('修改设备失败');
            }
        }
	});
}

//显示新增弹框
function Show(){
	$('#editModal').modal("show");
}

//新增分类
function InsertSort(){
	var sortName = $('[name="sortName1"]').val();
	var sortSeachkey = $('[name="sortSeachkey1"]').val();
	var sortRemark = $('[name="sortRemark1"]').val();
	var sortContent = $('[name="sortContent1"]').val();
	var sortParentid = $('[name="sortParentid1"]').val();
	var sortDir = $('[name="sortDir1"]').val();
	
	$.ajax({
		url:"../insertBySortOne",
		type:"post",
		data:{"sortName":sortName,"sortSeachkey":sortSeachkey,"sortRemark":sortRemark,"sortContent":sortContent,"sortParentid":sortParentid,"sortDir":sortDir},
		success:function(date) {
			var futes = JSON.parse(date);
			if(futes){
				$('#InsertSort')[0].reset();
            	$("#editModal").modal('hide');
            	location.reload();
			}else{
				alert('新增设备失败');
			}
		}
	})
}

//删除分类
function DeleteSort(sortId,parntId){
	if(window.confirm('是否确定删除分类？删除分类后，分类及分类相关的所有数据将从平台消失，并且不可恢复。请谨慎操作.')){
		$.ajax({
			url:"../deleteBySortOne",
			type:"post",
			data:{"sortId":sortId,"parntId":parntId},
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

//查询所有的分类
function fulei(){
	$.ajax({
		url:"../selectBySortParentInfo",
		type:"post",
		data:null,
		success:function(date) {
			//alert(date);
			var str = JSON.parse(date);
			for (var i = 0; i < str.length; i++) {
				$("#Parent1").append('<option value="'+str[i].sortId+'">'+str[i].sortName+'</option>');
				$("#Parent2").append('<option value="'+str[i].sortId+'">'+str[i].sortName+'</option>');
				$("#Parent3").append('<option value="'+str[i].sortId+'">'+str[i].sortName+'</option>');
			}
		}
	})
}

//表格操作事件
$('tbody').on('click', 'i', function (e) {
    var btn = $(e.target);
    /*根据i标签class显示对应模态框，列表数据data绑定到模态框title上，传给后台 selectDeviceOne*/
    if (btn.hasClass("fa-edit")) {//修改信息
        $("#editModals").modal("show");
    }else if (btn.hasClass("fa-info")) {//显示设备信息
        $('#infoModal').modal('show');//显示模态框
    }
});

function getLocalTime(nS) {     
    return new Date(parseInt(nS)).toLocaleString().substr(0,20);
}