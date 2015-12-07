package com.joshlong.spring.walkingtour.services.repositories.jpa;

import org.springframework.data.jpa.repository.Query;

import com.joshlong.spring.walkingtour.services.repositories.BaseCustomerRepository;


public interface JpaCustomerRepository
	extends BaseCustomerRepository {


    @Query(nativeQuery = true, value = "select sum(c)  as name_count, name from ( " +
            "    SELECT   count( first_name )  as c , first_name as name FROM CUSTOMER group by first_name  UNION " +
            "    SELECT   count( last_name )  as c, last_name as name FROM CUSTOMER group by last_name " +
            "    )  tabla group by  name   order by  name_count DESC " +
            "  LIMIT 1 ")
    public Object[] findMostFrequentName();

}
