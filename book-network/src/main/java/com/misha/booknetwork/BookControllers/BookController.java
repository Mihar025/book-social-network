package com.misha.booknetwork.BookControllers;

import com.misha.booknetwork.BookRequests.BookRequest;
import com.misha.booknetwork.BookService.BookService;
import com.misha.booknetwork.dto.BookResponse;
import com.misha.booknetwork.dto.PageResponse;
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

        @GetMapping
        public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
                @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                @RequestParam(name = "page", defaultValue = "0", required = false) int size,
                Authentication connectedUser
        ){
            return ResponseEntity.ok(bookService.findAllBooks(page, size, connectedUser));
        }

}
