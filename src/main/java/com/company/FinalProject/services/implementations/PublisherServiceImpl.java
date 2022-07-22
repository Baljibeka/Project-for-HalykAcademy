package com.company.FinalProject.services.implementations;

import com.company.FinalProject.entity.Publisher;
import com.company.FinalProject.repo.PublisherRepository;
import com.company.FinalProject.services.PublisherService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepo;

    public PublisherServiceImpl(PublisherRepository publisherRepo) {
        super();
        this.publisherRepo = publisherRepo;
    }

    @Override
    public List<Publisher> getAll() {
        return publisherRepo.findAll();
    }

    @Override
    public Publisher create(Publisher publisherDTO) {
        return publisherRepo.save(publisherDTO);
    }

    @Override
    public void delete(long id) {
        publisherRepo.deleteById(id);
    }

    @Override
    public void update(Publisher publisher) {
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
    public Optional<Publisher> findById(long id) {
        return publisherRepo.findById(id);
    }

    @Override
    public List<Publisher> getByNameContaining(String name) {
        return publisherRepo.findByNameIsContainingIgnoreCase(name);
    }

}
