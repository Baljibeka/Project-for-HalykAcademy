package com.company.FinalProject.Providers.Implementation;

import com.company.FinalProject.Entity.Book;
import com.company.FinalProject.Providers.BookProvider;

import java.util.List;

public class BookProviderImpl implements BookProvider {
    private static List<Book> bookList;

    public BookProviderImpl(List<Book> bookList) {
        this.bookList=bookList;
    }

    @Override
    public List<Book> getAll() {
        return this.bookList;
    }
}
