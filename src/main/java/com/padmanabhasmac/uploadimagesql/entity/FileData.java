package com.padmanabhasmac.uploadimagesql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FILE_DATA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String type;

	private String filePath;
}
