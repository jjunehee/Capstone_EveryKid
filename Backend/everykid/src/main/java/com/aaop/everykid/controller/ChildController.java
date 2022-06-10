package com.aaop.everykid.controller;

import com.aaop.everykid.dto.ChildFormDto;
import com.aaop.everykid.dto.RegisterCFormDto;
import com.aaop.everykid.entity.Child;
import com.aaop.everykid.service.RegisterCService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/child")
public class ChildController {

    private final RegisterCService registerCService;


    @PostMapping(value = "/child")
    public RegisterCFormDto registerCFormDto(RegisterCFormDto registerCFormDto) {
        Child child = Child.createChild(registerCFormDto);
        registerCService.saveChild(child);
        System.out.println("아이등록" + registerCFormDto);
        return registerCFormDto;
    }
}


/*
    //@Value("${upload.path}")
    private String uploadPath = "C:/upload";

    @PostMapping(value = "/child")
    public ResponseEntity register(@RequestPart("child") String childString,
                                   @RequestPart("file") MultipartFile picture)
            throws Exception {
        log.info("childString" + childString);

        Child child = new ObjectMapper().readValue(childString, Child.class);

        String cAGE = child.getCAGE();
        String cNAME = child.getCNAME();

        if (cNAME != null) {
            log.info("child.getCNAME()");
        }
        if (cAGE != null) {
            log.info("child.getCAGE()");
        }
        child.setPicture(picture);

        MultipartFile file = child.getPicture();

        log.info("orginalName:" + file.getOriginalFilename());
        log.info("size" + file.getSize());
        log.info("contentType" + file.getContentType());

        String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());

        child.setPictureUrl(createdFileName);

        this.childService.regist(child);

        log.info("register child.getChildCKID()=" + child.getCKID());

        Child createdChild = new Child();
        createdChild.setCKID(child.getCKID());

        return new ResponseEntity<>(createdChild, HttpStatus.OK);
    }


    private String uploadFile(String originalName, byte[] fileData) throws Exception {
        UUID uid = UUID.randomUUID();

        String createdFileName = uid.toString() + "_" + originalName;

        File target = new File(uploadPath, createdFileName);

        FileCopyUtils.copy(fileData, target);

        return createdFileName;
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> displayFile(Long childId) throws Exception {
        ResponseEntity<byte[]> entity = null;

        String fileName = childService.getPicture(childId);

        log.info("FILE NAME:" + fileName);

        try {
            String formatName = fileName.substring(fileName.lastIndexOf(",") + 1);

            MediaType mediaType = getMediaType(formatName);

            HttpHeaders headers = new HttpHeaders();

            File file = new File(uploadPath + File.separator + fileName);

            if (mediaType != null) {
                headers.setContentType(mediaType);
            }

            entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    private MediaType getMediaType(String formatName) {
        if (formatName != null) {
            if (formatName.equals("JPG")) {
                return MediaType.IMAGE_JPEG;
            }
            if (formatName.equals("GIF")) {
                return MediaType.IMAGE_GIF;
            }
            if (formatName.equals("PNG")) {
                return MediaType.IMAGE_PNG;
            }
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<List<Child>> list() throws Exception {
        log.info("list");
        List<Child> childList = this.childService.list();

        return new ResponseEntity<>(childList, HttpStatus.OK);
    }

    @GetMapping("/{childId}")
    public ResponseEntity<Child> read(@PathVariable("childId") Long childId) throws Exception {
        log.info("read");

        Child child = this.childService.read(childId);

        return new ResponseEntity<>(child, HttpStatus.OK);
    }
    @DeleteMapping("/{childId}")
    public ResponseEntity<Void> remove(@PathVariable("childId") Long childId) throws Exception{
        log.info("remove");
        this.childService.remove(childId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Child> modify(@RequestPart("child") String childString, @RequestPart(name = "file", required = false) MultipartFile picture) throws Exception {
        log.info("childString:" + childString);

        Child child = new ObjectMapper().readValue(childString, Child.class);

        String childName = child.getCNAME();
        String childAge = child.getCAGE();

        if (childName != null) {
            child.setCNAME(childName);
        }
        if (childAge != null) {
            child.setCAGE(childAge);
        }

        if (picture != null) {
            child.setPicture(picture);

            MultipartFile file = child.getPicture();
            String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
            child.setPictureUrl(createdFileName);
        } else {
            Child oldchild = this.childService.read(child.getCKID());
            child.setPictureUrl(oldchild.getPictureUrl());

        }
        this.childService.modify(child);

        Child modifiedChild = new Child();
        modifiedChild.setCKID(child.getCKID());

        return new ResponseEntity<>(modifiedChild, HttpStatus.OK);
    }
}



*/



















