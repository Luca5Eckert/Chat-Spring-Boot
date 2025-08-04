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

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String> {

    Optional<TokenEntity> findByTokenInHash(String tokenHash);

    @Query("SELECT t FROM TokenEntity t WHERE t.user.id = :userId " +
            "AND t.isRevoked = false AND t.expiresAt > :now")
    List<TokenEntity> findValidTokensByUserId(@Param("userId") Long userId,
                                              @Param("now") LocalDateTime now);


    @Query("SELECT t FROM TokenEntity t WHERE t.user.id = :userId " +
            "AND t.typeToken = :typeToken AND t.isRevoked = false AND t.expiresAt > :now")
    List<TokenEntity> findValidTokensByUserIdAndType(@Param("userId") Long userId,
                                                     @Param("typeToken") TypeToken typeToken,
                                                     @Param("now") LocalDateTime now);

    @Query("SELECT COUNT(t) > 0 FROM TokenEntity t WHERE t.tokenInHash = :tokenHash " +
            "AND t.isRevoked = false AND t.expiresAt > :now")
    boolean existsValidToken(@Param("tokenHash") String tokenHash,
                             @Param("now") LocalDateTime now);

    @Modifying
    @Query("UPDATE TokenEntity t SET t.isRevoked = true WHERE t.tokenInHash = :tokenHash")
    int revokeToken(@Param("tokenHash") String tokenHash);

    @Modifying
    @Query("UPDATE TokenEntity t SET t.isRevoked = true WHERE t.user.id = :userId")
    int revokeAllUserTokens(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE TokenEntity t SET t.isRevoked = true WHERE t.user.id = :userId " +
            "AND t.typeToken = :typeToken")
    int revokeUserTokensByType(@Param("userId") Long userId,
                               @Param("typeToken") TypeToken typeToken);


    @Modifying
    @Query("DELETE FROM TokenEntity t WHERE t.expiresAt < :now")
    int deleteExpiredTokens(@Param("now") LocalDateTime now);

    @Modifying
    @Query("DELETE FROM TokenEntity t WHERE t.isRevoked = true " +
            "AND t.createdAt < :cutoffDate")
    int deleteOldRevokedTokens(@Param("cutoffDate") LocalDateTime cutoffDate);

    @Query("SELECT t FROM TokenEntity t WHERE t.expiresAt BETWEEN :now AND :soonTime " +
            "AND t.isRevoked = false")
    List<TokenEntity> findTokensExpiringSoon(@Param("now") LocalDateTime now,
                                             @Param("soonTime") LocalDateTime soonTime);


    @Query("SELECT COUNT(t) FROM TokenEntity t WHERE t.user.id = :userId " +
            "AND t.isRevoked = false AND t.expiresAt > :now")
    long countActiveTokensByUser(@Param("userId") Long userId,
                                 @Param("now") LocalDateTime now);


    List<TokenEntity> findByTypeToken(TypeToken typeToken);

    List<TokenEntity> findByIsRevokedTrue();

    List<TokenEntity> findByCreatedAtAfter(LocalDateTime date);

    List<TokenEntity> findByExpiresAtBefore(LocalDateTime date);
}