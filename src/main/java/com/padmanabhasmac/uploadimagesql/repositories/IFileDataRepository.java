package com.padmanabhasmac.uploadimagesql.repositories;

import com.padmanabhasmac.uploadimagesql.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IFileDataRepository extends JpaRepository<FileData, Integer> {
	Optional<FileData> findByName(String fileName);
}
