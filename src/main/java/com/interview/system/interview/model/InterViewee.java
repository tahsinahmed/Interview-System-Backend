package com.interview.system.interview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "interviewee")
@Data
public class InterViewee implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "interviewee_id", nullable = false, unique = true)
    private String intervieweeId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "versity_name", nullable = false)
    private String versityName;

    @Column(name = "exp_period")
    private Double experiencePeriod;
}
