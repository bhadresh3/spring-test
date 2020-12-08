package com.system27.springtest.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Entity
@Getter
@Setter
@Table(name = "person")
public class Person {
    @Id
    private Integer id;
    @NotNull
    @NotEmpty(message = "The name of person cannot be empty")
    private String name;
}
