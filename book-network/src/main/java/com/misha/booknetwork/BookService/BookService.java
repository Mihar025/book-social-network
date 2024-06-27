package com.misha.booknetwork.BookService;

import com.misha.booknetwork.BookMapper.BookMapper;
import com.misha.booknetwork.BookRepository.BookRepository;
import com.misha.booknetwork.BookRequests.BookRequest;
import com.misha.booknetwork.book.Book;
import com.misha.booknetwork.dto.BookResponse;
import com.misha.booknetwork.dto.PageResponse;
import com.misha.booknetwork.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return bookRepository.findById(bookId)
                .map(bookMapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("No book founded with id: " + bookId));
    }

    public ResponseEntity<List<PageResponse>> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::toBookResponse)


    }
}
