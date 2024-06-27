package com.misha.booknetwork.BookMapper;

import com.misha.booknetwork.BookRequests.BookRequest;
import com.misha.booknetwork.book.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public Book toBook(BookRequest request) {
        return Book.builder()
                .id(request.id())
                .title(request.title())
                .authorName(request.authorName())
                .synopsis(request.synopsis())
                .archived(false)
                .shareable(request.shareable())
                .build();
    }
}
