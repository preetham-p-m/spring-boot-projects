package com.pmp.spring_aws.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pmp.spring_aws.model.File;

public interface FileRepository extends JpaRepository<File, UUID> {

}