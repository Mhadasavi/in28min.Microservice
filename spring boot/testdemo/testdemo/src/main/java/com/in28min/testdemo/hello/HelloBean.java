package com.in28min.testdemo.hello;
 
public class HelloBean {

	private String message;
	
	public HelloBean(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "HelloBean [message=" + message + "]";
	}
	
}

