package com.projetospring.chatonline.core.security.token;

import com.projetospring.chatonline.core.security.token.enums.TypeToken;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_token")
public class TokenEntity {

    @Id
    @Column(name = "token_hash", length = 255)
    private final String tokenInHash;

    @Column(name = "created_at", nullable = false)
    private final LocalDateTime createsAt;

    @Column(name = "expires_at", nullable = false)
    private final LocalDateTime expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_token", nullable = false)
    private final TypeToken typeToken;

    @Column(name = "is_revoked", nullable = false)
    private boolean isRevoked;

    public TokenEntity(String tokenInHash, LocalDateTime createsAt, LocalDateTime expiresAt, TypeToken typeToken, boolean isRevoked) {
        this.tokenInHash = tokenInHash;
        this.createsAt = createsAt;
        this.expiresAt = expiresAt;
        this.typeToken = typeToken;
        this.isRevoked = isRevoked;
    }

    public TokenEntity() {
        this.tokenInHash = null;
        this.createsAt = null;
        this.expiresAt = null;
        this.typeToken = null;
        this.isRevoked = false;
    }

    public String getTokenInHash() {
        return tokenInHash;
    }

    public LocalDateTime getCreatesAt() {
        return createsAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public TypeToken getTypeToken() {
        return typeToken;
    }

    public boolean isRevogate() {
        return isRevoked;
    }

    public void setRevogate(boolean revogate) {
        isRevoked = revogate;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public boolean isValid() {
        return !isRevoked && !isExpired();
    }
}
