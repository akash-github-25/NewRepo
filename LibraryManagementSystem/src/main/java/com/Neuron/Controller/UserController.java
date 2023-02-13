package com.Neuron.Controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Neuron.Exception.BookException;
import com.Neuron.Exception.UserException;
import com.Neuron.Repository.BookRepo;
import com.Neuron.Repository.UserRepo;
import com.Neuron.Service.UserService;
import com.Neuron.bean.Book;
import com.Neuron.bean.User;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BookRepo bookRepo;
	

	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.addUser(user), HttpStatus.OK);
	}
	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book book) throws BookException{
		return new ResponseEntity<Book>(userService.addBook(book), HttpStatus.OK);
	}
	@DeleteMapping("/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable("bookId") Integer bookId){
		return new ResponseEntity<String>(userService.deleteBook(bookId),HttpStatus.OK);
	}
	@PutMapping("/updateBook")
	public ResponseEntity<Book> updateBook(@RequestBody Book book){
		return new ResponseEntity<Book>(userService.updateBook(book),HttpStatus.OK);
	}
   @PostMapping("/borrowBook/{bookId}/{userId}")
	public ResponseEntity<LocalDate> borrowBook(@PathVariable("bookId") Integer bookId,@PathVariable("userId") Integer userId) throws BookException, UserException {
	   Optional<Book> bookOpt = bookRepo.findById(bookId);
	   if(bookOpt.isEmpty()) 	throw new BookException("Provide proper BookId");
	   Book book = bookOpt.get();
	   User user = userRepo.findById(userId).get();
	   return new ResponseEntity<LocalDate>(userService.borrowBook(book,user), HttpStatus.OK);
   }
   
   @PostMapping("/returnBook/{bookId}/{userId}")
   public ResponseEntity<Book> returnBook(@PathVariable("bookId") Integer bookId,@PathVariable("userId") Integer userId) throws BookException, UserException {   
	   Optional<Book> bookOpt = bookRepo.findById(bookId);
	   if(bookOpt.isEmpty()) 	throw new BookException("Provide proper BookId");
	   Book book = bookOpt.get();
	   User user = userRepo.findById(userId).get();
	   return new ResponseEntity<Book>(userService.returnBook(user, book), HttpStatus.OK);	  
   }
	
}
