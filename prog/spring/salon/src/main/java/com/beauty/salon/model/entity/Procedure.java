package com.beauty.salon.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Entity
@Table(name = "procedure_table")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer code;
    private String name;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language languageId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procedureId")
    private List<Record> records;
}
