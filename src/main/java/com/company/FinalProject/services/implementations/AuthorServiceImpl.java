package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.AuthorDTO;
import com.company.FinalProject.providers.AuthorProvider;
import com.company.FinalProject.providers.implementation.AuthorProviderImpl;
import com.company.FinalProject.repo.AuthorRepository;
import com.company.FinalProject.services.AuthorService;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl extends AuthorProviderImpl implements AuthorService {
    private final AuthorRepository authorRepo;

    public AuthorServiceImpl(AuthorRepository authorRepo) {
        super();
        this.authorRepo = authorRepo;
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        return authorRepo.save(authorDTO);
    }

    @Override
    public void deleteAuthor(long id) {
        authorRepo.deleteById(id);
    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDTO) {
        AuthorDTO existingAuthor = null;
        try {
            existingAuthor = authorRepo.findById(authorDTO.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingAuthor.setName(authorDTO.getName());
            existingAuthor.setSurname(authorDTO.getSurname());
            existingAuthor.setPatronymic(authorDTO.getPatronymic());
            existingAuthor.setDateOfBirth(authorDTO.getDateOfBirth());
            existingAuthor.setNumberOfBooks(authorDTO.getNumberOfBooks());
            authorRepo.save(existingAuthor);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
        return existingAuthor;
    }

    @Override
    public Optional<AuthorDTO> findByIdAuthor(long id) {
        return authorRepo.findById(id);
    }

    @Override
    public List<AuthorDTO> getAll() {
        return super.getAll();
    }

    @Override
    public List<AuthorDTO> findByFIO(String name){
        return super.findByFIO(name);
    }
}
