package com.misha.booknetwork.BookMapper;

import com.misha.booknetwork.BookRequests.BookRequest;
import com.misha.booknetwork.book.Book;
import com.misha.booknetwork.dto.BookResponse;
import com.misha.booknetwork.dto.BorrowedBookResponse;
import com.misha.booknetwork.files.FileUtils;
import com.misha.booknetwork.history.BookTransactionHistory;

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

    public BookResponse toBookResponse(Book book) {

        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .owner(book.getOwner().fullName())
                .cover(FileUtils.readFileFromLocation(book.getBookCover()))

                .rate(book.getRate())
                .archived(book.isArchived())
                .shareable(book.isShareable())
                .build();

    }

    public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory bookTransactionHistory) {
        return BorrowedBookResponse.builder()
                .id(bookTransactionHistory.getBook().getId())
                .title(bookTransactionHistory.getBook().getTitle())
                .authorName(bookTransactionHistory.getBook().getAuthorName())
                .isbn(bookTransactionHistory.getBook().getIsbn())
                .rate(bookTransactionHistory.getBook().getRate())
                .returned(bookTransactionHistory.isReturned())
                .returnApprove(bookTransactionHistory.isReturnApproved())
                .build();
    }

}
