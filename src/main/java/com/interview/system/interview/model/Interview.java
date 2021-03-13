package com.interview.system.interview.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "interview")
@Data
public class Interview implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "interview_type", nullable = false)
    @NotNull
    private String interviewType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "choden_interviewer",
            joinColumns = @JoinColumn(name = "interview_id"),
            inverseJoinColumns = @JoinColumn(name = "interviewer_id")
    )
    private List<Interviewer> chosenInterviewer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interviewee_id")
    private InterViewee interViewee;

    @Column(name = "interview_date", nullable = false)
    @NotNull
    private LocalDate interviewDate;

    @Column(name = "phase", nullable = false)
    @NotNull
    private String phase;

    @Column(name = "score")
    private Double score;

    @Column(name = "result")
    private Boolean result;
}
