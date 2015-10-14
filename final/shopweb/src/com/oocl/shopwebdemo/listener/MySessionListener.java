package com.oocl.shopwebdemo.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/*
 *  在session中创建获取销毁的时候此监听器会操作
 * */
public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// session是服务器端容器,客户端是没有session
		System.out.println("sessionid:" + event.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println(event.getSession().getId());
		//获取即将销毁的session,然后从session中拿到登录信息,执行 insert语句,吧当前的用户信息与当前的时间添加到DB中(用户的退出时间)
		System.out.println("------sessionDestroyed------");
	}

}
