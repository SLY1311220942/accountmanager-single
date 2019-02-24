package com.sly.accountmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sly.accountmanager.common.result.BaseResult;

@SpringBootApplication
@MapperScan(basePackages = "com.sly.accountmanager.**.mapper")
@Controller
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}
	
	@ResponseBody
	@RequestMapping("/test100")
	public BaseResult test100() {
		return new BaseResult(200,"haha");
	}

}
