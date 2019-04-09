package cn.lijunkui.jpa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.lijunkui.jpa.model.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserJpaRepositoryTest {
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Test
	public void findAll() {
		List<User> userList = userJpaRepository.findAll();
		Assert.assertTrue(userList.size()>0);
	}
	@Test
	public void findAllBySort() {
		List<User> userList = userJpaRepository.findAll(new Sort(Sort.Direction.ASC, "age"));
		userList.forEach((user)->{
			System.out.println(user.getName()+"#"+user.getAge());
		});
	}
	@Test
	public void findAllByIdByIterable() {
		List<Long> ids = new ArrayList<Long>(){{add(1l);add(2l);}};
		List<User> userList = userJpaRepository.findAllById(ids);
		userList.forEach((user)->{
			System.out.println(user.getName()+"#"+user.getAge());
		});
	}
	
	@Test
	public void saveAll() {
		User user = new User();
		user.setName("bfsu");
		user.setAge(100);
		user.setSex("woman");
		user.setAddress("BEIJING");
		User user2 = new User();
		user2.setName("bfsu");
		user2.setAge(100);
		user2.setSex("woman");
		user2.setAddress("BEIJING");
		List<User> userList = new ArrayList<User>(){{add(user);add(user2);}};
		List<User> userResultList = userJpaRepository.saveAll(userList);
	}
	@Test
	@Transactional//加上这个就不报懒加载 no session 的问题了
	public void getOne() {
		User user = userJpaRepository.getOne(1l);
		System.out.println(user.getId()+""+user.getName()+user.getSex());
		
	}
	@Test
	@Transactional
	public void deleteAllInBatch() {
		userJpaRepository.deleteAllInBatch();
	}
	
	@Test
	@Transactional
	public void deleteInBatch() {
		User user = new User();
		user.setId(1l);
		User user2 = new User();
		user2.setId(2l);
		List<User> userList = new ArrayList<User>(){{add(user);add(user2);}};
		userJpaRepository.deleteInBatch(userList);
	}
	@Test
	public void flush() {
		User user = new User();
		user.setName("bfsu4");
		user.setAge(100);
		user.setSex("woman");
		user.setAddress("BEIJING");
		userJpaRepository.save(user);
		userJpaRepository.flush();
	}
	@Test
	public void saveAndFlush() {
		User user = new User();
		user.setName("bfsu5");
		user.setAge(100);
		user.setSex("woman");
		user.setAddress("BEIJING");
		userJpaRepository.saveAndFlush(user);
	}
	@Test
	public void findAllByExample() {
	    User user = new User();
        user.setName("xiaoliu");
        List<User> list = userJpaRepository.findAll(Example.of(user));
        System.out.println(list.size());
	}
	@Test
	public void findAllByExampleAndSort() {
        User user = new User();
        user.setName("xiaoliu");
        user.setAddress("beij");
        user.setAge(8);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith())//模糊查询匹配开头，即{username}%
                .withMatcher("address" ,ExampleMatcher.GenericPropertyMatchers.contains())//全部模糊查询，即%{address}%
                .withIgnorePaths("id")//忽略字段，即不管id是什么值都不加入查询条件
                .withIgnorePaths("age");//忽略字段，即不管id是什么值都不加入查询条件
        Example<User> example = Example.of(user ,matcher);
        List<User> userList = userJpaRepository.findAll(example,new Sort(Sort.Direction.ASC, "age"));
        Assert.assertTrue(userList.size() > 0);
	}
}
