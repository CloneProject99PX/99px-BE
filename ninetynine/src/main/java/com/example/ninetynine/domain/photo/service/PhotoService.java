package com.example.ninetynine.domain.photo.service;

import com.example.ninetynine.domain.common.entity.Category;
import com.example.ninetynine.domain.member.entity.Member;
import com.example.ninetynine.domain.member.repository.MemberRepository;
import com.example.ninetynine.domain.photo.dto.PhotoRequestDto;
import com.example.ninetynine.domain.photo.dto.PhotoResponseDto;
import com.example.ninetynine.domain.photo.entity.Photo;
import com.example.ninetynine.domain.photo.repository.PhotoRepository;
import com.example.ninetynine.global.dto.StatusResponseDto;
import com.example.ninetynine.global.error.CustomException;
import com.example.ninetynine.global.error.ErrorCode;
import com.example.ninetynine.global.security.UserDetailsImpl;
import com.example.ninetynine.infra.aws.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoService { // todo statusResponseDto.toResponseEntity() method + status code 만들어서 배포

    private final MemberRepository memberRepository;

    private final PhotoRepository photoRepository;

    private final S3Uploader s3Uploader;

    @Transactional
    public StatusResponseDto<?> upload(String email, MultipartFile s3image, PhotoRequestDto photoRequestDto) throws IOException {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_CLIENT)
        );
        String imageUrl = s3Uploader.upload(s3image, "image");
        Photo photo = photoRepository.saveAndFlush(new Photo(photoRequestDto, member, imageUrl));
        return StatusResponseDto.builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(new PhotoResponseDto(photo))
                .msg("작성을 완료하였습니다")
                .build();

    }
    @Transactional
    public StatusResponseDto<?> read(Long id) {
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PHOTO));
        photoRepository.updateView(id);
        return StatusResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .data(new PhotoResponseDto(photo))
                .build();
    }


    public StatusResponseDto<?> delete(UserDetailsImpl member, Long id) {
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PHOTO));
        if (!photo.getMember().equals(member.getMember())) throw new CustomException(ErrorCode.INVALID_REQUEST);
        else {
            s3Uploader.delete(photo.getUrl());
            photoRepository.delete(photo);
        }
        return StatusResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("삭제 완료")
                .build();
    }

    public StatusResponseDto<?> findPopular(Category category, Pageable pageable) {
        Slice<Photo> photos = photoRepository.findByCategory(category,pageable);
        List<PhotoResponseDto> photolist = photos.stream().map(PhotoResponseDto::new).collect(Collectors.toList());
        return StatusResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .data(photolist)
                .build();
    }
}
