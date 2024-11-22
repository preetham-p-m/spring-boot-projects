package com.pmp.restful_web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pmp.restful_web_service.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

}
