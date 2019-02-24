function userAdd() {
	var bootstrapValidator = $("#userAddForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()) {
		var index = parent.layer.getFrameIndex(window.name);
		
		var encrypt = new JSEncrypt();
		encrypt.setPublicKey($("#publicKey").val());
		var username = encrypt.encrypt($.trim($("#username").val()));
		var password = encrypt.encrypt($.trim($("#password").val()));
		
		$.ajax({
			type: "post",
			url: webRoot + "/system/user/userAdd",
			dataType: "json",
			data: {
				username: username,
				nickname: $.trim($("#nickname").val()),
				realname: $.trim($("#realname").val()),
				password: password,
				phone: $.trim($("#phone").val()),
				tel: $.trim($("#tel").val()),
				email: $.trim($("#email").val()),
				sex: $.trim($("#sex").val()),
				userTag: $.trim($("#userTag").val()),
				status: $.trim($("#status").val()),
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
						parent.userSearch();
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
	$("#userAddForm").bootstrapValidator({
		feedbackIcons: {
			validating: 'glyphicon glyphicon-refresh'
		},
		message: '',
		fields: {
			username: {
				message: '',
				validators: {
					callback: {
						message: "用户名称只能输入中文、数字、字母_.,长度为1到32个字符!",
						callback: function(value, validator) {
							if($.trim(value)) {
								var reg = /^([\u4e00-\u9fa5a-zA-Z0-9]){1,32}$/;
								if(!$.trim(value).match(reg)) {
									return false;
								}
							}
							return true;
						}
					},
					notEmpty: {
						message: "用户名称不能为空!"
					}
				}
			},
			nickname: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^([\u4e00-\u9fa5a-zA-Z0-9]){1,32}$/,
						message: "昵称只能输入中文、数字、字母,长度为1到32个字符!"
					},
				}
			},
			realname: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^([\u4e00-\u9fa5a-zA-Z0-9]){1,32}$/,
						message: "真实姓名只能输入中文、数字、字母,长度为1到32个字符!"
					},
					notEmpty: {
						message: "真实姓名不能为空!"
					}
				}
			},
			password: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^(?!^[0-9]+$)(?!^[a-zA-Z]+$)(?!^[_#@.]+$).{8,32}$/,
						message: "密码至少包括字母、数字、特殊字符中的2种组合，长度为8到32个字符!"
					},
					notEmpty: {
						message: "密码不能为空!"
					}
				}
			},
			repassword: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^(?!^[0-9]+$)(?!^[a-zA-Z]+$)(?!^[_#@.]+$).{8,32}$/,
						message: "确认密码至少包括字母、数字、特殊字符中的2种组合，长度为8到32个字符!"
					},
					notEmpty: {
						message: "确认密码不能为空!"
					}
				}
			},
			phone: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^1(3|4|5|6|7|8)\d{9}$/,
						message: "请输入正确的手机号!"
					},
					notEmpty: {
						message: "手机号不能为空!"
					}
				}
			},
			tel: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^0\d{2,3}-\d{7,8}$/,
						message: "请输入正确的座机号!"
					},
				}
			},
			email: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^(?=\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$).{1,64}$/,
						message: "请输入正确的邮箱格式，长度最大为64个字符!"
					},
					notEmpty: {
						message: "邮箱不能为空!"
					}
				}
			},
			sex: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^M|W$/,
						message: "用户性别只能为男或女!"
					},
					notEmpty: {
						message: "用户性别不能为空!"
					}
				}
			},
			userTag: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^1|2$/,
						message: "用户类型只能为系统内置用户和普通用户!"
					},
					notEmpty: {
						message: "用户类型不能为空!"
					}
				}
			},
			status: {
				message: '',
				validators: {
					regexp: {	 
						regexp: /^0|1|2$/,
						message: "请选择正确的用户状态!"
					},
					notEmpty: {
						message: "用户状态不能为空!"
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