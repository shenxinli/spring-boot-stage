package io.ymq.elasticsearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.ymq.elasticsearch.model.Book;
import io.ymq.elasticsearch.repository.BookRepository;

/**
 * 描述:
 *
 * @author yanpenglei
 * @create 2017-11-02 14:49
 **/
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Book findOne(String id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> findByAuthor(String author, PageRequest pageRequest) {
        return bookRepository.findByAuthor(author, pageRequest);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

	@Override
	public Page<Book> findByTitle(String title, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return bookRepository.findByTitle(title, pageRequest);
	}

}
