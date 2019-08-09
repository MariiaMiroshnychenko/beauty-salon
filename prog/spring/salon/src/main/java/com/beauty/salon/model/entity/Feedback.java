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
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User clientId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User masterId;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Record recordId;

    private String text;

    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
