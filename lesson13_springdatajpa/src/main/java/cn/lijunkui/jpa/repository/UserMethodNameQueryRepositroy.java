package cn.lijunkui.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.lijunkui.jpa.model.User;

public interface UserMethodNameQueryRepositroy extends JpaRepository<User,Long>{
	/**
	 * where name=?1 and age=?2
	 * @param name
	 * @param age
	 * @return
	 */
	public List<User> findByNameAndAge(String name,Integer age);
	/**
	 * where name=?1 or age=?2
	 * @param name
	 * @param age
	 * @return
	 */
	public List<User> findByNameOrAge(String name,Integer age);
	
}
