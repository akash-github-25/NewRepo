package com.Neuron.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Neuron.Exception.BookException;
import com.Neuron.Exception.UserException;
import com.Neuron.Repository.BookRepo;
import com.Neuron.Repository.UserRepo;
import com.Neuron.bean.Book;
import com.Neuron.bean.User;

@Service
public class UserServiceImpl implements UserService{
	
	private final int FINE_PER_DAY  = 2;
	
	private final int MAX_DIFF_DAYS = 5;
	
	private final int MAX_BOOKS = 5;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BookRepo bookRepo;

	@Override
	public LocalDate borrowBook(Book book, User user) throws BookException, UserException {

		
		if(!book.isIssued()) {
			
			List<Book> borrowedBook = user.getBorrowedBooks();
		
			if(borrowedBook.size() < MAX_BOOKS  && book.getBorrowingAmount() < user.getSavingAmount()) {
				borrowedBook.add(book);
				user.setSavingAmount(user.getSavingAmount() - book.getBorrowingAmount());
				book.setIssuedDate(LocalDate.now());
				book.setIssued(true);
				bookRepo.save(book);
				userRepo.save(user);
			}
			else throw new UserException("User can't borrow more than five books and make sure you have the savingAmount to buy the book !");		
		}
		else throw new BookException("Book is not present in the library !");
		
		return LocalDate.now().plusDays(MAX_DIFF_DAYS);
	}

	@Override
	public Book returnBook(User user, Book book) throws BookException, UserException {
			
		int days = Math.abs(book.getIssuedDate().compareTo(LocalDate.now()));
		int fine = days*FINE_PER_DAY;
		
		if(days > MAX_DIFF_DAYS) user.setSavingAmount(user.getSavingAmount()-fine);
			
		book.setReturnDate(LocalDate.now());
		book.setIssued(false);
		
		Integer bookId = book.getBookId();
		List<Book> books = user.getBorrowedBooks();
		
	    Book book1 = books.stream().filter((s)->s.getBookId() == bookId).findFirst().get();
	    books.remove(book1);
          	
		bookRepo.save(book);
		userRepo.save(user);
		return book;
	}


	
	@Override
	public User addUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public Book updateBook(Book book) {		
		return bookRepo.save(book);
	}

	@Override
	public String deleteBook(Integer bookId) {	
		 bookRepo.delete(bookRepo.findById(bookId).get());
		 return "Book deleted";
	}

	@Override
	public Book addBook(Book book) throws BookException {
		return bookRepo.save(book);
	}

}
