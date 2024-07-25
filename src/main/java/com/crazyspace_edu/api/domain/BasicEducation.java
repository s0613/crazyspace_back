package com.crazyspace_edu.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BasicEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String edu_title;

    @Column(nullable = false)
    private String edu_text;

    @Column(nullable = false)
    private int edu_progress;
}
