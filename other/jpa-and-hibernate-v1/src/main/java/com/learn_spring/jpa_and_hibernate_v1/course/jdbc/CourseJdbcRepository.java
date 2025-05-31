package com.learn_spring.jpa_and_hibernate_v1.course.jdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.learn_spring.jpa_and_hibernate_v1.course.Course;

@Repository
public class CourseJdbcRepository {
    private JdbcTemplate jdbcTemplate;

    public CourseJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_QUERY = """
                INSERT INTO COURSE (id, name, author)
                values (?, ?, ?)
            """;

    private static final String SELECT_QUERY = """
            SELECT * FROM COURSE
            WHERE id = ?
            """;

    public void insert(Course course) {
        jdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }

    public Course findById(long id) {
        return jdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), id);
    }
}
