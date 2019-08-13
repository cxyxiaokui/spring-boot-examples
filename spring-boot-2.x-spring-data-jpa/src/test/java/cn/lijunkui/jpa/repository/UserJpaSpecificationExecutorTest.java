package cn.lijunkui.jpa.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import cn.lijunkui.jpa.model.User;
import cn.lijunkui.jpa.model.UserParam;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserJpaSpecificationExecutorTest {
	
	@Autowired
	private UserJpaSpecificationExecutor executor;
	@Test
	public void findAll() {
		UserParam user = new UserParam();
		user.setName("xiaoliu");
		user.setAge(18);
		user.setMinage(18);
		user.setMaxage(20);
		user.setAddress("bei");
		Page<User> findAll = executor.findAll((root,query,criteriaBuilder)->{
			List<Predicate> predicates = new ArrayList<Predicate>();
            // name = ?
            if (!StringUtils.isEmpty(user.getName())){
                predicates.add(criteriaBuilder.equal(root.get("name"),user.getName()));
            }
            // address like = %?%
            if (!StringUtils.isEmpty(user.getAddress())){
                predicates.add(criteriaBuilder.like(root.get("address"),"%"+user.getAddress()+"%"));
            }
            // age between ? ?
            if (user.getMinage() != null && user.getMaxage() != null){
                predicates.add(criteriaBuilder.between(root.get("age"),user.getMinage(),user.getMaxage()));
            }
            // age > ?
            if (user.getAge()!=null){
                predicates.add(criteriaBuilder.greaterThan(root.get("age"),18));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
		}, new PageRequest(0, 10));
		List<User> content = findAll.getContent();
		content.forEach((u)->{
			System.out.println(u.getName());;
		});
	}
	
	

}
