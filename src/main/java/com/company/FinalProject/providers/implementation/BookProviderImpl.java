package com.company.FinalProject.providers.implementation;

import com.company.FinalProject.dto.BookDTO;
import com.company.FinalProject.providers.BookProvider;

import java.util.List;

public class BookProviderImpl implements BookProvider {
    private static List<BookDTO> bookList;

    public BookProviderImpl(List<BookDTO> bookList) {
        this.bookList=bookList;
    }

    @Override
    public List<BookDTO> getAll() {
        return this.bookList;
    }
}
