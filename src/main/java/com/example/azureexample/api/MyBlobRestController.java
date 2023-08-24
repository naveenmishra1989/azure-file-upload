package com.example.azureexample.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/blob")
@RequiredArgsConstructor
@Slf4j
public class MyBlobRestController {
    private final MyBlobService myBlobService;

    @GetMapping("/")
    public List<String> blobitemst() {
        return myBlobService.listFiles();
    }


    @GetMapping("/download/{filename}")
    public byte[] download(@PathVariable String filename) {
        log.info("download blobitem: {}", filename);
        return myBlobService.downloadFile(filename).toByteArray();
    }

    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("Filename :" + file.getOriginalFilename());
        log.info("Size:" + file.getSize());
        log.info("Contenttype:" + file.getContentType());
        myBlobService.storeFile(file.getOriginalFilename(), file.getInputStream(), file.getSize());
        return file.getOriginalFilename() + " Has been saved as a blob-item!!!";

    }
}
