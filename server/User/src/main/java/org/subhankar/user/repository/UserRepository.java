package org.subhankar.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.subhankar.user.model.DO.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE " +
            "(:name IS NULL OR u.name LIKE %:name%) AND " +
            "(:email IS NULL OR u.email LIKE %:email%) AND " +
            "(:dob IS NULL OR u.dob LIKE %:dob%) AND " +
            "(:phone IS NULL OR u.phone LIKE %:phone%)")
    List<User> searchUser(@Param("name") String name,
                          @Param("email") String email,
                          @Param("dob") String dob,
                          @Param("phone") String phone);

    Optional<User> findByEmail(String email);
}
