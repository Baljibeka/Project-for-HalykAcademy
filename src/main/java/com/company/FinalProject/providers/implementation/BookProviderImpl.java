package com.company.FinalProject.providers.implementation;

import com.company.FinalProject.dto.BookDTO;
import com.company.FinalProject.providers.BookProvider;

import java.util.List;

public class BookProviderImpl implements BookProvider {
    private static List<BookDTO> bookList;

    public BookProviderImpl(List<BookDTO> bookList) {
        this.bookList=bookList;
    }
    public BookProviderImpl(){}
    @Override
    public List<BookDTO> getAll() {
        return this.bookList;
    }

    @Override
    public List<BookDTO> findByName(String name) {
        List<BookDTO> newList=null;
        for(BookDTO bookDTO :this.bookList){
            if(bookDTO.getTitle().matches("(.*)" + name + "(*.)")){
                newList.add(bookDTO);
            }
        }
        return newList;
    }
}
