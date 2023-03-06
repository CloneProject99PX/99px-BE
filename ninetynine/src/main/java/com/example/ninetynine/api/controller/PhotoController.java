package com.example.ninetynine.api.controller;

import com.example.ninetynine.domain.photo.entity.Photo;
import com.example.ninetynine.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PhotoController {

//    @PostMapping("/manage/upload")
//    public StatusResponseDto<T> upload(){
//
//    }

//    @GetMapping("/popular")
//    public Page<Photo> getPhotos(@RequestParam String sort,
//                                 @RequestParam int page,
//                                 @RequestParam int size){
//        PageRequest.of(page, size, sort)
//    }
}
