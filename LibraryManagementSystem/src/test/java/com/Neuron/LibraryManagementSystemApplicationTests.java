package com.Neuron;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.Neuron.Exception.BookException;
import com.Neuron.Exception.UserException;
import com.Neuron.Repository.BookRepo;
import com.Neuron.Repository.UserRepo;
import com.Neuron.Service.UserService;
import com.Neuron.bean.Book;
import com.Neuron.bean.User;

@RunWith(SpringRunner.class)
@SpringBootTest
class LibraryManagementSystemApplicationTests {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepo userRepo;
	
	@MockBean
	private BookRepo bookRepo;
	
	@Test
	public void addUserTest() {
		
		List<Book> books = new ArrayList<>();
		User user = new User(20,"akash-ayush",4000,books);
		when(userRepo.save(user)).thenReturn(user);
		try {
			assertEquals(user, userService.addUser(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void borrowBookTest() {		
		
		List<Book> books = new ArrayList<>();
		for(int i=0; i<5; i++) books.add(new Book());
		Book book = new Book();
		User user = new User(20,"akash-ayush",4000,books);
		assertThrows(UserException.class, ()->userService.borrowBook(book,user));		
	}
	
	@Test
	public void returnBookTest() {
		
		List<User> userList = new ArrayList<>();
		Book book = new Book(100,"harryPotter","akMishra",false,300,LocalDate.now(),LocalDate.now(),userList);
		List<Book> books = new ArrayList<>();
		for(int i=0; i<5; i++) books.add(new Book());
		User user = new User(20,"akash-ayush",4000,books);	
		assertThrows(NoSuchElementException.class, ()->userService.returnBook(user,book));
	}

	@Test
	void contextLoads() {
	}

}
