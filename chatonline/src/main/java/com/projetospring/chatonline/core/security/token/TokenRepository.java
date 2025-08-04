package com.projetospring.chatonline.core.security.token;

import com.projetospring.chatonline.core.security.token.enums.TypeToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String> {

    Optional<TokenEntity> findByTokenInHash(String tokenHash);

    @Query("SELECT COUNT(t) > 0 FROM TokenEntity t WHERE t.tokenInHash = :tokenHash " +
            "AND t.isRevoked = false AND t.expiresAt > :now")
    boolean existsValidToken(@Param("tokenHash") String tokenHash,
                             @Param("now") LocalDateTime now);

    @Modifying
    @Query("UPDATE TokenEntity t SET t.isRevoked = true WHERE t.tokenInHash = :tokenHash")
    int revokeToken(@Param("tokenHash") String tokenHash);



}