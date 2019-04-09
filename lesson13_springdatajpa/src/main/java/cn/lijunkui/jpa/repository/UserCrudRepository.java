package cn.lijunkui.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import cn.lijunkui.jpa.model.User;

public interface UserCrudRepository extends CrudRepository<User,Long>{

}
