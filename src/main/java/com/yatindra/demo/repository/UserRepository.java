package com.yatindra.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yatindra.demo.dto.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long>{

}
