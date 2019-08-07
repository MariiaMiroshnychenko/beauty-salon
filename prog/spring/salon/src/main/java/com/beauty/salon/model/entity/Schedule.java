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
@Table(name = "schedule")
public class Schedule {
    @Id
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User masterId;

    private Boolean sunday;
    private Boolean monday;
    private Boolean tuesday;
    private Boolean wednesday;
    private Boolean thursday;
    private Boolean friday;
    private Boolean saturday;
}
