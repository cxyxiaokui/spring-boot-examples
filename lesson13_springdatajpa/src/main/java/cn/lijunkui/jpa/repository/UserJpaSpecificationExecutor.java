package cn.lijunkui.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.lijunkui.jpa.model.User;

public interface UserJpaSpecificationExecutor extends JpaRepository<User, Long>,JpaSpecificationExecutor<User>{

}
