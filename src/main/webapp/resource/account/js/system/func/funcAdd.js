function funcAdd() {
	var bootstrapValidator = $("#funcAddForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()) {
		var index = parent.layer.getFrameIndex(window.name);
		$.ajax({
			type: "post",
			url: webRoot + "/system/func/funcAdd",
			dataType: "json",
			data: {
				parentId: $.trim($("#parentId").val()),
				funcName: $.trim($("#funcName").val()),
				funcIcon: $.trim($("#funcIcon").val()),
				funcUrl: $.trim($("#funcUrl").val()),
				funcTag: $.trim($("#funcTag").val()),
				funcSort: $.trim($("#funcSort").val()),
				isOpen: $.trim($("#isOpen").val()),
				funcType: $.trim($("#funcType").val()),
				remark: $.trim($("#remark").val()),
				token: $.trim($("#token").val()),
				isAjax: 1
			},
			success: function(data) {
				if(data.status == 200) {
					layer.confirm(data.message, {
						btn: ['确定']
						// 按钮
					}, function() {
						parent.func_initZTree();
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
	$("#funcAddForm").bootstrapValidator({
		feedbackIcons: {
			validating: 'glyphicon glyphicon-refresh'
		},
		message: '',
		fields: {
			funcName: {
				message: '',
				validators: {
					callback: {
						message: "功能名称只能输入中文、数字、字母,长度为1到32个字符!",
						callback: function(value, validator) {
							if($.trim(value)) {
								var reg = /^([\u4e00-\u9fa5a-zA-Z0-9_\-\(\)\/]){1,32}$/;
								if(!$.trim(value).match(reg)) {
									return false;
								}
							}
							return true;
						}
					},
					notEmpty: {
						message: "功能名称不能为空!"
					}
				}
			},
			funcIcon: {
				message: '',
				validators: {
					stringLength: {
						min: 1,
						max: 32,
						message: "功能图标不超过32个字符!"
					}
				}
			},
			funcUrl: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^([a-zA-Z0-9_/]){1,240}$/,
						message: "功能Url只能输入数字、字母、/、_,长度为1到240个字符!"
					},
				}
			},
			funcTag: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^0|1$/,
						message: "功能标签只能为菜单或按钮!"
					},
				}
			},
			funcSort: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^[\d]{1,4}$/,
						message: "功能排序为大于0的正整数,最多4位!"
					},
				}
			},
			isOpen: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^0|1$/,
						message: "是否启用只能为启用和未启用!"
					},
				}
			},
			funcType: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^0|1$/,
						message: "功能类型只能为系统内置和普通功能!"
					},
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