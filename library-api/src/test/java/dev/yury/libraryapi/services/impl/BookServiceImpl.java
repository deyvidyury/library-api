package dev.yury.libraryapi.services.impl;

import dev.yury.libraryapi.exception.BusinessException;
import dev.yury.libraryapi.model.entity.Book;
import dev.yury.libraryapi.model.repository.BookRepository;
import dev.yury.libraryapi.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        if (repository.existsByIsbn(book.getIsbn())) {
            throw new BusinessException("ISBN já cadastrado.");
        }
        return repository.save(book);
    }
}
