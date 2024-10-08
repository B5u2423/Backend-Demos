package com.example.todolist.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true   )
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "role")
    private String role = "USER";

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Task> tasks = new HashSet<>();

}
