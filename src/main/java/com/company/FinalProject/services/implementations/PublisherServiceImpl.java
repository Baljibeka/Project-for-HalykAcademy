package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Publisher.PublisherDTO;
import com.company.FinalProject.dto.Publisher.PublisherResponseDTO;
import com.company.FinalProject.entity.Publisher;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.repo.PublisherRepository;
import com.company.FinalProject.services.PublisherService;
import lombok.val;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepo;
    private final BookRepository bookRepo;

    public PublisherServiceImpl(PublisherRepository publisherRepo, BookRepository bookRepo) {
        this.bookRepo=bookRepo;
        this.publisherRepo = publisherRepo;
    }

    @Override
    public List<PublisherResponseDTO> getAll() {
        return publisherRepo.findAll().stream().map(Publisher::convertToResponseDto).collect(Collectors.toList());
    }


    @Override
    public PublisherResponseDTO create(PublisherDTO publisherDTO) {
        val books=bookRepo.findAllById(publisherDTO.getPublishedBooks());
        Publisher publisher=publisherDTO.convertToEntity(books);
        return publisherRepo.save(publisher).convertToResponseDto();
    }

    @Override
    public void delete(long id) {
        publisherRepo.deleteById(id);
    }

    @Override
    public void update(PublisherDTO publisherDTO) {
        val books=bookRepo.findAllById(publisherDTO.getPublishedBooks());
        Publisher publisher=publisherDTO.convertToEntity(books);
        Publisher existingPublisher = null;
        try {
            existingPublisher = publisherRepo.findById(publisher.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingPublisher.setName(publisher.getName());
            existingPublisher.setPublishedBooksList(publisher.getPublishedBooksList());
            publisherRepo.save(publisher);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<PublisherResponseDTO> findById(long id) {
        return publisherRepo.findById(id)
                .map(Publisher::convertToResponseDto);
    }

    @Override
    public List<PublisherResponseDTO> getByNameContaining(String name) {
        return publisherRepo.findByNameIsContainingIgnoreCase(name)
                .stream()
                .map(Publisher::convertToResponseDto)
                .collect(Collectors.toList());
    }

}
