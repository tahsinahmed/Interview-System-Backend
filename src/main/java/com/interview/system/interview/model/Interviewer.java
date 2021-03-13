package com.interview.system.interview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "interviewer")
@Data
public class Interviewer implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "emplyee_id", nullable = false, unique = true)
    private String employeeId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "position", nullable = false)
    private String position;

    @NotNull
    @Column(name = "exp_period", nullable = false)
    private Double experiencePeriod;

    @ManyToMany(mappedBy = "chosenInterviewer")
    @JsonIgnore
    private List<Interview> interviews;
}
