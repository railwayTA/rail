package com.example.capstone_hw2.service;

import com.example.capstone_hw2.dto.TodoItemDTO;
import com.example.capstone_hw2.entity.TodoItem;
import com.example.capstone_hw2.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    // 할 일 생성
    public TodoItem createTodoItem(TodoItemDTO.Create dto) {
        TodoItem todoItem = TodoItem.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dueDate(dto.getDueDate())
                .isCompleted(false)
                .build();
        return todoItemRepository.save(todoItem);
    }

    // 모든 할 일 목록 조회
    public List<TodoItem> getAllTodoItems() {
        return todoItemRepository.findAll();
    }

    // 특정 ID로 할 일 조회
    public Optional<TodoItem> getTodoItemById(Long id) {
        return todoItemRepository.findById(id);
    }

    // 할 일 수정
    public TodoItem updateTodoItem(Long id, TodoItemDTO.Create dto) {
        Optional<TodoItem> todoItemOptional = todoItemRepository.findById(id);
        if (todoItemOptional.isPresent()) {
            TodoItem todoItem = todoItemOptional.get();
            todoItem.setTitle(dto.getTitle());
            todoItem.setDescription(dto.getDescription());
            todoItem.setDueDate(dto.getDueDate());
            return todoItemRepository.save(todoItem);
        } else {
            throw new RuntimeException("TodoItem not found with id " + id);
        }
    }

    // 할 일 완료 표시
    public TodoItem markTodoItemAsCompleted(Long id) {
        Optional<TodoItem> todoItemOptional = todoItemRepository.findById(id);
        if (todoItemOptional.isPresent()) {
            TodoItem todoItem = todoItemOptional.get();
            todoItem.setIsCompleted(true);
            return todoItemRepository.save(todoItem);
        } else {
            throw new RuntimeException("TodoItem not found with id " + id);
        }
    }

    // 할 일 삭제
    public void deleteTodoItem(Long id) {
        todoItemRepository.deleteById(id);
    }
}
