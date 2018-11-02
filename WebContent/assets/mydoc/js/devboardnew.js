$(function(){
	$.ajax({
		url:"../selectBySortSubclass",
		type:"post",
		data:{"parentId":"5"},
		success:function(date){
			var sort = JSON.parse(date);
			for (var i = 0; i < sort.length; i++) {
				var resultset = "<option value='"+sort[i].sortId+"'>"+sort[i].sortName+"</option>";
				$('#selectoptin').append(resultset);
			}
		}
	});
});
        
//提交商品的接口
function Sbutton(){
	var formData = new FormData($('#Commodtiyform')[0]);
	$.ajax({
		url: "../commodityUpload",
        type: 'post',
        data: formData,
        cache: false,
        processData: false,
        contentType: false,
        success:function (date) {
        	if(date){
        		$('#Commodtiyform')[0].reset();
        		window.location.href = "devboard.html";
        	}else{
        		$('#Commodtiyform')[0].reset();
        		alert("输入信息不符合规定，请重新输入！！！");
        	}
        }
	});
}