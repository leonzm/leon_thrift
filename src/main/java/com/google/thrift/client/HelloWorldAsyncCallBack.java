package com.google.thrift.client;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

import com.google.thrift.service.imp.IHelloWorldService;
import com.google.thrift.service.imp.IHelloWorldService.AsyncClient.sayHello_call;

public class HelloWorldAsyncCallBack implements AsyncMethodCallback<IHelloWorldService.AsyncClient.sayHello_call> {

	private static final Logger logger = Logger.getLogger(HelloWorldAsyncCallBack.class);
	
	@Override
	public void onComplete(sayHello_call sayHello) {
		try {
			
			logger.info("thrift异步回调方法HelloWorldAsyncCallBack返回：" + sayHello.getResult());
		} catch (TException e) {
			logger.warn("thrift异步回调方法HelloWorldAsyncCallBack返回异常", e);
		}
	}

	@Override
	public void onError(Exception e) {
		logger.error("thrift异步回调方法HelloWorldAsyncCallBack异常", e);
	}


}
