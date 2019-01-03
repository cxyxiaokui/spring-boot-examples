package cn.lijunkui.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags ="用户管理")
@RestController()
@RequestMapping("/user")
public class UserController {
    Logger log = LoggerFactory.getLogger(UserController.class);
    /**
     * 根据用户id 查询用户
     * @return
     */
    @ApiOperation(value="根据id获取用户信息")
    @ApiImplicitParam(paramType= "path", name = "id", value = "用户id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求无效 (Bad request)") })
    public User get(@PathVariable(name = "id") Long id){
        User user = new User();
        user.setName("lijunkui");
        user.setAge(18);
        log.info("springboot查询用户成功："+"id:{}",id);
        return user;
    }
    /**
     * 添加用户
     */
    @ApiOperation(value="添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="用户名",dataType="string", required = true, paramType = "form",example="ljk"),
            @ApiImplicitParam(name="age",value="用户年龄",dataType="int", paramType = "form")})
    @PostMapping()
    public void add(User user){
        log.info("springboot添加用户成功："+"name:{},age:{}",user.getName(),user.getAge());
    }
 
    /**
     * 全部更新
     * @param user
     */
    @PutMapping()
    public void updatePut(User user){
        log.info("springboot Put 修改用户成功："+"name:{},age:{}",user.getName(),user.getAge());
    }
    /**
     * 局部更新
     */
    public void updatePatch(@PathVariable("name") String name){
        log.info("springboot Patch 修改用户成功："+"name:{}",name);
    }
    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        User user = new User();
        user.setName("lijunkui");
        user.setAge(18);
        log.info("springboot 删除用户成功："+"id:{}",id);
    }
 
    /**
     * 根据requestBody 更新用户信息
     * @param user
     * @return
     */
    @PostMapping("/updateUserByRequestBody")
    public void updateUserByRequestBody(@RequestBody User user){
        log.info("updateUserByRequestBody 修改用户成功："+"name:{},age:{}",user.getName(),user.getAge());
    }

}
