package com.sly.accountmanager.account.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sly.accountmanager.account.service.BillTypeService;

/**
 * 账单类型service实现
 * @author sly
 * @time 2018年11月19日
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class BillTypeServiceImpl implements BillTypeService {
	
}
