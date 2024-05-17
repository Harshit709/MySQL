package MySQL.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "department_id")
    private Long id;

    @Column(name = "department_name")
    private String name;

    @JsonIgnore
    @OneToMany()
    @JoinColumn(name = "department_id")
    private List<Employee> employees;

}
