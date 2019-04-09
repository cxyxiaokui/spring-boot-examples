package cn.lijunkui.jpa.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.lijunkui.jpa.model.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMethodNameQueryRepositroyTest {
	@Autowired
	private UserMethodNameQueryRepositroy methodNameQueryRepositroy;
	@Test
	public void findByNameAndAge() {
		List<User> findByNameAndAge = methodNameQueryRepositroy.findByNameAndAge("xiaoliu", 18);
		findByNameAndAge.forEach((user)->{
			System.out.println(user.getName()+user.getAge());
		});
	}
	@Test
	public void findByNameOrAge() {
		List<User> findByNameOrAge = methodNameQueryRepositroy.findByNameOrAge("xiaoliu", 18);
		findByNameOrAge.forEach((user)->{
			System.out.println(user.getName()+user.getAge());
		});
	}
}
