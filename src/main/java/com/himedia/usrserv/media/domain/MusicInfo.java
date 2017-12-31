package com.himedia.usrserv.media.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="music_info")
public class MusicInfo {

	@Id
	@Column(name="id", nullable=false, unique=true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	//music name without extension
	@Column(name="name", nullable=false, length=256)
	String name;
	
	//music file extension
	@Column(name="extension", nullable=false, length=20)
	String extension;
	
	//music full name wit extension
	@Column(name="full_name", nullable=false, length=276)
	String fullName;
	
	//music file size
	@Column(name="size", nullable=false)
	Long size;
	
	@Column(name="category")
	String category;
	
	@Column(name="save_path", nullable=false)
	@JsonIgnore
	String savePath;
	
	@Column(name="description")
	String description;
	
	//file upload date
	@Column(name="create_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date createDate;
	
	@Column(name="create_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "MusicInfo [id=" + id + ", name=" + name + ", extension=" + extension + ", fullName=" + fullName
				+ ", size=" + size + ", category=" + category + ", savePath=" + savePath + ", description="
				+ description + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
