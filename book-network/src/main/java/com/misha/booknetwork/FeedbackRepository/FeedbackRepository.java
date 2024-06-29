package com.misha.booknetwork.FeedbackRepository;

import com.misha.booknetwork.feedback.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack, Integer> {
}
