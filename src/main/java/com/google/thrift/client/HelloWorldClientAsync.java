package com.google.thrift.client;

import org.apache.log4j.Logger;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import com.google.thrift.service.imp.IHelloWorldService;

public class HelloWorldClientAsync {
	
	private static final Logger logger = Logger.getLogger(HelloWorldClientAsync.class);
	
	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 8090;//Thrift server listening port
	public static final int TIMEOUT = 30000;
	
	/**
	 * thrift客户端异步调用
	 * 
	 * @param userName
	 */
	public void startClient(String userName) {
		logger.info("thrift客户端异步调用");
		
		try {
			
			TAsyncClientManager clientManager = new TAsyncClientManager(); 
            TNonblockingTransport transport = new TNonblockingSocket(SERVER_IP, SERVER_PORT, TIMEOUT); 
            TProtocolFactory protocolFactory = new TCompactProtocol.Factory(); 
            
            IHelloWorldService.AsyncClient asyncClient = new IHelloWorldService.AsyncClient(protocolFactory, clientManager, transport);
            HelloWorldAsyncCallBack callBack = new HelloWorldAsyncCallBack();
            asyncClient.sayHello(userName, callBack);
            
		} catch (Exception e) {
			logger.warn("thrift客户端异步调用异常", e);
		}
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		HelloWorldClientAsync client = new HelloWorldClientAsync();
		client.startClient("Google");
		
		Thread.sleep(12000);
	}

}
