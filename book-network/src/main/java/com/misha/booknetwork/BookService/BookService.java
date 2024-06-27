package com.misha.booknetwork.BookService;

import com.misha.booknetwork.BookRepository.BookRepository;
import com.misha.booknetwork.BookRequests.BookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

private final BookRepository bookRepository;

    public Integer save(BookRequest request, Authentication connectedUser) {
        return
    }
}
