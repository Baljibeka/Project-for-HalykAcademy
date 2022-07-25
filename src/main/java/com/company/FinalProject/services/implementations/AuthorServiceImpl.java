package com.company.FinalProject.services.implementations;

import com.company.FinalProject.entity.Author;
import com.company.FinalProject.repo.AuthorRepository;
import com.company.FinalProject.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepo;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }


    public Author create(Author author) {
        return authorRepo.save(author);
    }

    public void delete(long id) {
        authorRepo.deleteById(id);
    }

    public void update(Author author) {
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
    public Optional<Author> findById(long id) {
        return authorRepo.findById(id);
    }
 // добавить фио
    @Override
    public List<Author> findByFIO(String name) {
        return authorRepo.findByNameContaining(name);
    }
    public List<Author> getAll(){
        return authorRepo.findAll();
    }
}
