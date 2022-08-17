package com.company.FinalProject.services.implementations;

import com.company.FinalProject.dto.Publisher.PublisherDTO;
import com.company.FinalProject.dto.Publisher.PublisherShortDTO;
import com.company.FinalProject.entity.Publisher;
import com.company.FinalProject.exception.NotFoundException;
import com.company.FinalProject.repo.BookRepository;
import com.company.FinalProject.repo.PublisherRepository;
import com.company.FinalProject.services.PublisherService;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<PublisherShortDTO> getAll() {
        return publisherRepo.findAll().stream().map(Publisher::convertToResponseDto).collect(Collectors.toList());
    }


    @Override
    public PublisherShortDTO create(PublisherShortDTO publisherShortDTO) {
        Publisher publisher = publisherShortDTO.convertToEntity();
        publisher.setIsBlocked(false);
        return publisherRepo.save(publisher).convertToResponseDto();
    }

    @Override
    public void delete(long id) {
        Publisher publisher =  publisherRepo.findById(id).orElseThrow(()->new NotFoundException("There is no such publisher"));
      publisher.setIsBlocked(true);
        publisherRepo.save(publisher);
    }

    @Override
    public void update(PublisherDTO publisherDTO) {
        val books = bookRepo.findAllById(publisherDTO.getPublishedBooks());
        Publisher publisher =publisherDTO.convertToEntity(books);
        publisher.setIsBlocked(publisherRepo.findById(publisher.getId()).orElseThrow(()->new NotFoundException("There is no such publisher")).getIsBlocked());
        publisherRepo.save(publisher);
    }

    @Override
    public PublisherShortDTO findById(long id) {
        Publisher publisher = publisherRepo.findById(id).orElseThrow(()->new NotFoundException("There is no such publisher"));
        if(publisher.getIsBlocked()) throw new NotFoundException("No publisher");
        return publisher.convertToResponseDto();
    }

    @Override
    public List<PublisherShortDTO> getByNameContaining(String name) {
        return publisherRepo.findByNameIsContainingIgnoreCase(name)
                .stream()
                .map(Publisher::convertToResponseDto)
                .collect(Collectors.toList());
    }

}
