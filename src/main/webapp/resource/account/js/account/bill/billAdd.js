
function loadBillType() {
	$.ajax({
		type: "post",
		url: webRoot + "/account/billType/findAllTopBillType",
		dataType: "json",
		data: {
			isAjax: 1
		},
		success: function(data) {
			if(data.status == 200) {
				var html = new StringBuffer();
				html.append('<option value="">请选择</option>');
				for(var i = 0; i < data.rows.length; i++) {
					html.append('<option value="' + data.rows[i].billTypeId + '">' + data.rows[i].billTypeName + '</option>');
				}
				
				$("#billTypeId").html(html.toString());
				
			} else if(data.status == 300) {
				eval(data.dataMap.evalStr);
			} else {
				layer.alert(data.message, { icon: 2 });
			}
		}
	});
}


function billAdd(w) {
	var bootstrapValidator = $("#billAddForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()) {
		var l = Ladda.create(w);
		l.start();
		var index = parent.layer.getFrameIndex(window.name);
		$.ajax({
			type: "post",
			url: webRoot + "/account/bill/billAdd",
			dataType: "json",
			data: {
				revexpType: $.trim($("#revexpType").val()),
				billTime: $.trim($("#billTime").val()),
				billAmount: $.trim($("#billAmount").val()),
				billTypeId: $.trim($("#billTypeId").val()),
				billDetail: $.trim($("#billDetail").val()),
				remark: $.trim($("#remark").val()),
				token: $.trim($("#token").val()),
				isAjax: 1
			},
			success: function(data) {
				if(data.status == 200) {
					layer.confirm(data.message, {
						btn : [ '确定' ]
					// 按钮
					}, function() {
						parent.billSearch();
						parent.layer.close(index);
					});
				} else if(data.status == 300) {
					eval(data.dataMap.evalStr);
				} else {
					layer.alert(data.message, { icon: 2 });
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				layer.alert(COMMON_REQUEST_RESPONSE_FAIL, { icon: 2 });
			},
			complete: function(XMLHttpRequest, textStatus){
				l.stop();
			}
		});
	}
}

function validator() {
	$("#billAddForm").bootstrapValidator({
		feedbackIcons: {
			validating: 'glyphicon glyphicon-refresh'
		},
		message: '',
		fields: {
			revexpType: {
				message: '',
				validators: {
					notEmpty: {
						message: "收支类型不能为空!"
					}
				}
			},
			billTime: {
				message: '',
				validators: {
					notEmpty: {
						message: "账单时间不能为空!"
					}
				}
			},
			billAmount: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /(^[\d]{1,10}$)|(^[\d]{1,10}.[\d]{1,2}$)/,
						message: "账单金额不超过10位整数,最多保留2位小数!"
					},
					notEmpty: {
						message: "账单金额不能为空!"
					}
				}
			},
			billDetail: {
				message: '',
				validators: {
					stringLength: {
						min: 1,
						max: 240,
						message: '账单摘要不得超过240个字符!'
					},
					notEmpty: {
						message: "账单摘要不能为空!"
					}
				}
			},
			remark: {
				message: '',
				validators: {
					stringLength: {
						min: 1,
						max: 240,
						message: '备注信息不得超过240个字符!'
					}
				}
			}
		}
	});
}