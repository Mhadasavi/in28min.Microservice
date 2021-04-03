package com.in28min.testdemo.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	@GetMapping(path="/hello-world")
	public String hi() {
		return "Hello-World";
	}
	@GetMapping(path="/hello-world-bean")
	public HelloBean hiBean() {
		return new HelloBean("Hello World");
	}
	@GetMapping(path="/hello-world/path-variable/{msg}")
	public HelloBean pathVariable(@PathVariable String msg) {
		return new HelloBean(("Hello-world "+ msg));
	}
	
	@GetMapping(path="/hello-world-internationalized")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language",required=false)Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
}
 