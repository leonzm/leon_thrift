package com.google.thrift.service;

import org.apache.thrift.TException;

import com.google.thrift.service.imp.IHelloWorldService;

public class HelloWorldService implements IHelloWorldService.Iface {

	@Override
	public String sayHello(String username) throws TException {
		return "Hello, " + username + ", Welcome to the Thrift world, you will enjoy it!";
	}

}
