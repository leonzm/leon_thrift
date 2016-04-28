package com.google.thrift.client;

import org.apache.log4j.Logger;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.google.thrift.service.imp.IHelloWorldService;

public class HelloWorldClient {
	
	private static final Logger logger = Logger.getLogger(HelloWorldClient.class);

	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 8090;//Thrift server listening port
	public static final int TIMEOUT = 30000;

	/**
	 * thrift客户端调用
	 * 
	 * @param userName
	 */
	public void startClient(String userName) {
		logger.info("thrift客户端调用");
		
		TTransport transport = null;
		try {
			transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
			// 协议要和服务端一致
			TProtocol protocol = new TBinaryProtocol(transport);
			// TProtocol protocol = new TCompactProtocol(transport);
			// TProtocol protocol = new TJSONProtocol(transport);
			IHelloWorldService.Client client = new IHelloWorldService.Client(protocol);
			transport.open();
			
			String result = client.sayHello(userName);
			
			logger.info("Thrift client result =: " + result);
		} catch (Exception e) {
			logger.warn("thrift客户端调用异常", e);
		} finally {
			if (transport != null && transport.isOpen()) {
				transport.close();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloWorldClient client = new HelloWorldClient();
		client.startClient("Google");
	}
}
