package com.company.FinalProject.providers.implementation;

import com.company.FinalProject.dto.AuthorDTO;
import com.company.FinalProject.providers.AuthorProvider;

import java.util.List;

public class AuthorProviderImpl implements AuthorProvider {
    private static List<AuthorDTO> authorList;

    public AuthorProviderImpl(List<AuthorDTO> authorList) {
        this.authorList=authorList;
    }
    public AuthorProviderImpl(){}
    @Override
    public List<AuthorDTO> getAll() {
        return this.authorList;
    }

    @Override
    public List<AuthorDTO> findByFIO(String name) {
        List<AuthorDTO> newList=null;
        for(AuthorDTO authorDTO:this.authorList){
            if(authorDTO.getName().matches("(.*)"+name + "(*.)") ||
                    authorDTO.getSurname().matches("(.*)"+name + "(*.)") ||
                    authorDTO.getPatronymic().matches("(.*)"+name + "(*.)")){
                newList.add(authorDTO);
            }
        }
        return newList;
    }
}
