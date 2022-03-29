package com.wellyprtm.contact.controllers;


import com.wellyprtm.contact.dtos.ApiResponse;
import com.wellyprtm.contact.dtos.ContactDto;
import com.wellyprtm.contact.entities.Contact;
import com.wellyprtm.contact.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/public/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @CrossOrigin
    @GetMapping("{id}")
    public ApiResponse get(@PathVariable String id) {
        return ApiResponse.data(ContactDto.fromEntity(contactService.findById(id)));
    }

    @CrossOrigin
    @GetMapping
    public ApiResponse get(@RequestParam Map<String, Object> param) {
        long getAll = contactService.findAll(param);
        int total = 0;
        if (!ObjectUtils.isEmpty(getAll)) {
            total = (int) getAll;
        }
        return ApiResponse.data(ContactDto.fromEntities(contactService.findByParameter(param)), total);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<ApiResponse> post(@RequestBody ContactDto contactDto)  {
        try {
            return ResponseEntity.ok(ApiResponse.data(contactService.save(contactDto)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @CrossOrigin
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        contactService.delete(id);
    }
}
