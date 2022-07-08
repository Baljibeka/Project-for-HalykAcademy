package com.company.FinalProject.Providers.Implementation;

import com.company.FinalProject.Entity.Author;
import com.company.FinalProject.Providers.AuthorProvider;

import java.util.List;

public class AuthorProviderImpl implements AuthorProvider {
    private static List<Author> authorList;

    public AuthorProviderImpl(List<Author> authorList) {
        this.authorList=authorList;
    }

    @Override
    public List<Author> getAll() {
        return this.authorList;
    }
}
