package com.pmp.spring_elastic.model;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Document(indexName = "post")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String title;
    private String content;
    private String author;

}