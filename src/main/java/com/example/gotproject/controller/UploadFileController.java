package com.example.gotproject.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class UploadFileController {

    /*@GetMapping("/")
    public String index(){
        return "upload";
    }
*/
    @PostMapping("/upload")
    public String uploadFile(@RequestParam ("file") MultipartFile file) throws IOException {
        if(file==null || file.isEmpty()){
            return "No se ha seleccionado un fichero";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(System.getProperty("user.dir"));
        builder.append(File.separator);
        builder.append("src");
        builder.append(File.separator);
        builder.append("main");
        builder.append(File.separator);
        builder.append("resources");
        builder.append(File.separator);
        builder.append("csv");
        builder.append(File.separator);
        builder.append(file.getOriginalFilename());

        byte[] fileBytes = file.getBytes();
        Path path = Paths.get(builder.toString());
        Files.write(path, fileBytes);


        return "Fichero subido";
    }
}
