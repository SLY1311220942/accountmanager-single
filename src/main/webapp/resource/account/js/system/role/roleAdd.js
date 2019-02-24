function roleAdd() {
	var bootstrapValidator = $("#roleAddForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()) {
		var index = parent.layer.getFrameIndex(window.name);
		
		$.ajax({
			type: "post",
			url: webRoot + "/system/role/roleAdd",
			dataType: "json",
			data: {
				roleName: $.trim($("#roleName").val()),
				isOpen: $.trim($("#isOpen").val()),
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
						parent.roleSearch();
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
	$("#roleAddForm").bootstrapValidator({
		feedbackIcons: {
			validating: 'glyphicon glyphicon-refresh'
		},
		message: '',
		fields: {
			roleName: {
				message: '',
				validators: {
					callback: {
						message: "角色名称只能是中文数字字母，最大长度32位!",
						callback: function(value, validator) {
							if($.trim(value)) {
								var reg = /^([\d\u4E00-\u9FA5a-zA-Z]){1,32}$/;
								if(!$.trim(value).match(reg)) {
									return false;
								}
							}
							return true;
						}
					},
					notEmpty: {
						message: "角色名称不能为空!"
					}
				}
			},
			isOpen: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^0|1$/,
						message: "是否启用只能是启用和禁用!"
					},
				},
				notEmpty: {
					message: "是否启用不能为空!"
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