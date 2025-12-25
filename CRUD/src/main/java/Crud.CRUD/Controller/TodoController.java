package Crud.CRUD.Controller;

import Crud.CRUD.Module.Todo;
import Crud.CRUD.Services.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api")
public class TodoController {
    @Autowired
    private TodoServices connection;

    @PostMapping
    ResponseEntity<Todo> create(@RequestBody Todo todo){
        return new ResponseEntity<>(connection.createTodo(todo), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    ResponseEntity<Todo> Update(@PathVariable  Long id){
        return new ResponseEntity<>(connection.ReadTodo(id),HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity<List<Todo>> Allanswer(){
        return new ResponseEntity<>(connection.allanswer(),HttpStatus.OK);
    }
    @GetMapping("/path")
    ResponseEntity<Page<Todo>> pahgs(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(connection.pages(page,size),HttpStatus.OK);
    }
    @PutMapping
    ResponseEntity<Todo> Update(@RequestBody Todo todo){
        return new ResponseEntity<>(connection.UpdateTodo(todo),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    void Delete(@PathVariable Long id){
        connection.delete(id);
    }

}
