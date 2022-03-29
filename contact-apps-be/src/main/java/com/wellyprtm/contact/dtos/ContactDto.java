package com.wellyprtm.contact.dtos;

import com.wellyprtm.contact.entities.Contact;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ContactDto {

    private String id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String profileImage;
    private Date birthDate;
    private Boolean active;
    private Date createdDate;
    private Date updatedDate;

    public Contact toEntity() {
        Contact contact = new Contact();
        contact.setId(this.id);
        contact.setFullName(this.fullName);
        contact.setPhoneNumber(this.phoneNumber);
        contact.setEmail(this.email);
        if (!ObjectUtils.isEmpty(profileImage)) {
            contact.setProfileImage(ArrayUtils.toObject(this.profileImage.getBytes()));
        }
        contact.setBirthDate(this.birthDate);
        contact.setActive(this.active);

        return contact;
    }

    public static List<Contact> toEntities(List<ContactDto> dtos) {
        if (ObjectUtils.isEmpty(dtos)) {
            return null;
        }

        List<Contact> entities = new ArrayList<>();
        for(ContactDto dto: dtos) {
            entities.add(dto.toEntity());
        }

        return entities;
    }

    public static ContactDto fromEntity(Contact entity) {
        if (ObjectUtils.isEmpty(entity)) return null;

        ContactDto dto = new ContactDto();

        dto.id = entity.getId();
        dto.fullName = entity.getFullName();
        dto.phoneNumber = entity.getPhoneNumber();
        dto.email = entity.getEmail();
        dto.birthDate = entity.getBirthDate();
        if (!ObjectUtils.isEmpty(entity.getProfileImage())) {
            dto.profileImage = new String(ArrayUtils.toPrimitive(entity.getProfileImage()));
        }
        dto.active = entity.getActive();

        return dto;
    }

    public static List<ContactDto> fromEntities(List<Contact> entities) {
        if (ObjectUtils.isEmpty(entities)) return null;

        List<ContactDto> dtos = new ArrayList<>();
        for (Contact entity: entities) {
            dtos.add(ContactDto.fromEntity(entity));
        }

        return dtos;
    }


}
