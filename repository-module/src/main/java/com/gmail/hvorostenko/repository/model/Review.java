package com.gmail.hvorostenko.repository.model;

import liquibase.pro.packaged.B;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reviews")
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String comment;
    @Column(name = "date_added")
    private Date dateAdded;
    @Column(name = "status_show")
    private Boolean statusShow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Review(String comment, Date dateAdded, Boolean statusShow) {
        this.comment = comment;
        this.dateAdded = dateAdded;
        this.statusShow = statusShow;
    }

    public Review() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id.equals(review.id) && dateAdded.equals(review.dateAdded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateAdded);
    }
}
