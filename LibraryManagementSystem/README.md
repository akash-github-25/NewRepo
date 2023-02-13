
# Library_Management_System

This is a Library Management Application where a user will be able to borrow  books 
and can also return it. If a user is using a certain book then it will be available to the 
other user only after the first user returns it.


## Features

- Add User
- Borrow Book
- Return Book




## Tech Stack

* SPRING BOOT
* SPRING DATA JPA
* SPRING BOOT DEVTOOLS
* MySQL DATABASE

## ASSUMPTIONS

* Fine_Per_Day = Rs 2.
* User should return the book within five days.

## FLOW

* ADD  USERS IN THE DATABASE.
* ADD BOOKS IN THE DATABASE.
* BORROW BOOKS FOR A USER.USER CAN HAVE ATMOST  FIVE BOOKS.
* RETURN BOOK WITHIN FIVE DAYS OTHERWISE GIVE FINE OF RS 2 PER DAY.

## DATABASE DETAILS

*  I have used port 8320 , and the name  of my database is "Library" with three tables namely -
      book , user , user_borrowed_books .

## QUERIES TO INSERT BOOK DATA IN DATABASE

```MySql
insert into  Book(book_id,author,title,borrowing_amount,is_issued,issued_date,return_date) values(1,'premchand','IdGah',120,false,null,null);
insert into  Book(book_id,author,title,borrowing_amount,is_issued,issued_date,return_date) values(2,'jayshankar Prasad','Ansu',100,false,null,null);
insert into  Book(book_id,author,title,borrowing_amount,is_issued,issued_date,return_date) values(3,'Hariwans rai bacchan','Madhusala',120,false,null,null);
insert into  Book(book_id,author,title,borrowing_amount,is_issued,issued_date,return_date) values(4,'APJ','Ignited Minds',124,false,null,null);
insert into  Book(book_id,author,title,borrowing_amount,is_issued,issued_date,return_date) values(5,'Rs Agarwal','Quantative maths',180,false,null,null);
```


## API Reference

#### Borrow Book

```http
  borrowBook/{bookId}/{userId}
```

| Parameter | Type      | Description   |

| :--------   | : ---------  | :--------------    |

| `bookId`  | `Integer` | **Required**. |

| `userId`  | `Integer` | **Required**. |

#### Return Book

```http
  returnBook/{bookId}/{userId}
```

| Parameter | Type      | Description   |

| :--------   | : ---------  | :--------------    |

| `bookId`  | `Integer` | **Required**. |

| `userId`  | `Integer` | **Required**. |

#### borrowBook(bookId, userId)

* It finds the Book associated with bookId and the User associated with user Id and adds that book into the List of Books of User.
* User won't be able to borrow more than five books.

#### returnBook(bookId, userId)

* It finds the book from the List of Books of User and removes it.
* User should return within a span of five days otherwise there is a fine of Rs 2 per day.


