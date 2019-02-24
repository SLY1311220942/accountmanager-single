package com.sly.accountmanager.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sly.accountmanager.system.service.DicCodeService;

/**
 * DicCode Service 实现
 * @author sly
 * @time 2018年11月19日
 */
@Service
@Transactional(rollbackFor = { Exception.class })
public class DicCodeServiceImpl implements DicCodeService {
	
}
