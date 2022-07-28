package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.AuthorDTO;
import com.company.FinalProject.entity.Author;
import com.company.FinalProject.repo.AuthorRepository;
import com.company.FinalProject.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepo;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }


    public AuthorDTO create(AuthorDTO authorDTO) {
        Author author = authorDTO.convertToEntity();
        Author authorCreated = authorRepo.save(author);
        return authorCreated.convertToDto(true);
    }

    public void delete(long id) {
        authorRepo.deleteById(id);
    }

    public void update(AuthorDTO authorDTO) {
        Author author = authorDTO.convertToEntity();
        Author existingAuthor = null;
        try {
            existingAuthor = authorRepo.findById(author.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingAuthor.setName(author.getName());
            existingAuthor.setSurname(author.getSurname());
            existingAuthor.setPatronymic(author.getPatronymic());
            existingAuthor.setDateOfBirth(author.getDateOfBirth());
            authorRepo.save(author);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<AuthorDTO> findById(long id) {
        return authorRepo.findById(id).map(item->item.convertToDto(true));
    }
 // добавить фио
    @Override
    public List<AuthorDTO> findByFIO(String name) {
        return authorRepo.findByNameContaining(name).stream().map(item->item.convertToDto(true)).collect(Collectors.toList());
    }
    public List<AuthorDTO> getAll(){
        List<Author> authors = authorRepo.findAll();
        return authors.stream()
                .map(item->item.convertToDto(true))
                .collect(Collectors.toList());
    }
}
