package com.misha.booknetwork.BookControllers;

import com.misha.booknetwork.BookRequests.BookRequest;
import com.misha.booknetwork.BookService.BookService;
import com.misha.booknetwork.dto.BookResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

        private final BookService bookService;

        @PostMapping
    public ResponseEntity<Integer> saveBook(
            @Valid @RequestBody BookRequest request,
            Authentication connectedUser
        ){
            return ResponseEntity.ok(bookService.save(request, connectedUser));
        }


        @GetMapping("{book-id}")
    public ResponseEntity<BookResponse>findNookById(
            @PathVariable("book-id") Integer bookId
        ){
            return ResponseEntity.ok(bookService.findById(bookId));
        }

}
