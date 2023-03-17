package com.example.mvccrud.service;


import com.example.mvccrud.dao.AuthorDao;
import com.example.mvccrud.dao.BookDao;
import com.example.mvccrud.entity.Author;
import com.example.mvccrud.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final AuthorDao authorDao;
    private final BookDao bookDao;

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
    public void saveBook(Book book) {
        Author author=
                authorDao.findById(book.getAuthor().getId()).get();
        author.addBook(book);
        bookDao.save(book);
    }
}
