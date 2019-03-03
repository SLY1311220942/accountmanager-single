function loadbillType(){
	$.ajax({
		type:"post",
		url:webRoot + "/account/billType/findBillTypeById",
		dataType: "json",
		data: {
			billTypeId: $.trim($("#billTypeId").val()),
			isAjax: 1
		},
		success: function(data){
			if(data.status == 200) {
				var billType = data.dataMap.billType;
				$("#billTypeName").val(billType.billTypeName)
			} else if(data.status == 300) {
				eval(data.dataMap.evalStr)
			} else {
				layer.alert(data.message, { icon: 2 });
			}
		}
	});
}


function billTypeUpdate() {
	var bootstrapValidator = $("#billTypeUpdateForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()) {
		var index = parent.layer.getFrameIndex(window.name);
		$.ajax({
			type: "post",
			url: webRoot + "/account/billType/billTypeUpdate",
			dataType: "json",
			data: {
				billTypeId: $.trim($("#billTypeId").val()),
				billTypeName: $.trim($("#billTypeName").val()),
				token: $.trim($("#token").val()),
				isAjax: 1
			},
			success: function(data) {
				if(data.status == 200) {
					layer.confirm(data.message, {
						btn : [ '确定' ]
					// 按钮
					}, function() {
						parent.billTypeSearch();
						parent.layer.close(index);
					});
				} else if(data.status == 300) {
					eval(data.dataMap.evalStr)
				} else {
					layer.alert(data.message, { icon: 2 });
				}
			}
		});
	}
}

function validator() {
	$("#billTypeUpdateForm").bootstrapValidator({
		feedbackIcons: {
			validating: 'glyphicon glyphicon-refresh'
		},
		message: '',
		fields: {
			billTypeName: {
				message: '',
				validators: {
					stringLength: {
						min: 1,
						max: 32,
						message: '账单类型名称不得超过32个字符!'
					},
					notEmpty: {
						message: "账单类型名称不能为空!"
					}
				}
			},
		}
	});
}