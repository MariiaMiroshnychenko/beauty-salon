package com.beauty.salon.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Entity
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User clientId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User masterId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Procedure procedureId;

    @Column(name = "record_date")
    private LocalDate recordDate;

    private LocalTime time;

    @OneToOne(mappedBy = "recordId")
    private Feedback feedback;
}
