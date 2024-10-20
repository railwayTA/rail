package com.example.capstone_hw2.dto;

import com.example.capstone_hw2.entity.TodoItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class TodoItemDTO {

    // 할 일 생성에 사용할 Create 클래스
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Create {
        @NotEmpty(message = "Title cannot be empty")
        private String title;

        @NotEmpty(message = "Description cannot be empty")
        private String description;

        private LocalDate dueDate;

        // Getters and Setters for title, description, and dueDate are automatically provided by Lombok
    }

    // 할 일 조회에 사용할 Read 클래스
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Read {
        private Long id;
        private String title;
        private String description;
        private LocalDate dueDate;
        private boolean isCompleted;

        // Entity -> DTO 변환 생성자
        public Read(TodoItem todoItem) {
            this.id = todoItem.getId();
            this.title = todoItem.getTitle();
            this.description = todoItem.getDescription();
            this.dueDate = todoItem.getDueDate();
            this.isCompleted = todoItem.isCompleted();
        }
    }
}
