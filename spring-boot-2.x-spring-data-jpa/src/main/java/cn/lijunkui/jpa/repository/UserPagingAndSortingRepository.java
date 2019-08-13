package cn.lijunkui.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.lijunkui.jpa.model.User;

public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User,Long>{

}
