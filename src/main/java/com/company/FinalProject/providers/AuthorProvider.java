package com.company.FinalProject.providers;

import com.company.FinalProject.dto.AuthorDTO;

import java.util.List;

public interface AuthorProvider {
    List<AuthorDTO> getAll();
}
