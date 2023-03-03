package com.example.ninetynine.domain.photo.repository;

import com.example.ninetynine.domain.photo.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
