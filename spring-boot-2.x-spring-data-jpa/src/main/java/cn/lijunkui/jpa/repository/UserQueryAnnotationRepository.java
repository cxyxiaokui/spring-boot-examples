package cn.lijunkui.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.lijunkui.jpa.model.ResultDTO;
import cn.lijunkui.jpa.model.User;

public interface UserQueryAnnotationRepository extends JpaRepository<User, Long>{
	
	@Query("select u from User u where 1=1 and u.name = ?1")
	public List<User> findUserByNameWithQueryAnnotation(String name);
	@Query("select u from User u where 1=1 and u.name = ?1 and u.age=?2")
	public List<User> findUserByNameAndAgeWithQueryAnnotation(String name,Integer age);
	@Query("select u from User u where 1=1 and u.name like  CONCAT('%',?1,'%') and u.age= ?2")
	public List<User> findUserByLikeNameWithQueryAnnotation(String name,Integer age);
	@Query("select u from User u where 1=1 and u.name like  CONCAT('%',:name,'%') and u.age= :age")
	public List<User> findUserByLikeNameWithQueryAnnotation2(@Param("name")String name,@Param("age")Integer age);
	@Query(value = "select * from user u where u.name like CONCAT('%',:name,'%') and u.age = :age",nativeQuery = true)
	public List<User> findUserByLikeNameAndAgeWithNative(@Param("name")String name,@Param("age")Integer age);
	@Query("select new cn.lijunkui.jpa.model.ResultDTO(u.address,count(u.id))  from User u group by u.address")
	public List<ResultDTO> findCountGroupByAddress();
}
