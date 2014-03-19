package com.engagepoint.university.messaging.service.repository;

import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.service.GenericService;

import java.util.List;

public interface SmsService extends GenericService<SmsDTO> {
    public List<SmsDTO> getSmsBySender(String sender);
    public void deleteIdList(List<Long> idList);
    public List<SmsDTO> quickSearch(String quickSearchPhrase);
}
