package com.beauty.salon.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Entity
@Table(name = "record_table")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "master_id", nullable = false)
    private User masterId;

    @ManyToOne
    @JoinColumn(name = "procedure_id", nullable = false)
    private Procedure procedureId;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @Column(nullable = false)
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User clientId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recordId")
    private List<Feedback> feedbacks;
 }
