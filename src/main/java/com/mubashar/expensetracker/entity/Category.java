package com.mubashar.expensetracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_Name", nullable = false)
    private String name;

    @Column(name = "user_id",nullable = false)
    private Long userId;






}


