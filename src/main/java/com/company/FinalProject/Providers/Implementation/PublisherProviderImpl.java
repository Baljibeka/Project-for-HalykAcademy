package com.company.FinalProject.Providers.Implementation;

import com.company.FinalProject.Entity.Publisher;
import com.company.FinalProject.Providers.PublisherProvider;

import java.util.List;

public class PublisherProviderImpl implements PublisherProvider {
    private static List<Publisher> publisherList;

    public PublisherProviderImpl(List<Publisher> publisherList) {
        this.publisherList=publisherList;
    }

    @Override
    public List<Publisher> getAll() {
        return this.publisherList;
    }
}
