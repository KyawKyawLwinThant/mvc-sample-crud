package com.example.mvccrud.service;


import com.example.mvccrud.dao.AuthorDao;
import com.example.mvccrud.dao.BookDao;
import com.example.mvccrud.ds.Cart;
import com.example.mvccrud.ds.CartItem;
import com.example.mvccrud.entity.Author;
import com.example.mvccrud.entity.Book;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final Cart cart;

    public void clearCart(){
        cart.clearCart();
    }

    public Book findBookById(int id){
        return bookDao.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
    public Set<CartItem> removeFromCart(int id){
        Set<CartItem> cartItems=getCartItems()
                .stream()
                .filter(i -> i.getId()!=id)
                .collect(Collectors.toSet());
                cart.setCartItems(cartItems);
                return cartItems;
    }

    public BookService(AuthorDao authorDao, BookDao bookDao, Cart cart) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.cart = cart;
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
    public Set<CartItem> getCartItems(){
        return cart.getCartItems();
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

    public void updateAgain(Book updateBook) {
        bookDao.saveAndFlush(updateBook);
    }
    public int cartSize(){
        return cart.cartSize();
    }



    public void addToCart(int id) {
        Book book=findBookById(id);
        cart.addToCart(new CartItem(
                book.getId(),
                book.getTitle(),
                book.getPrice(),
                1
        ));
    }



}
