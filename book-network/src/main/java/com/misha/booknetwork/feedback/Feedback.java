package com.misha.booknetwork.feedback;

import com.misha.booknetwork.book.Book;
import com.misha.booknetwork.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Feedback extends BaseEntity {


    private Double note; // 1- 5 stars

    private String comment; // feed back about book or whatever

    @ManyToOne
    @JoinColumn(name = "book_id")
        private Book book;

}
