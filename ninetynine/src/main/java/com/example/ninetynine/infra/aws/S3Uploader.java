package com.example.ninetynine.infra.aws;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.ninetynine.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor    // final 멤버변수가 있으면 생성자 항목에 포함시킴
@Component
@Service
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    // MultipartFile을 전달받아 File로 전환한 후 S3에 업로드
    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
        return upload(uploadFile, dirName);
    }

    // 2. uploadFile : File로 변환된 multipartFile, dirName : "image"
    private String upload(File uploadFile, String dirName) {
        // 2-1. fileName(S3 URI)
        String fileName = dirName + "/" + uploadFile.getName() + new RandomString(10).nextString();
        log.info("key : {}",fileName);
        // 3. uploadImageUrl에 image_url저장, S3에 파일 저장
        String uploadImageUrl = putS3(uploadFile, fileName);
        log.info("image_url : {}",uploadImageUrl);
        // 4. 로컬에 생성된 File 삭제 (MultipartFile -> File 전환 하며 로컬에 파일 생성됨)
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    // 3-1. uploadFile : File로 변환된 multipartFile, FileName : key
    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead)	// PublicRead 권한으로 업로드 됨
        );
        // 3-2. S3에 저장 후 URL을 가져와서 반환
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 4.
    private void removeNewFile(File targetFile) {
        if(targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        }else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        // 1-1.multiFile의 이름을 가져와서 새로운 File객체에 입력
        File convertFile = new File(file.getOriginalFilename());
        // 1-2. convertFile.createNewFile() : convertFile과 같은 이름을 가진 파일이 없으면 생성 후 true반환, 있으면 false반환
        if(convertFile.createNewFile()) {
            // 1-3. FileOutputStram : byte단위로 파일을 기록하는 클래스 / 파일 생성됨
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                // 1-4.file의 내용을 바이트로 변환해서 반환
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    public StatusResponseDto delete(String filePath){ // todo 테스트 해보기
        try{
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket,filePath));
        } catch (AmazonS3Exception e){
            e.printStackTrace();
        } catch (SdkClientException e){
            e.printStackTrace();
        }
        return StatusResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("삭제 성공")
                .build();
    }

}

/*
 * == S3Uploader ==
 * MultipartFile을 전달받아 File로 전환한 후 S3에 업로드
 *
 *  1. mulipartFile을 File로 변환
 *  1-1. multiFile의 이름을 가져와서 새로운 File객체에 입력
 *  1-2. convertFile.createNewFile() : convertFile과 같은 이름을 가진 파일이 없으면 생성 후 true반환, 있으면 false반환
 *  1-3. FileOutputStram : byte단위로 파일을 기록하는 클래스 / 파일 생성됨
 *  2. uploadFile : File로 변환된 multipartFile, dirName : "image"
 *  2-1. fileName(S3 URI)
 *  3. uploadImageUrl(객체URL)에 image_url저장, S3에 파일 저장
 *  3-1. uploadFile : File로 변환된 multipartFile, FileName : key
 *  3-2. S3에 저장 후 URL을 가져와서 반환
 *  4. 로컬에 생성된 File 삭제 (MultipartFile -> File 전환 하며 로컬에 파일 생성됨)
 *  5. 업로드된 파일의 S3 URL 주소 반환
 */