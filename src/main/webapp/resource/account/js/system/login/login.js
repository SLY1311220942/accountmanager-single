function login() {
	var encrypt = new JSEncrypt();
	encrypt.setPublicKey($("#publicKey").val());
	// 加密后的字符串
	var username = $.trim($("#username").val());
	var password = $.trim($("#password").val());
	var validate = $.trim($("#validate").val());
	var _997D08B3 = encrypt.encrypt(username);
	var _3DCBF670 = encrypt.encrypt(password);
	var _1EE97457 = encrypt.encrypt(validate);

	if (!username) {
		layer.alert("请输入用户名!");
		return;
	}
	if (!password) {
		layer.alert("请输入密码!");
		return;
	}
	if (!validate) {
		layer.alert("请输入验证码!");
		return;
	}

	$.ajax({
		type : "post",
		url : webRoot + "/system/login/login",
		dataType : 'json',
		data : {
			a997d08b3 : _997D08B3,
			a3dcbf670 : _3DCBF670,
			a1ee97457 : _1EE97457,
			isAjax : 1
		},
		success : function(data) {
			if (data.status == 200) {
				top.location = webRoot + "/index/home";
			} else if (data.status == 300) {
				eval(data.dataMap.evalStr);
			} else {
				$("#publicKey").val(data.dataMap.publicKey);
				layer.alert(data.message, { icon: 2 });
			}
		}
	});
}

function loadValidate() {
	var t = (new Date()).valueOf();
	var url = webRoot + "/system/validate/getJPGCode?type=l&t=" + t;
	$("#validatePic").attr("src", url);
}

$("#login").keydown(function(event) {
	if(event.keyCode == "13") {
		login();
	};
});