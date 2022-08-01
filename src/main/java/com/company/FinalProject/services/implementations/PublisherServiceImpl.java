package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Publisher.PublisherDTO;
import com.company.FinalProject.dto.Publisher.PublisherResponseDTO;
import com.company.FinalProject.entity.Publisher;
import com.company.FinalProject.repo.PublisherRepository;
import com.company.FinalProject.services.PublisherService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepo;

    public PublisherServiceImpl(PublisherRepository publisherRepo) {
        super();
        this.publisherRepo = publisherRepo;
    }

    @Override
    public List<PublisherDTO> getAll() {
        return publisherRepo.findAll().stream().map(Publisher::convertToDto).collect(Collectors.toList());
    }


    @Override
    public PublisherResponseDTO create(PublisherResponseDTO publisherResponseDTO) {
        Publisher publisher=publisherResponseDTO.convertToEntity();
        return publisherRepo.save(publisher).convertToResponseDto();
    }

    @Override
    public void delete(long id) {
        publisherRepo.deleteById(id);
    }

    @Override
    public void update(PublisherDTO publisherDTO, long id) {
        Publisher publisher=publisherDTO.convertToEntity();
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
    public Optional<PublisherDTO> findById(long id) {
        return publisherRepo.findById(id)
                .map(Publisher::convertToDto);
    }

    @Override
    public List<PublisherResponseDTO> getByNameContaining(String name) {
        return publisherRepo.findByNameIsContainingIgnoreCase(name)
                .stream()
                .map(Publisher::convertToResponseDto)
                .collect(Collectors.toList());
    }

}
