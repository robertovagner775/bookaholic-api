package com.bookaholic.backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookaholic.backend.dto.FileUploadResponse;
import com.bookaholic.backend.model.Epub;
import com.bookaholic.backend.model.FileResponse;
import com.bookaholic.backend.model.Imagem;
import com.bookaholic.backend.model.Livro;
import com.bookaholic.backend.repository.EpubRepository;
import com.bookaholic.backend.repository.ImagemRepository;
import com.bookaholic.backend.repository.LivroRepository;

import jakarta.mail.Multipart;

@Service
public class FileService {


    @Value("${file.upload-path-epub}")
    private String uploadPathEpub;

    
    @Value("${file.upload-path-imagem}")
    private String uploadPathImagem;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private EpubRepository epubRepository;
    

    public FileResponse uploadFileEpub(MultipartFile file, Long id) {

            String FTP_ADDRESS = "files.000webhost.com";
            String LOGIN = "robertomeudominio";
            String PSW = "12345@Fatec";
            FTPClient con = null;

        try{
            con = new FTPClient();
            con.connect(FTP_ADDRESS);

            con.login(LOGIN, PSW);
                con.enterLocalPassiveMode();
                con.setFileType(FTP.FILE_STRUCTURE);

                
                var originalFileName = file.getOriginalFilename();
                var fileExtencion = originalFileName.substring(originalFileName.lastIndexOf("."));
                var ramdomFilename = UUID.randomUUID() + fileExtencion;

                var targetPath =  uploadPathEpub + "/"  + ramdomFilename;

                if(!Files.exists(Path.of(uploadPathEpub))) {
                    Files.createDirectories(Path.of(uploadPathEpub));
                }

                Epub epub = new Epub(null,fileExtencion, ramdomFilename ,targetPath, file.getSize());
                Long idEpub = epubRepository.save(epub).getIdEpub();
                setIdEpubLivro(idEpub, id);

                Files.copy(file.getInputStream(), Path.of(targetPath));
                
                con.storeFile(uploadPathEpub + ramdomFilename, file.getInputStream());
            
                con.logout();
                con.disconnect();
                var fileResponse = new FileResponse();
                fileResponse.setFilename(ramdomFilename);
                fileResponse.setSize(file.getSize());
                fileResponse.setStatusMessage("Sucesso !!!!!!!!!");
                
                return fileResponse;
              
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
     
    }

    private void setIdEpubLivro(Long id_epub, Long id_livro) {
        Livro livro = livroRepository.findById(id_livro).get();
        livro.setEpub(epubRepository.findById(id_epub).get());
        livroRepository.save(livro);
    }

     public FileResponse uploadFileImagem(MultipartFile file, Long id) {


        
         String FTP_ADDRESS = "files.000webhost.com";
         String LOGIN = "robertomeudominio";
         String PSW = "12345@Fatec";
         FTPClient con = null;
         
         try{
       
            con = new FTPClient();
            con.connect(FTP_ADDRESS);

            con.login(LOGIN, PSW);
                con.enterLocalPassiveMode();
                con.setFileType(FTP.BINARY_FILE_TYPE);
                
                var originalFileName = file.getOriginalFilename();
                var fileExtencion = originalFileName.substring(originalFileName.lastIndexOf("."));
                var ramdomFilename = UUID.randomUUID() + fileExtencion;
                
                var targetPath = uploadPathImagem + "/"  + ramdomFilename;
                con.storeFile(uploadPathImagem + ramdomFilename, file.getInputStream());
                con.logout();
                con.disconnect();

            if(!Files.exists(Path.of(uploadPathImagem))) {
                Files.createDirectories(Path.of(uploadPathImagem));
            }

            //Epub epub = new Epub(null,fileExtencion, ramdomFilename ,targetPath, file.getSize(), livroRepository.findById(id).get());
            //epubRepository.save(epub);

            Imagem imagem = new Imagem(null, ramdomFilename, fileExtencion, targetPath, livroRepository.findById(id).get());
            imagemRepository.save(imagem);

            
            Files.copy(file.getInputStream(), Path.of(targetPath));
            
            var fileResponse = new FileResponse();
            fileResponse.setFilename(ramdomFilename);
            fileResponse.setSize(file.getSize());
            fileResponse.setStatusMessage("Sucesso !!!!!!!!!");
            
        
            return fileResponse;
            
        }catch (IOException e) {
            throw new RuntimeException(e);
        
    }
     

    
    

}
}
