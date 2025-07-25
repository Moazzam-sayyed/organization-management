package org.anjuman_sayyed_mohalla.repositories;

import org.anjuman_sayyed_mohalla.model.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface MemberRepository extends JpaRepository<Member, Serializable> {

    Member findByName(String name);
}
