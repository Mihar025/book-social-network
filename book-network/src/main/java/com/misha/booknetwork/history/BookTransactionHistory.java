package com.misha.booknetwork.history;

import com.misha.booknetwork.book.Book;
import com.misha.booknetwork.common.BaseEntity;
import com.misha.booknetwork.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
public class BookTransactionHistory extends BaseEntity {

    private User user;

    private Book book;
    //book relationship

    private boolean returned;
    private boolean returnApproved;



}
