package com.padmanabhasmac.uploadimagesql.services;

import com.padmanabhasmac.uploadimagesql.entity.FileData;
import com.padmanabhasmac.uploadimagesql.entity.ImageData;
import com.padmanabhasmac.uploadimagesql.repositories.IFileDataRepository;
import com.padmanabhasmac.uploadimagesql.repositories.IStorageRepository;
import com.padmanabhasmac.uploadimagesql.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageService {

	@Autowired
	private IStorageRepository storageRepository;

	@Autowired
	private IFileDataRepository fileDataRepository;

	private final String FOLDER_PATH = "/Users/padmanabhadas/Chayan_Personal/Spring/upload-image-sql/sample-image-files";

	public String uploadImage(MultipartFile multipartFile) throws IOException {
		ImageData imageData = storageRepository.save ( ImageData.builder ( ).name ( multipartFile.getOriginalFilename ( ) ).type ( multipartFile.getContentType ( ) ).imageData ( ImageUtils.compressImage ( multipartFile.getBytes ( ) ) ).build ( ) );
		if (imageData != null) {
			return "File uploaded successfully : " + multipartFile.getOriginalFilename ( );
		}
		return null;
	}

	public byte[] downloadImage(String fileName) {
		Optional<ImageData> dbImageData = storageRepository.findByName ( fileName );
		return ImageUtils.decompressImage ( dbImageData.get ( ).getImageData ( ) );
	}

	public String uploadImageToFileSystem(MultipartFile multipartFile) throws IOException {
		String filePath = FOLDER_PATH + "/" + multipartFile.getOriginalFilename ( );
		FileData fileData =
				fileDataRepository.save ( FileData.builder ( ).name ( multipartFile.getOriginalFilename ( ) ).type ( multipartFile.getContentType ( ) ).filePath ( filePath ).build ( ) );
		multipartFile.transferTo ( new File ( filePath ) );

		if (fileData != null) {
			return "File uploaded successfully : " + filePath;
		}
		return null;
	}

	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
		Optional<FileData> fileData = fileDataRepository.findByName ( fileName );
		String filePath = fileData.get ( ).getFilePath ( );
		return Files.readAllBytes ( new File ( filePath ).toPath ( ) );
	}
}
