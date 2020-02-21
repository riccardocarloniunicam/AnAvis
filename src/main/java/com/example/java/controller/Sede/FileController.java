package com.example.java.controller.Sede;

import com.example.java.model.Analisi;
import com.example.java.model.DBFile;
import com.example.java.payload.UploadFileResponse;
import com.example.java.service.AnalisiService;
import com.example.java.service.DBFileStorageService;
import com.example.java.service.PrenotazioniService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private DBFileStorageService dbFileStorageService;
    @Autowired
    private PrenotazioniService prenotazioniService;
    @Autowired
    private AnalisiService analisiService;




    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("user_id") Integer user_id,@RequestParam("prenotazione_id")Integer prenotazione_id,
                                         @RequestParam("nota")String nota) {
        //metti la prenotazione eseguita
        //INSERT INTO ANALISI user_id file download, prenotazione id;

        DBFile dbFile = dbFileStorageService.storeFile(file,user_id,prenotazione_id);
        prenotazioniService.updateAnalisiParamentro(prenotazione_id);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        Analisi analisi = new Analisi();
        analisi.setUser_id(user_id);
        analisi.setPrenotazione_id(prenotazione_id);
        analisi.setDownload_path(fileDownloadUri);
        analisi.setNota(nota);
        analisiService.saveAnalisi(analisi);


        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }


    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}