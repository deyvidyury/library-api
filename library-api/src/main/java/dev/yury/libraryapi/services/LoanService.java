package dev.yury.libraryapi.services;

import dev.yury.libraryapi.model.entity.Loan;

public interface LoanService {
    Loan save(Loan loan);
}
