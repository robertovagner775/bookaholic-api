package com.bookaholic.backend.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookaholic.backend.model.Epub;

import com.bookaholic.backend.repository.EpubRepository;
import com.bookaholic.backend.service.FileService;



import jakarta.servlet.annotation.MultipartConfig;



@CrossOrigin("*")
@RestController
@RequestMapping("/files")
public class uploadController {

    @Autowired
    private FileService fileService;



    @PostMapping(value = "/epub")
    public ResponseEntity<?> salvarArquivo(@RequestBody @RequestParam("file") MultipartFile file, @RequestParam("id")  long id) {

        try{
            var fileUploadResponse =  fileService.uploadFileEpub(file, id);
            return ResponseEntity.ok(fileUploadResponse);
        }catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping(value = "/imagem")
    public ResponseEntity<?> salvarImagem(@RequestBody @RequestParam("file") MultipartFile file, @RequestParam("id")  long id) {

        try{
            var fileUploadResponse =  fileService.uploadFileImagem(file, id);
            return ResponseEntity.ok(fileUploadResponse);
        }catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private String extrairExtensao(String nomeArquivo) {
        int i = nomeArquivo.lastIndexOf(".");
        return nomeArquivo.substring(i + 1);
    }
    
    
}
