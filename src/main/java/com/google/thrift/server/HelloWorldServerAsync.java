package com.google.thrift.server;

import org.apache.log4j.Logger;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

import com.google.thrift.service.HelloWorldServiceAsync;
import com.google.thrift.service.imp.IHelloWorldService;
import com.google.thrift.service.imp.IHelloWorldService.Iface;

public class HelloWorldServerAsync {
	private static final Logger logger = Logger.getLogger(HelloWorldServerAsync.class);
	
	public static final int SERVER_PORT = 8090;
	
	/**
	 * 启动thrift异步服务
	 */
    private void startServer() { 
        try { 
            TNonblockingServerSocket socket = new TNonblockingServerSocket(SERVER_PORT);
            final IHelloWorldService.Processor<Iface> processor = new IHelloWorldService.Processor<Iface>(new HelloWorldServiceAsync()); 
            TNonblockingServer.Args arg = new TNonblockingServer.Args(socket); 
            // 高效率的、密集的二进制编码格式进行数据传输 
            // 使用非阻塞方式，按块的大小进行传输，类似于 Java 中的 NIO 
            arg.protocolFactory(new TCompactProtocol.Factory()); 
            arg.transportFactory(new TFramedTransport.Factory()); 
            arg.processorFactory(new TProcessorFactory(processor)); 
            logger.info("#服务启动-使用:非阻塞&高效二进制编码"); 
            //TServer server = new THsHaServer(arg); 
            TServer server = new TNonblockingServer(arg); 
            server.serve(); 
            
        } catch (Exception e) {
        	logger.error("启动thrift异步服务异常", e);
        }
    }
     
    /**
     * @param args
     */
    public static void main(String[] args) {
    	HelloWorldServerAsync server = new HelloWorldServerAsync();
        server.startServer();
    }

}
