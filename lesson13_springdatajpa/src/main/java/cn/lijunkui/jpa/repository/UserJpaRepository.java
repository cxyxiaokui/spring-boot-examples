package cn.lijunkui.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.lijunkui.jpa.model.User;

public interface UserJpaRepository extends JpaRepository<User,Long>{
}
