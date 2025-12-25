package Crud.CRUD.Module;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CRUD")
public class Todo {
@GeneratedValue
    @Id

    Long id;
    String name;
    long number;
    String email;
    long salary;
}
