package com.example.ninetynine.domain.photo.repository;

import com.example.ninetynine.domain.common.entity.Category;
import com.example.ninetynine.domain.photo.entity.Photo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Modifying
    @Query("update Photo p set p.view = p.view+1 where p.id = :id")
    int updateView(Long id);

    Slice<Photo> findByCategory(Category category, Pageable pageable);
}
