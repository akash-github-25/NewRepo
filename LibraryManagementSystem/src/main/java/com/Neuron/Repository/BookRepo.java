package com.Neuron.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Neuron.bean.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{

}
