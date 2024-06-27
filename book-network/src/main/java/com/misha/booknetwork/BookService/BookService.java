package com.misha.booknetwork.BookService;

import com.misha.booknetwork.BookMapper.BookMapper;
import com.misha.booknetwork.BookRepository.BookRepository;
import com.misha.booknetwork.BookRequests.BookRequest;
import com.misha.booknetwork.book.Book;
import com.misha.booknetwork.dto.BookResponse;
import com.misha.booknetwork.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

private final BookRepository bookRepository;
private final BookMapper bookMapper;

    public Integer save(BookRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Book book = bookMapper.toBook(request);
        book.setOwner(user);
        return bookRepository.save(book).getId();
    }

    public BookResponse findById(Integer bookId) {
        return null;
    }
}
