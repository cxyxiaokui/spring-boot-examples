package cn.lijunkui.jpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.lijunkui.jpa.model.User;

public interface UserCrudRepository extends CrudRepository<User,Long>{
	long countByName(String name);
	@Transactional
	List<User> reomveByName(String name);
	@Transactional
	List<User> deleteByName(String name);
}
