package cn.lijunkui.freemark;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ftlDemo")
public class FtlController {
	@RequestMapping("/test")
    public String test(){
        return "test/freemarkerDemo";
    }
}
