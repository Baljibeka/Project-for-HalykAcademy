package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Author.AuthorDTO;
import com.company.FinalProject.dto.Author.AuthorResponseDTO;
import com.company.FinalProject.entity.Author;
import com.company.FinalProject.repo.AuthorRepository;
import com.company.FinalProject.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepo;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }


    public AuthorResponseDTO create(AuthorResponseDTO authorResponseDTO) {
        Author author = authorResponseDTO.convertToEntity();
        Author authorCreated = authorRepo.save(author);
        return authorCreated.convertToResponseDTO();
    }

    public void delete(long id) {
        authorRepo.deleteById(id);
    }

    public void update(AuthorDTO authorDTO, long id) {
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
        return authorRepo.findById(id).map(Author::convertToDto);
    }

    @Override
    public List<AuthorDTO> findByFIO(String name) {
        return authorRepo.findByName(name).stream().map(Author::convertToDto).collect(Collectors.toList());
    }
    public List<AuthorDTO> getAll(){
        List<Author> authors = authorRepo.findAll();
        return authors.stream()
                .map(Author::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorResponseDTO> getByGenreName(String genreName) {
        return authorRepo.findAllByGenre(genreName).stream().map(Author::convertToResponseDTO).toList();
    }
}




