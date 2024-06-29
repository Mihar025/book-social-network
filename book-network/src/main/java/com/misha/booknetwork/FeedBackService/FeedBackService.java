package com.misha.booknetwork.FeedBackService;

import com.misha.booknetwork.BookRepository.BookRepository;
import com.misha.booknetwork.FeedBackRequests.FeedBackRequest;
import com.misha.booknetwork.FeedbackRepository.FeedbackRepository;
import com.misha.booknetwork.book.Book;
import com.misha.booknetwork.exception.OperationNotPermittedException;
import com.misha.booknetwork.feedback.FeedBack;
import com.misha.booknetwork.feedbackMapper.FeedBackMapper;
import com.misha.booknetwork.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedBackService {
private final BookRepository bookRepository;
private final FeedBackMapper feedBackMapper;
private final FeedbackRepository feedbackRepository;

    public Integer save(FeedBackRequest request, Authentication connectedUser) {
        Book book = bookRepository.findById(request.bookId()).orElseThrow(
                () -> new EntityNotFoundException("No book with the ID::" + request.bookId())
        );
        if(!book.isArchived() || !book.isArchived()){
            throw new OperationNotPermittedException("You cannot give a feedback for an archived or not shareable book");
        }
        User user = ((User) connectedUser.getPrincipal());
        if(!Objects.equals(book.getOwner().getId(), user.getId())){
            throw new OperationNotPermittedException("You cannot give a feedback to your own book");
        }
        FeedBack feedBack = feedBackMapper.toFeedback(request);
        return feedbackRepository.save(feedBack).getId();
    }
}
