package com.company.FinalProject.providers;

import com.company.FinalProject.dto.BookDTO;

import java.util.List;

public interface BookProvider {
    List<BookDTO> getAll();
}
