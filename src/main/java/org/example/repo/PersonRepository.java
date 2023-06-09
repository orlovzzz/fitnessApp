package org.example.repo;

import org.example.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p.subscription FROM Person p WHERE p.username = :username")
    public Date findSubscriptionByUsername(@Param("username") String username);

    public Optional<Person> findByUsername(String username);

    @Query("SELECT p.coach FROM Person p WHERE p.username = :username")
    public String findCoachByUsername(@Param("username") String username);

    @Query("SELECT p.balance FROM Person p WHERE p.username = :username")
    public int findBalanceByUsername(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("UPDATE Person p SET p.balance = p.balance + :amount WHERE p.username = :username")
    void updateBalanceByUsername(@Param("username") String username, @Param("amount") int amount);

    @Transactional
    @Modifying
    @Query("UPDATE Person p SET p.subscription = :subscription WHERE p.username = :username")
    void updateSubscriptionByUsername(@Param("username") String username, @Param("subscription") Date subscription);

    @Transactional
    @Modifying
    @Query("UPDATE Person p SET p.coach = :coach WHERE p.username = :username")
    void updateCoachByUsername(@Param("username") String username, @Param("coach") String coach);
}
