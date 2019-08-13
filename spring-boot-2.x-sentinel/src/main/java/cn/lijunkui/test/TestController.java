package cn.lijunkui.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.csp.sentinel.annotation.SentinelResource;

@RestController
public class TestController {
 	@GetMapping(value = "/hello")
    @SentinelResource("hello")
    public String hello() {
        return "Hello Sentinel";
    }
}
