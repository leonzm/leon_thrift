package com.google.thrift.server;

import org.apache.log4j.Logger;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import com.google.thrift.service.HelloWorldService;
import com.google.thrift.service.imp.IHelloWorldService;

public class HelloWorldServer {
	private static final Logger logger = Logger.getLogger(HelloWorldServer.class);
	
    public static final int SERVER_PORT = 8090;

    /**
     * 启动thrift服务
     */
    public void startServer() {
        try {
            logger.info("thrift服务启动");

            TProcessor tprocessor = new IHelloWorldService.Processor<IHelloWorldService.Iface>(new HelloWorldService());
            // 简单的单线程服务模型，一般用于测试
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            // tArgs.protocolFactory(new TCompactProtocol.Factory());
            // tArgs.protocolFactory(new TJSONProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();

        } catch (Exception e) {
            logger.error("thrift服务启动异常", e);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HelloWorldServer server = new HelloWorldServer();
        server.startServer();
    }

}
