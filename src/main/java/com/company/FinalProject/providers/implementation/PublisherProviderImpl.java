package com.company.FinalProject.providers.implementation;

import com.company.FinalProject.dto.PublisherDTO;
import com.company.FinalProject.providers.PublisherProvider;

import java.util.List;

public class PublisherProviderImpl implements PublisherProvider {
    private static List<PublisherDTO> publisherList;

    public PublisherProviderImpl(List<PublisherDTO> publisherList) {
        this.publisherList=publisherList;
    }

    @Override
    public List<PublisherDTO> getAll() {
        return this.publisherList;
    }
}
