package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.PublisherDTO;
import com.company.FinalProject.repo.PublisherRepository;
import com.company.FinalProject.services.PublisherService;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepo;

    public PublisherServiceImpl(PublisherRepository publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public PublisherDTO createPublisher(PublisherDTO publisherDTO) {
        return publisherRepo.save(publisherDTO);
    }

    @Override
    public void deletePublisher(long id) {
        publisherRepo.deleteById(id);
    }

    @Override
    public PublisherDTO updatePublisher(PublisherDTO publisherDTO) {
        PublisherDTO existingPublisher = null;
        try {
            existingPublisher = publisherRepo.findById(publisherDTO.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingPublisher.setName(publisherDTO.getName());
            existingPublisher.setBooks(publisherDTO.getBooks());
            publisherRepo.save(existingPublisher);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }
        return existingPublisher;
    }

    @Override
    public Optional<PublisherDTO> findByIdPublisher(long id) {
        return publisherRepo.findById(id);
    }

    @Override
    public List<PublisherDTO> findByName(String name) {
        return ;
    }
}
