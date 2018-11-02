$(function () {
	var C1 = window.location.href.split("?")[1];
	var dev_code = C1.split("=")[1];
         $.ajax({
             url: "../getDatasByInfo",
             type: "post",
             data: {"dev_codes":dev_code},
             success: function (data) {
                 var str = JSON.parse(data);
                 for (var i = 0; i < str.pageData.length; i++) {
                	 var mis  = str.pageData[i].ds_Swtch == 1?'上行':'下行';
                     var resultset = "<tr><td>" + mis + "</td><td>" + str.pageData[i].ds_id + "</td><td>" + str.pageData[i].ds_value + "</td><td>" + str.pageData[i].dev_codes + "</td><td>" + str.pageData[i].user_code + "</td><td>" + getLocalTime(str.pageData[i].ds_time) + "</td><tr>";
                     $("#mytab").append(resultset);
                 }

                 $("#page").paging({
                     pageNo: str.curPage,
                     totalPage: str.totalPages,
                     totalSize: str.totalRecords,
                     callback: function (num) {
                         $.ajax({
                             url: "../getDatasByInfo",
                             type: "post",
                             data: {"dev_codes":dev_code,"curPage":num},
                             success: function (data) {
                                 var str = JSON.parse(data);
                                 $("#mytab tbody").html("");
                                 for (var i = 0; i < str.pageData.length; i++) {
                                	 var mis  = str.pageData[i].ds_Swtch == 1?'上行':'下行';
                                     var resultset = "<tr><td>" + mis + "</td><td>" + str.pageData[i].ds_id + "</td><td>" + str.pageData[i].ds_value + "</td><td>" + str.pageData[i].dev_codes + "</td><td>" + str.pageData[i].user_code + "</td><td>" + getLocalTime(str.pageData[i].ds_time) + "</td><tr>";
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

function getParam(){
	C1=window.location.href.split("?")[1];
	C2=C1.split("=")[1];
	alert(C1); 
	alert(C2);
}

function getLocalTime(nS) {     
    return new Date(parseInt(nS)).toLocaleString().substr(0,21)
}