package com.example.mvccrud.service;


import com.example.mvccrud.dao.AuthorDao;
import com.example.mvccrud.dao.BookDao;
import com.example.mvccrud.entity.Author;
import com.example.mvccrud.entity.Book;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final AuthorDao authorDao;
    private final BookDao bookDao;

    public Book findBookById(int id){
        return bookDao.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public BookService(AuthorDao authorDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }

    public void saveAuthor(Author author){
        authorDao.save(author);
    }
    public List<Author> listAuthors(){
        return authorDao.findAll();
    }
    public List<Book> listBooks(){
        return bookDao.findAll();
    }
    @Transactional
    public void removeBook(int bookId){
        Book book=bookDao.findById(bookId)
                .get();
        Author author=book.getAuthor();

        author.removeBook(book);

       /* if(bookDao.existsById(bookId)){
            bookDao.deleteById(bookId);
        }
        else{
            throw new EntityNotFoundException(bookId + " Not Found!");
        }*/
    }
    @Transactional
    public void saveBook(Book book) {
        Author author=
                authorDao.findById(book.getAuthor().getId()).get();
        author.addBook(book);
        bookDao.save(book);
    }
    @Transactional
    public void update(Book book) {
        Book existBook=findBookById(book.getId());
        existBook.setAuthor(book.getAuthor());
        existBook.setId(book.getId());
        existBook.setTitle(book.getTitle());
        existBook.setPrice(book.getPrice());
        existBook.setImgUrl(book.getImgUrl());
        existBook.setPublisher(book.getPublisher());
        existBook.setYearPublished(book.getYearPublished());
    }
}
