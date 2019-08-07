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
@Table(name = "procedure")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer code;
    private String name;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Language languageId;

    @OneToMany(mappedBy = "procedureId")
    private List<Record> records;
}
