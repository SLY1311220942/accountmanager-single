package com.sly.accountmanager.system.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * demo service接口
 * 
 * @author sly
 * @time 2018年11月12日
 */
public interface DemoService {
	
	/**
	 * 测试传图片到controller
	 * @param id
	 * @return
	 * @author sly
	 * @time 2018年11月12日
	 */
	ResponseEntity<byte[]> testPicture(String id);

	/**
	 * 上传文件
	 * @param file
	 * @param fileType
	 * @return
	 * @author sly
	 * @time 2018年11月12日
	 */
	Map<String, Object> uploadFile(MultipartFile file, String fileType);

	/**
	 * 下载
	 * @return
	 * @author sly
	 * @time 2018年11月12日
	 */
	ResponseEntity<byte[]> downloadFile();
	
	/**
	 * redis set
	 * @param key
	 * @param value
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	Integer redisSet(String key,String value);
	
	/**
	 * redis get
	 * @param key
	 * @param value
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	Object redisGet(String key);
}
