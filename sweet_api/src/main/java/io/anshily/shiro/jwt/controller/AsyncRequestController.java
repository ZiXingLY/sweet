package io.anshily.shiro.jwt.controller;

import java.util.concurrent.Callable;

import io.anshily.shiro.jwt.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AsyncRequestController {

	@GetMapping("/async")
	public Callable<UserDto> doAsync(){
		return ()->{
			Thread.sleep(5000);
			return (UserDto)SecurityUtils.getSubject().getPrincipal();
		};
	}
}
