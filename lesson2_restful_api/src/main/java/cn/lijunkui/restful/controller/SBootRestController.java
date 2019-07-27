package cn.lijunkui.restful.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lijunkui.restful.model.User;

/**
 * SpringBoot restful api
 */
@RestController()
@RequestMapping("/bootUser")
public class SBootRestController {
	Logger log = LoggerFactory.getLogger(SmvcRestController.class);
    /**
     * 根据用户id 查询用户
     * @return
     */
    @GetMapping("/{id}")
    public User get(@PathVariable(name = "id") Long id){
        User user = new User();
        user.setName("lijunkui");
        user.setAge(18);
        log.info("springboot查询用户成功："+"id:{}",id);
        return user;
    }
    /**
     * 根据用户id 查询用户
     * @return
     */
    @GetMapping()
    public List<User> getAll(){
        User user = new User();
        user.setName("lijunkui");
        user.setAge(18);
        
        User user2 = new User();
        user2.setName("lijunkui");
        user2.setAge(18);
        
        User user3 = new User();
        user3.setName("lijunkui");
        user3.setAge(18);
        
        List<User> userList = new ArrayList<User>();
        userList.add(user);
        userList.add(user2);
        userList.add(user3);
        
        return userList;
    }
    /**
     * 添加用户
     */
    @PostMapping()
    public User add(@Valid User user,BindingResult bindingResult){
    	if(bindingResult.hasErrors()) {
    		String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
    		System.out.println(defaultMessage);
    		return null;
        }
        log.info("springboot添加用户成功："+"name:{},age:{}",user.getName(),user.getAge());
		return user;
    }
    @PutMapping()
    public void updatePut(User user){
        log.info("springboot Put 修改用户成功："+"name:{},age:{}",user.getName(),user.getAge());
    }
    /**
     * 局部更新
     */
    @PatchMapping("/{name}")
    public void updatePatch(@PathVariable("name") String name){
        log.info("springboot Patch 修改用户成功："+"name:{}",name);
    }
    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        log.info("springboot 删除用户成功："+"id:{}",id);
    }
}
