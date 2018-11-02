$(function () {
    //datetimepicker时间插件，引入文件 jquery.datetimepicker.css 和 jquery.datetimepicker.full.min.js
    $.datetimepicker.setLocale('ch');//时间插件设置为中文
    $('#uworkstart').datetimepicker({
    	format:'Y-m-d',
        datepicker:true,
        timepicker:false,
        step: 5//时间插件间隔
    });
    $('#uworkfinish').datetimepicker({
    	format:'Y-m-d',
    	datepicker:true,
        timepicker:false,
        step: 5//时间插件间隔
    });
    $('#uworkstart2').datetimepicker({
    	format:'Y-m-d',
        datepicker:true,
        timepicker:false,
        step: 5//时间插件间隔
    });
    $('#uworkfinish2').datetimepicker({
    	format:'Y-m-d',
    	datepicker:true,
        timepicker:false,
        step: 5//时间插件间隔
    });
	
	$.ajax({
		url:"getProductAnalysis",
        type:"post",
        data:null,
        processData:false,
        contentType:false,
        success:function(data){
        	var str = JSON.parse(data);
        	$("#zaixian").html(str.zxnum);
        	$("#xinzeng").html(str.datasnum);
        	$("#zongnum").html(str.totalnum);
        }
	});
	
   //产品详情打开模态框
	$('#proMsgBtn').click(function(){
		$("#proMsgModal").modal('show');
	});
	
	//设备趋势分析 总数据 begin
	//页面初次加载时执行selectByDeviceChart
	//增长设备数据 selectByDeviceZONG
	var date1 = [];
	var date2 = [];
	//设备总数据
	var date3 = [];
	var date4 = [];
	//增长信息数据
	var date5 = [];
	var date6 = [];
	
	////设备趋势分析 新增数据ajax
	$.ajax({
		url:"selectByDeviceChart",
        type:"post",
        data:null,
        success:function(data){
        	//alert(data);
        	var json = JSON.parse(data);
        	for (var i = 0; i < json.length; i++) {
				 date1.push(json[i].days);
				 date2.push(json[i].zsum);
			 }
        }
	});
	
	//设备按日期来分析数据
	$("#maxchat").click(function(){
		var head1 = document.getElementById('uworkstart').value;
		var tail1 = document.getElementById('uworkfinish').value;
		if(head1 !== null && head1 !== '' && tail1 !== null && tail1 !== '' && datetime(head1,tail1)){
			var form = new FormData(document.getElementById("minlikeChat"));
			$.ajax({
	            url:"selectByDeviceDate",
	            type:"post",
	            data:form,
	            processData:false,
	            contentType:false,
	            success:function(data){
	            	var str = JSON.parse(data);
	            	var zong = str.Zong;
	            	var xin = str.Xin;
	            	date1 = [];
	            	date2 = [];
	            	date3 = [];
	            	date4 = [];
	            	for (var i = 0; i < zong.length; i++) {
	            		date1.push(xin[i].days);
	   				 	date2.push(xin[i].zsum);
	   				 	date3.push(zong[i].days);
					 	date4.push(zong[i].zsum);
					}
	            	
	            	option = ({
	            		xAxis: {
	            	        type: 'category',
	            	        boundaryGap: false,
	            	        data:date3
	            	    },
	            	    yAxis: {
	            	        type: 'value'
	            	    },
	            	    series: [{
	            	        type:'line',
	            	        smooth:true,
	            	        itemStyle: {normal: {areaStyle: {type: 'default'}}},
	            	        data:date4
	            	    }]
	            	});
	            	
	            	myChart_dc1 = echarts.init(document.getElementById('dateCharts1'));
	         		myChart_dc1.setOption(option);
	            }
			});
		}
		
	});
	
	////设备趋势分析 总数据ajax
	var  myChart_dc1 = null;
	$.ajax({
		url:"selectByDeviceZONG",
        type:"post",
        data:null,
        success:function(data){
        	//alert(data);
        	var json = JSON.parse(data);
        	for (var i = 0; i < json.length; i++) {
				 date3.push(json[i].days);
				 date4.push(json[i].zsum);
			 }
        	
        	option = ({
        		xAxis: {
        	        type: 'category',
        	        boundaryGap: false,
        	        data:date3
        	    },
        	    yAxis: {
        	        type: 'value'
        	    },
        	    series: [{
        	        type:'line',
        	        smooth:true,
        	        itemStyle: {normal: {areaStyle: {type: 'default'}}},
        	        data:date4
        	    }]
        	});
        	
        	myChart_dc1 = echarts.init(document.getElementById('dateCharts1'));
     		myChart_dc1.setOption(option);
        }
	});
	////数据点趋势分析 新增数据ajax
	var myChart_dc3 = null;
	$.ajax({
		url:"selectByDatasChart",
        type:"post",
        data:null,
        success:function(data){
        	//alert(data);
        	var json = JSON.parse(data);
        	for (var i = 0; i < json.length; i++) {
        		 date5.push(json[i].days);
				 date6.push(json[i].zsum);
			}
        	
        	option = ({
        		xAxis: {
        	        type: 'category',
        	        boundaryGap: false,
        	        data:date5
        	    },
        	    yAxis: {
        	        type: 'value'
        	    },
        	    series: [{
        	        type:'line',
        	        smooth:true,
        	        itemStyle: {normal: {areaStyle: {type: 'default'}}},
        	        data:date6
        	    }]
        	});
        	
        	myChart_dc3 = echarts.init(document.getElementById('dateCharts3'));
     		myChart_dc3.setOption(option);
        }
	});
	
	//设备信息按日期来分析数据
	$("#minchat").click(function(){
		var head2 = document.getElementById('uworkstart2').value;
		var tail2 = document.getElementById('uworkfinish2').value;
		if(head2 !== null && head2 !== '' && tail2 !== null && tail2 !== '' && datetime(head2,tail2)){
			var form = new FormData(document.getElementById("maxlikeChat"));
			$.ajax({
	            url:"selectByDatasDateEchar",
	            type:"post",
	            data:form,
	            processData:false,
	            contentType:false,
	            success:function(data){
	            	var json = JSON.parse(data);
	            	//alert(json);
	            	date5 = [];
	            	date6 = [];
	            	for (var i = 0; i < json.length; i++) {
	           		 	date5.push(json[i].days);
	           		 	date6.push(json[i].zsum);
	            	}
	            	
	            	option1 = ({
	            		xAxis: {
	            	        type: 'category',
	            	        boundaryGap: false,
	            	        data:date5
	            	    },
	            	    yAxis: {
	            	        type: 'value'
	            	    },
	            	    series: [{
	            	        type:'line',
	            	        smooth:true,
	            	        itemStyle: {normal: {areaStyle: {type: 'default'}}},
	            	        data:date6
	            	    }]
	            	});
	            	
	            	myChart_dc3 = echarts.init(document.getElementById('dateCharts3'));
	         		myChart_dc3.setOption(option1);
	            }
			});
		}
	});

	// 设备趋势分析tab切换
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		var activeTab = $(e.target).attr("href"); // 激活的标签页
		if(activeTab=="#alldata"){
			//设备趋势分析 总数据 begin
			var dom_dc1 = document.getElementById("dateCharts1");
			var myChart_dc1 = echarts.init(dom_dc1);
			var app = {};
			option = null;
			myChart_dc1.setOption({
				xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        data:date3
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [{
			        type:'line',
			        smooth:true,
			        itemStyle: {normal: {areaStyle: {type: 'default'}}},
			        data:date4
			    }]
			});				
			//设备趋势分析 总数据 end
		}else if(activeTab=="#newdata"){
			//设备趋势分析 新增数据 begin
			var dom_dc2 = document.getElementById("dateCharts2");
			var myChart_dc2 = echarts.init(dom_dc2);
			var app = {};
			option = null;
			myChart_dc2.setOption({
				xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        data: date1
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [{
			        type:'line',
			        smooth:true,
			        itemStyle: {normal: {areaStyle: {type: 'default'}}},
			        data:date2
			    }]
			});				
			//设备趋势分析 新增数据 end
		}
	})
});

/**添加设备弹框**/
function AddShow(){
	$("#addDeviceModal").modal('show');
}

/**提交设备信息，进行设备新增**/
function InsertSensor(){
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
	            	$("#addDeviceModal").modal('hide');
	            	location.reload();
	            }else{
	            	layer.msg('参数输入不合法，请重新输入');
	            }
	        }
		});
	}
	
}

function datetime(beginDate,endDate){
	var d1 = new Date(beginDate);  
	var d2 = new Date(endDate);  
	if(beginDate !="" && endDate !="" && d1 >=d2){  
		layer.msg("开始时间不能大于结束时间！");  
	  return false;  
	}
	return true;
};

/**显示编辑框**/
function Inshow(){
	$('#updateModal').modal("show");
}

/**提交编辑框**/
function UpdatesPorduct(){
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
		
		var form = new FormData(document.getElementById("updatemaxlikeChat"));
		$.ajax({
			url:"UpdateProduct",
			type:"post",
			data:form,
			processData:false,
            contentType:false,
            success:function(date){
            	if(date){
            		$('#updatemaxlikeChat')[0].reset();
	        		$('#updateModal').modal("hide");
	        		location.reload();
            	}else{
            		layer.msg('输入有误，请重新输入');
            	}
            }
		})
	}
}