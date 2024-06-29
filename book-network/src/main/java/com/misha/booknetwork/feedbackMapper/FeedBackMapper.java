package com.misha.booknetwork.feedbackMapper;

import com.misha.booknetwork.FeedBackRequests.FeedBackRequest;
import com.misha.booknetwork.book.Book;
import com.misha.booknetwork.feedback.FeedBack;
import org.springframework.stereotype.Service;

@Service
public class FeedBackMapper {
    public FeedBack toFeedback(FeedBackRequest request) {

        return FeedBack.builder()
                .note(request.note())
                .comment(request.comment())
                .book(Book.builder()
                        .id(request.bookId())
                        .archived(false)
                        .shareable(false)
                        .build()
                )

                .build();
    }
}
