package com.sly.accountmanager.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.model.User;

/**
 * _页面权限标签类
 * 
 * @author sly
 * @time 2019年3月9日
 */
public class PermissionTag extends TagSupport {

	private static final long serialVersionUID = -3929218217733977640L;

	/**
	 * _权限访问页面
	 */
	private String accessPage;

	@Override
	public int doStartTag() throws JspException {
		// 获取session
		HttpSession session = this.pageContext.getSession();
		User user = (User) session.getAttribute(CommonConstant.SESSION_USER);
		System.out.println(user.getUsername());

		if (accessPage.equals("/haha")) {
			// 有权限执行标签体
			return EVAL_BODY_INCLUDE;
		} else {
			// 无权限跳过标签体
			return SKIP_BODY;
		}
	}

	public String getAccessPage() {
		return accessPage;
	}

	public void setAccessPage(String accessPage) {
		this.accessPage = accessPage;
	}

}
