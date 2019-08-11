package com.beauty.salon.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Entity
@Table(name = "feedback_table")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User clientId;

    @ManyToOne
    @JoinColumn(name = "master_id", nullable = false)
    private User masterId;

    @ManyToOne
    @JoinColumn(name = "record_id", nullable = false)
    private Record recordId;

    private String text;

    private LocalDateTime dateTime;
}
