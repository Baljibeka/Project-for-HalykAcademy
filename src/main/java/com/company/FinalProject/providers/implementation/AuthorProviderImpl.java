package com.company.FinalProject.providers.implementation;

import com.company.FinalProject.dto.AuthorDTO;
import com.company.FinalProject.providers.AuthorProvider;

import java.util.List;

public class AuthorProviderImpl implements AuthorProvider {
    private static List<AuthorDTO> authorList;

    public AuthorProviderImpl(List<AuthorDTO> authorList) {
        this.authorList=authorList;
    }

    @Override
    public List<AuthorDTO> getAll() {
        return this.authorList;
    }
}
