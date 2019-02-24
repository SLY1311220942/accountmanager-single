package com.sly.accountmanager.system.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sly.accountmanager.system.service.DemoService;

/**
 * Demo service 实现
 * @author sly
 * @time 2018年11月12日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DemoServiceImpl implements DemoService {
	

	/**
	 * 测试传图片到controller
	 * @param id
	 * @author SLY
	 * @time 2018-11-07
	 * @return
	 */
	@Override
	public ResponseEntity<byte[]> testPicture(String id) {
		InputStream inputStream = null;
		ResponseEntity<byte[]> entity = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			inputStream = new FileInputStream("D:\\桌面杂项\\总结杂项2018-06-27\\bug\\办理抵押中-补充资料列表未对齐.png");
			byte[] bytes = new byte[inputStream.available()];
			inputStream.read(bytes);
			headers.add("Content-Disposition", "attachment-inline,filename="+"imageName");
			entity = new ResponseEntity<byte[]>(bytes,headers, HttpStatus.OK);
			return entity;
		} catch (Exception e) {
			throw new RuntimeException("测试传图片到controller异常:" + ExceptionUtils.getStackTrace(e), e);
		} finally{
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}

	/**
	 * 上传文件
	 * @param file
	 * @param fileType
	 * @author SLY
	 * @time 2018-11-07
	 * @return
	 */
	@Override
	public Map<String, Object> uploadFile(MultipartFile file,String fileType) {
		try {
			String orgFilename= file.getOriginalFilename();
			//后缀
			String suffix = orgFilename.substring(orgFilename.lastIndexOf("."));
			String uuid =UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
			File dest = new File("D:\\test\\" + uuid + suffix);
			file.transferTo(dest);
			Map<String, Object> result = new HashMap<String, Object>(16);
			result.put("status", 200);
			result.put("message", "上传成功!");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * 下载
	 * @author SLY
	 * @time 2018-11-07
	 * @return
	 */
	@Override
	public ResponseEntity<byte[]> downloadFile() {
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<byte[]> entity = null;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("D:\\桌面杂项\\总结杂项2018-06-27\\bug\\办理抵押中-补充资料列表未对齐.png");
			byte[] bytes = new byte[inputStream.available()];
			inputStream.read(bytes);
			headers.add("Content-Disposition", "attachment,filename="+"imageName");
			entity = new ResponseEntity<byte[]>(bytes,headers, HttpStatus.OK);
			return entity;
		} catch (Exception e) {
			throw new RuntimeException("测试下载异常:" + ExceptionUtils.getStackTrace(e), e);
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * redis set
	 * @param key
	 * @param value
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	@Override
	public Integer redisSet(String key,String value) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * redis get
	 * @param key
	 * @param value
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	@Override
	public Object redisGet(String key) {
		// TODO Auto-generated method stub
		return null;
	}
}
