package com.example.ninetynine.api.controller;

import com.example.ninetynine.domain.common.entity.Category;
import com.example.ninetynine.domain.photo.dto.PhotoRequestDto;
import com.example.ninetynine.domain.photo.entity.Photo;
import com.example.ninetynine.domain.photo.service.PhotoService;
import com.example.ninetynine.global.dto.StatusResponseDto;
import com.example.ninetynine.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping("/manage/upload")
    public StatusResponseDto<?> upload(@AuthenticationPrincipal UserDetailsImpl member,
                                       @RequestPart(value = "image") MultipartFile image,
                                       @RequestPart(value = "description")PhotoRequestDto photoRequestDto) throws IOException {
        return photoService.upload(member.getMember().getEmail(), image, photoRequestDto);
    }

    @GetMapping("/photo/{id}")
    public StatusResponseDto<?> read(@PathVariable Long id){
        return photoService.read(id);
    }

    //url이 api/popular?category=?&page=?&size=?&sort=view,DESC
    @GetMapping("/popular")
    public StatusResponseDto<?> findPopular(@RequestParam Category category,
                                            @PageableDefault(sort = "view", direction = Sort.Direction.DESC, size = 20) Pageable pageable
                                            ){
        return photoService.findPopular(category, pageable);
    }


    @PostMapping("/photo/{id}")
    public StatusResponseDto<?> delete(@AuthenticationPrincipal UserDetailsImpl member,
                                       @PathVariable Long id){
        return photoService.delete(member,id);
    }
}
