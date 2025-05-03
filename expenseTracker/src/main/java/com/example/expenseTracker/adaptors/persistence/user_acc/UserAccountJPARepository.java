package com.example.expenseTracker.adaptors.persistence.user_acc;

import com.example.expenseTracker.application.ports.user_acc.UserAccountRepository;
import com.example.expenseTracker.domain.entity.account.user_acc.UserAccount;
import org.apache.catalina.User;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

interface SpringDataUserRepo extends JpaRepository<UserAccountJPAEntity, Long> {
    Optional<UserAccountJPAEntity> findByUsername(String username);
    Optional<UserAccountJPAEntity> findByEmail(String email);
//    boolean checkPassword (String password);

}

@Mapper(componentModel = "spring")
interface UserMapper {
    UserAccount toDomain(UserAccountJPAEntity e);
    UserAccountJPAEntity toJpa(UserAccount d);
}

@Repository
public class UserAccountJPARepository implements UserAccountRepository {
    private final SpringDataUserRepo jpa;
    private final UserMapper mapper;

    public UserAccountJPARepository(SpringDataUserRepo jpa, UserMapper mapper) {
        this.jpa = jpa; this.mapper = mapper;
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        return jpa.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    public Optional<UserAccount> findByEmail(String email) {
        return jpa.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public UserAccount saveUser(UserAccount userAccount) {
        return mapper.toDomain(jpa.save(mapper.toJpa(userAccount)));
    }

    @Override
    public void updateLastLogin(Long userId, Instant ts) {
        jpa.findById(userId).ifPresent(e -> { e.setLastLoginAt(ts); jpa.save(e); });
    }
}
