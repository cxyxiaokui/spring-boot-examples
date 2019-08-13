package cn.lijunkui.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.lijunkui.jpa.model.User;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserPagingAndSortingRepositoryTest {
	@Autowired
	private UserPagingAndSortingRepository userPagingAndSortingRepository;
	/**
	 * 单个字段排序
	 */
	@Test
	public void findAllBySort() {
		Iterable<User> findAll = userPagingAndSortingRepository.findAll(new Sort(Sort.Direction.ASC,"age"));
		findAll.forEach((user)->{
			 System.out.println(user.getName()+"#"+user.getAge());
		 });
	}
	/**
	 * 多个字段排序
	 */
	@Test
	public void findAllByManySort() {
		List<Order> orders=new ArrayList<Order>();
		orders.add( new Order(Direction. ASC, "name"));
		orders.add( new Order(Direction. DESC, "age"));
		Iterable<User> findAll = userPagingAndSortingRepository.findAll(new Sort(orders));
		findAll.forEach((user)->{
			 System.out.println(user.getName()+"#"+user.getAge());
		 });
	}
	/**
	 *   分页查询
	 * new PageRequest(页数, 每页的数量)
	 */
	@Test
	public void findAllByPage() {
		//
		Page<User> userPage = userPagingAndSortingRepository.findAll(new PageRequest(1, 2));
		userPage.getNumber();// 页数
		userPage.getContent();// 分页的数据
		userPage.getTotalPages();// 总共的页数
		System.out.println("number:" + userPage.getNumber() + "Countet" + userPage.getContent().size() + "TotalPages"
				+ userPage.getTotalPages());
		Stream<User> stream = userPage.get();
		stream.forEach((user)->{
			System.out.println(user.getId()+"#"+user.getName());
		});
	}
}
