package com.padmanabhasmac.uploadimagesql.controllers;

import com.padmanabhasmac.uploadimagesql.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class UploadImageController {

	@Autowired
	private StorageService storageService;

	@PostMapping
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {
		String imageToBeUploaded = storageService.uploadImage ( multipartFile );
		return ResponseEntity.status ( HttpStatus.OK ).body ( imageToBeUploaded );
	}

	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
		byte[] imageData = storageService.downloadImage ( fileName );
		return ResponseEntity.status ( HttpStatus.OK ).contentType ( MediaType.valueOf ( "image/png" ) ).body ( imageData );
	}

	@PostMapping("/fileSystem")
	public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile multipartFile) throws IOException {
		String imageToBeUploaded = storageService.uploadImageToFileSystem ( multipartFile );
		return ResponseEntity.status ( HttpStatus.OK ).body ( imageToBeUploaded );
	}

	@GetMapping("/fileSystem/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		byte[] imageData = storageService.downloadImageFromFileSystem ( fileName );
		return ResponseEntity.status ( HttpStatus.OK ).contentType ( MediaType.valueOf ( "image/png" ) ).body ( imageData );
	}

}
