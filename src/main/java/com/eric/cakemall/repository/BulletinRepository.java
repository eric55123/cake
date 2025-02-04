package com.eric.cakemall.repository;

import com.eric.cakemall.model.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BulletinRepository extends JpaRepository<Bulletin, Integer> {
}
