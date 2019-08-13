package cn.lijunkui.jpa.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.lijunkui.jpa.model.ResultDTO;
import cn.lijunkui.jpa.model.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserQueryAnnotationRepositoryTest {
	@Autowired
	private UserQueryAnnotationRepository annotationRepository;
	
	@Test
	public void findUserByName() {
		List<User> findUserByName = annotationRepository.findUserByNameWithQueryAnnotation("xiaoliu");
		findUserByName.forEach((user)->{
			System.out.println(user.getName()+user.getAge());
		});
	}
	
	@Test
	public void findUserByNameAndAgeWithQuery() {
		List<User> findUserByNameAndAgeWithQuery = annotationRepository.findUserByNameAndAgeWithQueryAnnotation("xiaoliu", 18);
		findUserByNameAndAgeWithQuery.forEach((user)->{
			System.out.println(user.getName()+user.getAge());
		});
	}
	@Test
	public void findUserByLikeNameWithQueryAnnotation() {
		List<User> findUserByLikeNameWithQueryAnnotation = annotationRepository.findUserByLikeNameWithQueryAnnotation("xiao", 18);
		findUserByLikeNameWithQueryAnnotation.forEach((user)->{
			System.out.println(user.getName()+user.getAge());
		});
	}
	
	@Test
	public void findUserByLikeNameWithQueryAnnotation2() {
		List<User> findUserByLikeNameWithQueryAnnotation2 = annotationRepository.findUserByLikeNameWithQueryAnnotation2("xiao", 18);
		findUserByLikeNameWithQueryAnnotation2.forEach((user)->{
			System.out.println(user.getName()+user.getAge());
		});
	}
	@Test
	public void findUserByLikeNameAndAgeWithNative() {
		List<User> findUserByLikeNameAndAgeWithNative = annotationRepository.findUserByLikeNameAndAgeWithNative("xiao", 18);
		findUserByLikeNameAndAgeWithNative.forEach((user)->{
			System.out.println(user.getName()+user.getAge());
		});
	}
	@Test
	public void findCountGroupByAddress() {
		List<ResultDTO> findCountGroupByAddress = annotationRepository.findCountGroupByAddress();
		findCountGroupByAddress.forEach((resultDTO)->{
			System.out.println(resultDTO.getAddress()+"#"+resultDTO.getCount());
		});
	}
}
