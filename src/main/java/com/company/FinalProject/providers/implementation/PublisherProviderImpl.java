package com.company.FinalProject.providers.implementation;

import com.company.FinalProject.dto.PublisherDTO;
import com.company.FinalProject.providers.PublisherProvider;

import java.util.List;

public class PublisherProviderImpl implements PublisherProvider {
    private static List<PublisherDTO> publisherList;

    public PublisherProviderImpl(List<PublisherDTO> publisherList) {
        this.publisherList=publisherList;
    }
    public PublisherProviderImpl(){}
    @Override
    public List<PublisherDTO> getAll() {
        return this.publisherList;
    }

    @Override
    public List<PublisherDTO> findByName(String name) {
        List<PublisherDTO> newList=null;
        for(PublisherDTO publisherDTO:this.publisherList){
            if(publisherDTO.getName().matches("(.*)" + name + "(*.)")){
                newList.add(publisherDTO);
            }
        }
        return newList;
    }
}
