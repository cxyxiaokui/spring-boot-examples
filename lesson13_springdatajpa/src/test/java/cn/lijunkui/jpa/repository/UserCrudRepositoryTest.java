package cn.lijunkui.jpa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.lijunkui.jpa.model.User;
import org.junit.Assert;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserCrudRepositoryTest {
	@Autowired
	private UserCrudRepository userCrudRepository;
	@Test
	public void save(){
		User user = new User();
		user.setName("xiaoliu");
		user.setAge(18);
		user.setSex("man");
		user.setAddress("beijing");
		User resutl = userCrudRepository.save(user);
		Assert.assertNotNull(resutl);
	}
	@Test
	public void  saveAll() {
		List<User> userList = new ArrayList<User>();
		
		User user = new User();
		user.setName("xiaoliu");
		user.setAge(18);
		user.setSex("man");
		user.setAddress("beijing");
		User user2 = new User();
		user2.setName("xiaoliu");
		user2.setAge(18);
		user2.setSex("man");
		user2.setAddress("beijing");
		userList.add(user);
		userList.add(user2);
		userCrudRepository.saveAll(userList);
	}
	@Test
	public void findById() {
		Optional<User> userOptional = userCrudRepository.findById(1l);
		User user = userOptional.get();
		Assert.assertEquals(1l, user.getId().longValue());
	}
	@Test
	public void existsById() {
		boolean existsById = userCrudRepository.existsById(1l);
		Assert.assertTrue(existsById);
	}
	@Test
	public void findAll() {
		 Iterable<User> findAll = userCrudRepository.findAll();
		 findAll.forEach((user)->{
			 System.out.println(user.getName());
		 });
	}
	@Test
	public void findAllById() {
		List<Long> ids = new ArrayList<Long>(){{add(1l);add(2l);}};
		Iterable<User> findAllById = userCrudRepository.findAllById(ids);
		findAllById.forEach((user)->{
			 System.out.println(user.getName());
		 });
	}
	@Test
	public void count() {
		long count = userCrudRepository.count();
		Assert.assertTrue(count > 0);
	}
	@Test
	@Transactional//加上该注解不会吧数据真的删除掉
	public void deleteById() {
		userCrudRepository.deleteById(2l);
	}
	@Test
	@Transactional
	public void delete() {
		User user = new User();
		user.setId(2l);
		userCrudRepository.delete(user);
	}
	@Test
	@Transactional
	public void deleteAllByIterable() {
		User user = new User();
		user.setId(1l);
		User user2 = new User();
		user2.setId(2l);
		List<User> ids = new ArrayList<User>(){{add(user);add(user2);}};
		userCrudRepository.deleteAll(ids);
	}
	@Test
	@Transactional
	public void deleteAll() {
		userCrudRepository.deleteAll();
	}
}
