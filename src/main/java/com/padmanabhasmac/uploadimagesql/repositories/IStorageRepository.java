package com.padmanabhasmac.uploadimagesql.repositories;

import com.padmanabhasmac.uploadimagesql.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStorageRepository extends JpaRepository<ImageData, Long> {

	Optional<ImageData> findByName(String fileName);
}
