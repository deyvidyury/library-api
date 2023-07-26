package dev.yury.libraryapi.api.resource;

import dev.yury.libraryapi.api.dto.LoanDto;
import dev.yury.libraryapi.model.entity.Book;
import dev.yury.libraryapi.model.entity.Loan;
import dev.yury.libraryapi.services.BookService;
import dev.yury.libraryapi.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService service;
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody LoanDto dto){
        Book book =
                bookService.getBookByIsbn(dto.getIsbn())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book not " +
                "found for passed isbn"));
        Loan entity = Loan.builder().book(book).customer(dto.getCustomer()).loanDate(LocalDate.now()).build();

        entity = service.save(entity);
        return entity.getId();
    }
}
