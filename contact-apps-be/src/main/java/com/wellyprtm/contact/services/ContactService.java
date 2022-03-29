package com.wellyprtm.contact.services;

import com.wellyprtm.contact.dtos.ContactDto;
import com.wellyprtm.contact.entities.Contact;
import com.wellyprtm.contact.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact findById(String id) {
        return contactRepository.findById(id).orElse(null);
    }

    public List<Contact> findByParameter(Map<String, Object> param) {
        String fullName = !ObjectUtils.isEmpty(param.get("fullName")) ? ((String) param.get("fullName")).toLowerCase() : null;
        String phoneNumber = !ObjectUtils.isEmpty(param.get("phoneNumber")) ? ((String) param.get("phoneNumber")).toLowerCase() : null;
        String email = !ObjectUtils.isEmpty(param.get("email")) ?  ((String) param.get("email")).toLowerCase() : null;
        int page = Integer.parseInt(param.get("page").toString());
        int pageSize = Integer.parseInt(param.get("pageSize").toString());
        List<Contact> contacts = contactRepository.findByParameter(fullName, phoneNumber, email, PageRequest.of(page,pageSize));
        return contacts;
    }

    public ContactDto save(ContactDto contactDto) throws Exception {
        if (!ObjectUtils.isEmpty(contactDto.getEmail())) {
            Contact contact = contactRepository.findByEmailAndIsActive(contactDto.getEmail());

            if (!ObjectUtils.isEmpty(contact)) {
                throw new Exception("Email Has Been Registered");
            }
        }
        Contact contact = contactRepository.save(contactDto.toEntity());
        return ContactDto.fromEntity(contact);
    }

    public void delete(String id) {
        contactRepository.deleteContact(id);
    }

    public long findAll(Map<String, Object> param) {
        String fullName = !ObjectUtils.isEmpty(param.get("fullName")) ? ((String) param.get("fullName")).toLowerCase() : null;
        String phoneNumber = !ObjectUtils.isEmpty(param.get("phoneNumber")) ? ((String) param.get("phoneNumber")).toLowerCase() : null;
        String email = !ObjectUtils.isEmpty(param.get("email")) ?  ((String) param.get("email")).toLowerCase() : null;
        int page = Integer.parseInt(param.get("page").toString());
        int pageSize = Integer.parseInt(param.get("pageSize").toString());
        Page<Contact> contacts = contactRepository.totalData(fullName, phoneNumber, email, PageRequest.of(page,pageSize));
        return contacts.getTotalElements();
    }

}
