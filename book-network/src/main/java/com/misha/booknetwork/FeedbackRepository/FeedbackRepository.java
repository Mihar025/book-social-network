package com.misha.booknetwork.FeedbackRepository;

import com.misha.booknetwork.feedback.FeedBack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack, Integer> {
    @Query("""
            SELECT feedback
            FROM Feedback feedback
            WHERE feedback.book.id = :bookId
            """)
    Page<FeedBack> findAllByBookId(Integer bookId, Pageable pageable);
}
