package com.wellyprtm.contact.repositories;

import com.wellyprtm.contact.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {


    @Query(value = "select c from Contact c" +
            " where (:fullName is null or c.fullName like %:fullName%) " +
            " and (:phoneNumber is null or c.phoneNumber like %:phoneNumber%)" +
            " and (:email is null or c.email like %:email%)" +
            " and c.active = true" +
            " order by c.createdDate desc")
    List<Contact> findByParameter(@Param(value = "fullName") String fullName,
                                  @Param(value = "phoneNumber") String phoneNumber,
                                  @Param(value = "email") String email,
                                  Pageable pageable);

    @Query(value = "select c from Contact c" +
            " where (:fullName is null or c.fullName like %:fullName%) " +
            " and (:phoneNumber is null or c.phoneNumber like %:phoneNumber%)" +
            " and (:email is null or c.email like %:email%)" +
            " and c.active = true" +
            " order by c.createdDate desc")
    Page<Contact> totalData(@Param(value = "fullName") String fullName,
                                  @Param(value = "phoneNumber") String phoneNumber,
                                  @Param(value = "email") String email,
                                  Pageable pageable);

    @Query(value = "select c from Contact c" +
            " where email = :email" +
            " and active = true")
    Contact findByEmailAndIsActive(@Param(value = "email") String email);

    @Modifying
    @Transactional
    @Query(value = "update contact set active = false where id = :id", nativeQuery = true)
    void deleteContact(@Param(value = "id") String id);

    @Query(value = "select c from Contact c where active = true")
    List<Contact> findAllActive();

}
