package com.google.thrift.service;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.google.thrift.service.imp.IHelloWorldService;

public class HelloWorldServiceAsync implements IHelloWorldService.Iface {

	private static final Logger logger = Logger.getLogger(HelloWorldServiceAsync.class);
	
	@Override
	public String sayHello(String username) throws TException {
		logger.info("sayHello方法延迟10s返回");
		for (int i = 1; i <= 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			
			logger.info(i);
		}
		
		return "Hello, " + username + "，这是异步thrift服务";
	}

}
