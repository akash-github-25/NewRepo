package com.Neuron.Service;

import java.time.LocalDate;

import com.Neuron.Exception.BookException;
import com.Neuron.Exception.UserException;
import com.Neuron.bean.Book;
import com.Neuron.bean.User;

public interface UserService {
	
	public LocalDate borrowBook(Book book,User user)throws BookException,UserException;
	public Book returnBook(User user,Book book)throws BookException,UserException;
	public User addUser(User user);
	public Book updateBook(Book book);
	public String deleteBook(Integer bookId);
	public Book addBook(Book book)throws BookException;

}
