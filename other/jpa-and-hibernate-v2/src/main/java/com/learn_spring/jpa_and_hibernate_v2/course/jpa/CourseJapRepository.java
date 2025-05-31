package com.learn_spring.jpa_and_hibernate_v2.course.jpa;

import org.springframework.stereotype.Repository;

import com.learn_spring.jpa_and_hibernate_v2.course.Course;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CourseJapRepository {

    private EntityManager entityManager;

    public CourseJapRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(Course course) {
        entityManager.merge(course);
    }

    public Course findById(long id) {
        return entityManager.find(Course.class, id);
    }
}
