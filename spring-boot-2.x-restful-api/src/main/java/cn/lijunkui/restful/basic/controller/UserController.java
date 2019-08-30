package cn.lijunkui.restful.basic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lijunkui.restful.basic.controller.model.User;

/**
 * User restful api
 */
@RestController()
@RequestMapping("/user")
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);
    
	/**
     * 根据用户id 查询用户
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable(name = "id") Long id){
    	User user = new User("lijunkui",18);
        log.info("查询用户成功："+"id:{}",id);
        return ResponseEntity.ok(user);
    }
    /**
     * 根据用户id 查询所有用户
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<User>> getAll(){
        
        List<User> userList = new ArrayList<User>(){{
        	add(new User("lijunkui1",18));
        	add(new User("lijunkui2",18));
        	add(new User("lijunkui3",18));
        }};
        return  ResponseEntity.ok(userList);
    }
    /**
     * 添加用户
     */
    @PostMapping("/")
    public ResponseEntity<User> add(@RequestBody User user){
        log.info("添加用户成功："+"name:{},age:{}",user.getName(),user.getAge());
        return  ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    /**
     * 更新用户
     * @param user
     * @return
     */
    @PutMapping("/")
    public  ResponseEntity<Void> updatePut(@RequestBody User user){
        log.info("修改用户成功："+"name:{},age:{}",user.getName(),user.getAge());
        return ResponseEntity.ok().build();
    }
    /**
     * 局部更新
     * @return 
     */
    @PatchMapping("/{name}")
    public ResponseEntity<Void> updatePatch(@PathVariable("name") String name){
        log.info("修改用户成功："+"name:{}",name);
        return ResponseEntity.ok().build();
    }
    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        log.info("删除用户成功："+"id:{}",id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
