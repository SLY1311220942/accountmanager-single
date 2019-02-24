function loadBill(){
	$.ajax({
		type:"post",
		url: webRoot + "/account/bill/findBillById",
		dataType: "json",
		data: {
			billId: $.trim($("#billId").val()),
			isAjax: 1
		},
		success:function(data){
			if(data.status == 200){
				var bill = data.dataMap.bill;
				$("#userName").text(bill.userName);
				$("#createTime").text(bill.createTime);
				if(bill.revexpType == 0){
					$("#revexpType").text("支出");
				}else if(bill.revexpType == 1){
					$("#revexpType").text("收入");
				}else{
					$("#revexpType").text(bill.revexpType)
				}
				$("#billTime").text(bill.billTime);
				$("#billAmount").text(bill.billAmount);
				$("#billTypeName").text(bill.billTypeName);
				$("#billDetail").text(bill.billDetail);
				$("#remark").text(bill.remark);
			}else if(data.status == 300){
				eval(data.dataMap.evalStr);
			}else{
				layer.alert(data.message, { icon: 2 });
			}
		}
	});
}