var str = window.location.search;
var comId =  str.substring(str.indexOf("comId")+"comId".length+1);

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
	Commodity(comId);
});

function Commodity(comid){
	$.ajax({
		url:"../getCommodityOne",
		type:"get",
		data:{"comId":comid},
		success:function(date){
			var com = JSON.parse(date);
			//alert(date);
			$('[name="comTitle"]').val(com.comTitle);
			$('[name="comKeyword"]').val(com.comKeyword);
			$('[name="comLinkurl"]').val(com.comLinkurl);
			$("#selectoptin").find("option[value='"+com.sortId+"']").attr("selected",true);  
			//$('#selectoptin').val(com.sortId);
			$('[name="comIntro"]').val(com.comIntro);
			$('[name="comId"]').val(com.comId);
			$('#imgalert').html('<img src = "../'+com.comPrinturl+'">');
		}
	})
}