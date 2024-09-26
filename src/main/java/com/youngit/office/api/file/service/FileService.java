package com.youngit.office.api.file.service;

import com.youngit.office.api.file.mapper.FileMapper;
import com.youngit.office.api.file.model.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    private final FileMapper fileMapper;

    @Value("${file.upload-dir}")  // 파일 업로드 경로를 application.properties에서 정의
    private String uploadDir;

    @Autowired
    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    /**
     * 파일 서버 내 업로드 (견적서, 설치팀내역서 + 게시글 첨부파일)
     */
    public int uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if(fileName == null) {
            throw new IllegalArgumentException("파일 이름이 없습니다.");
        }
        Path filePath = Paths.get(uploadDir, fileName);
        FileModel fileModel = new FileModel();
        int result = fileMapper.insertFile(fileModel);
        return result;
    }

    public int updateFile(String fileUniqId, MultipartFile file) throws IOException {
        //서버 디렉토리 안에서 파일 교체
        String fileName = file.getOriginalFilename();
        if(fileName == null) {
            throw new IllegalArgumentException("파일 이름이 없습니다.");
        }
        Path filePath = Paths.get(uploadDir, fileName);
        FileModel fileModel = new FileModel();
        int result = fileMapper.updateFile(fileModel);
        return result;
    }
    public int deleteFile(String fileUniqId) {
        //서버 디렉토리 안에서 파일 삭제
        int result = fileMapper.deleteFile(fileUniqId);
        return result;

    }

    public File convertByteArrayToFile(byte[] file) throws IOException
    {
        File tempFile = File.createTempFile("temp", null);
        try(FileOutputStream fos = new FileOutputStream(tempFile))
        {
            fos.write(file);
        }
        return tempFile;

    }
}
