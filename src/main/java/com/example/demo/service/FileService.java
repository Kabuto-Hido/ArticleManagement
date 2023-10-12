package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Service
public interface FileService {
    boolean isCsvFormat(MultipartFile file);
    void processAndSaveData(MultipartFile file);
    void exportUserToCsv(Writer writer);
    void exportUserToPDF(HttpServletResponse response) throws IOException;
}
