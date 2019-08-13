package cn.lijunkui.helloworld.springwebflux;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/webflux")
public class HelloWordWebFluxController {
	
	@RequestMapping("/helloworld")
	public Mono<String> helloWord(){
		return Mono.just("hello WebFlux!"); 
	}
}
