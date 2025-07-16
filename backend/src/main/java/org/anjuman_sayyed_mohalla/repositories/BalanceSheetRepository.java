package org.anjuman_sayyed_mohalla.repositories;

import org.anjuman_sayyed_mohalla.model.entities.BalanceSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceSheetRepository extends JpaRepository<BalanceSheet, Serializable> {

    @Query("SELECT b FROM BalanceSheet b WHERE b.year = :year AND b.member.id = :memberId")
    Optional<BalanceSheet> findByYearAndMemberId(@Param("year") Long year,@Param("memberId") Long memberId);

    @Query("SELECT b FROM BalanceSheet b WHERE b.member.id = :memberId")
    List<BalanceSheet> findByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT b FROM BalanceSheet b WHERE b.member.name = :name")
    List<BalanceSheet> findByMemberName(@Param("name") String name);
}
