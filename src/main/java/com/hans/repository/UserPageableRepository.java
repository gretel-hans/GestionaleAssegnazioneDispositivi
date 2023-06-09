package com.hans.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hans.model.User;

public interface UserPageableRepository extends PagingAndSortingRepository<User, Long>{

}
