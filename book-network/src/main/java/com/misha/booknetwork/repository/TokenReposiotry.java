package com.misha.booknetwork.repository;

import com.misha.booknetwork.user.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenReposiotry  extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);

}
