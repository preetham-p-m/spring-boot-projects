package com.learn_spring.todo_application.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String userName;

    @Size(min = 5, message = "Description must have atleast 10 characters")
    @Size(max = 50, message = "Description must not exceed 50 characters")
    private String description;

    private LocalDate targetDate;

    private boolean done;

    @Override
    public String toString() {
        return "Todo [id=" + id + ", userName=" + userName + ", description=" + description + ", targetDate="
                + targetDate + ", done=" + done + "]";
    }

}
