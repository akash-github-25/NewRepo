package com.Neuron.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Neuron.bean.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
