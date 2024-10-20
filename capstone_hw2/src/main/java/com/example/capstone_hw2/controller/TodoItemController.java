package com.example.capstone_hw2.controller;

import com.example.capstone_hw2.dto.TodoItemDTO;
import com.example.capstone_hw2.entity.TodoItem;
import com.example.capstone_hw2.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoItemController {

    @Autowired
    private TodoItemService todoItemService;

    // 모든 할 일 조회
    @GetMapping
    public ResponseEntity<List<TodoItem>> getAllTodoItems() {
        List<TodoItem> todoItems = todoItemService.getAllTodoItems();
        return ResponseEntity.ok(todoItems);
    }

    // 특정 할 일 조회
    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoItemById(@PathVariable Long id) {
        Optional<TodoItem> todoItem = todoItemService.getTodoItemById(id);
        return todoItem.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 할 일 생성
    @PostMapping
    public ResponseEntity<TodoItem> createTodoItem(@RequestBody TodoItemDTO.Create todoItemDto) {
        TodoItem todoItem = todoItemService.createTodoItem(todoItemDto);
        return ResponseEntity.ok(todoItem);
    }

    // 할 일 수정
    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateTodoItem(
            @PathVariable Long id,
            @RequestBody TodoItemDTO.Create todoItemDto) {
        TodoItem updatedTodoItem = todoItemService.updateTodoItem(id, todoItemDto);
        return ResponseEntity.ok(updatedTodoItem);
    }

    // 할 일 완료 표시
    @PutMapping("/{id}/complete")
    public ResponseEntity<TodoItem> markTodoItemAsCompleted(@PathVariable Long id) {
        TodoItem completedTodoItem = todoItemService.markTodoItemAsCompleted(id);
        return ResponseEntity.ok(completedTodoItem);
    }

    // 할 일 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable Long id) {
        todoItemService.deleteTodoItem(id);
        return ResponseEntity.noContent().build();
    }
}

/*
* CRUD, 날씨 api -> map api  날씨 api
*
*
*  j쿼리 사용해서 캘린더 만들기, 부트스트랩 사용 후 클론해보기
*
*
*
* */