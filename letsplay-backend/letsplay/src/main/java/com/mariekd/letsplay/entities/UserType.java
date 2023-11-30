package com.mariekd.letsplay.entities;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "user_type")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserType {

    @Id
    @Column(updatable = false, nullable = false)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String type;
}
