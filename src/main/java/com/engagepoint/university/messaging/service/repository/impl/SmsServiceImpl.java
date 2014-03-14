package com.engagepoint.university.messaging.service.repository.impl;

import com.engagepoint.university.messaging.dao.repository.SmsDAO;
import com.engagepoint.university.messaging.dto.SmsDTO;
import com.engagepoint.university.messaging.entity.Sms;
import com.engagepoint.university.messaging.service.repository.SmsService;
import com.engagepoint.university.messaging.util.Converter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("smsService")
public class SmsServiceImpl implements SmsService {

    @Inject
    private SmsDAO smsDAO;


    @Override
    public List<SmsDTO> getSmsBySender(String sender) {
        List<Sms> smses = smsDAO.getSmsBySender(sender);
        List<SmsDTO> smsDTOs = new ArrayList<>();
        Iterator<Sms> smsIterator = smses.iterator();
        while (smsIterator.hasNext()) {
            smsDTOs.add(Converter.convert(smsIterator.next()));
        }
        return smsDTOs;
    }

    @Override
    public void deleteIdList(List<Long> idList) {
       smsDAO.deleteIdList(idList);
    }

    @Override
    public void smsQuickSearch() {
        smsDAO.smsQuickSearch();
    }

    @Override
    public List<SmsDTO> getSmsAllByQuery() {
        List<Sms> smses = smsDAO.getSmsAllByQuery();
        List<SmsDTO> smsDTOs = new ArrayList<>();
        Iterator<Sms> smsIterator = smses.iterator();
        while (smsIterator.hasNext()) {
            smsDTOs.add(Converter.convert(smsIterator.next()));
        }
        return smsDTOs;
    }

    @Override
    public SmsDTO getById(Long id) {
        Sms sms = smsDAO.getById(id);
        SmsDTO smsDTO = Converter.convert(sms);
        return smsDTO;
    }

    @Override
    public List<SmsDTO> getAll() {
        List<Sms> smses = smsDAO.getAll();
        List<SmsDTO> smsDTOs = new ArrayList<>();
        Iterator<Sms> smsIterator = smses.iterator();
        while (smsIterator.hasNext()) {
            smsDTOs.add(Converter.convert(smsIterator.next()));
        }
        return smsDTOs;
    }

    @Override
    public void save(SmsDTO smsDTO) {
        Sms sms = Converter.convert(smsDTO);
        smsDAO.save(sms);
    }

    @Override
    public void update(SmsDTO smsDTO) {
        Sms sms = Converter.convert(smsDTO);
        smsDAO.save(sms);
    }

    @Override
    public void delete(Long id) {
        smsDAO.delete(id);
    }

    @Override
    public void delete(SmsDTO smsDTO) {
        Sms sms = Converter.convert(smsDTO);
        smsDAO.delete(sms);
    }
}