package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    private String id;

    private String name;

    public Student(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
