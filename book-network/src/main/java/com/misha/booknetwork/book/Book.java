package com.misha.booknetwork.book;

import com.misha.booknetwork.common.BaseEntity;
import com.misha.booknetwork.feedback.Feedback;
import com.misha.booknetwork.history.BookTransactionHistory;
import com.misha.booknetwork.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Book extends BaseEntity {

    private String title;

    private String authorName;
    private String isbn;
    private String synopsis;

    private String bookCover;
    private boolean archived;

    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;

    @Transient
    public double getRate(){
        if(feedbacks == null || feedbacks.isEmpty()){
            return 0.0;
        }
        var rate = this.feedbacks.stream()
                .mapToDouble(Feedback::getNote)
                .average()
                .orElse(0.0);

        double roundedRate = Math.round( rate * 10.0)/ 10.0;
        return roundedRate;
    }



}
