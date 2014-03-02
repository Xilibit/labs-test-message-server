package com.engagepoint.university.messaging.dao.repository.impl;

import com.engagepoint.university.messaging.dao.repository.EmailDAO;
import com.engagepoint.university.messaging.dao.repository.SpringDataEmailDAO;
import com.engagepoint.university.messaging.dto.EmailDTO;
import com.engagepoint.university.messaging.entities.Email;
import com.engagepoint.university.messaging.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("emailDAO")
public class EmailDAOImpl implements EmailDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SpringDataEmailDAO springDataEmailDAO;


    @Override
    @Transactional
    public EmailDTO getById(Long id) {
        Email email = springDataEmailDAO.findOne(id);
        EmailDTO emailDTO = Converter.convert(email);
        return emailDTO;
    }

    @Override
    @Transactional
    public List<EmailDTO> getAll() {
        List<Email> attachments = springDataEmailDAO.findAll();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = attachments.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        return emailDTOs;
    }

    @Override
    @Transactional
    public void save(EmailDTO emailDTO) {
        Email email = Converter.convert(emailDTO);
        springDataEmailDAO.save(email);
    }

    @Override
    @Transactional
    public void update(EmailDTO emailDTO) {
        Email email = Converter.convert(emailDTO);
        springDataEmailDAO.save(email);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        springDataEmailDAO.delete(id.longValue());
    }

    @Override
    @Transactional
    public void delete(EmailDTO emailDTO) {
        Email email = Converter.convert(emailDTO);
        springDataEmailDAO.delete(email);
    }

    @Override
    @Transactional
    public List<EmailDTO> getEmailsBySender(String sender) {
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_ALL_BY_SENDER, Email.class)
                .setParameter("sender", sender).getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        return emailDTOs;
    }

    @Override
    @Transactional
    public List<EmailDTO> getEmailsBySubject(String subject) {
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_ALL_BY_SUBJECT, Email.class)
                .setParameter("subject", subject).getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        return emailDTOs;
    }

    @Override
    @Transactional
    public List<EmailDTO> getEmailsSortByDeliverDate() {
        List<Email> emails = entityManager
                .createNamedQuery(Email.GET_ALL_SORT_BY_DELIVERY_DATE, Email.class).getResultList();
        List<EmailDTO> emailDTOs = new ArrayList<>();
        Iterator<Email> emailIterator = emails.iterator();
        while (emailIterator.hasNext()) {
            emailDTOs.add(Converter.convert(emailIterator.next()));
        }
        return emailDTOs;
    }

    @Override
    @Transactional
    public void deleteIdList(List<Long> idList) {
        if (idList != null) {
            for (Long id : idList) {
                Email email = springDataEmailDAO.findOne(id);
                if (email != null) {
                    springDataEmailDAO.delete(email);
                }
            }
        }
    }
}