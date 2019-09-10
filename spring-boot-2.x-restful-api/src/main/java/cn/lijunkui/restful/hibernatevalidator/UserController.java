package cn.lijunkui.restful.hibernatevalidator;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.lijunkui.restful.hibernatevalidator.model.User;

/**
 * User restful api
 */
@RestController()
@RequestMapping("/user")
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);
    
    /**
     * 添加用户
     */
    @PostMapping("/")
    public ResponseEntity<User> add(@Valid  User user){
        log.info("添加用户成功："+"name:{},age:{}",user.getName(),user.getAge());
        return  ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
   
}
