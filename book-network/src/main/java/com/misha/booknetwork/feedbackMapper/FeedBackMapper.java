package com.misha.booknetwork.feedbackMapper;

import com.misha.booknetwork.FeedBackRequests.FeedBackRequest;
import com.misha.booknetwork.FeedBackRequests.FeedBackResponse;
import com.misha.booknetwork.book.Book;
import com.misha.booknetwork.feedback.Feedback;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeedBackMapper {
    public Feedback toFeedback(FeedBackRequest request) {

        return Feedback.builder()
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

    public FeedBackResponse toFeedBackResponse(Feedback feedback, Integer id) {
        return FeedBackResponse.builder()
                .note(feedback.getNote())
                .comment(feedback.getComment())
                .ownFeedback(Objects.equals(feedback.getCreatedBy(), id))
                .build();
    }
}
