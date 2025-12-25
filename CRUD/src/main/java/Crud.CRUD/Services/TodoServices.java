package Crud.CRUD.Services;

import Crud.CRUD.Module.Todo;
import Crud.CRUD.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServices {
    @Autowired
    private TodoRepository Connection;

    public Todo createTodo (Todo todo){
        return Connection.save(todo);
    }

    public Todo ReadTodo(Long id){
        return Connection.findById(id).orElseThrow(()->new RuntimeException("Todo not shown"));
    }
    public List<Todo> allanswer(){
        return Connection.findAll();
    }
    public Page<Todo> pages(int page, int size){
        Pageable pg = PageRequest.of(page,size);
        return Connection.findAll(pg);
    }
    public Todo UpdateTodo(Todo todo){
        return Connection.save(todo);
    }

    public void delete(Long id){
        Connection.delete(ReadTodo(id));
    }
}
