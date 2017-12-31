package com.himedia.usrserv.media.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.himedia.usrserv.basedao.Pager;
import com.himedia.usrserv.common.CommonResp;
import com.himedia.usrserv.media.dao.MusicInfoDao;
import com.himedia.usrserv.media.domain.MusicInfo;
import com.himedia.usrserv.media.pojo.MusicDetail;

@Service
public class MusicService {
	
	static final Logger logger = LoggerFactory.getLogger(MusicService.class);
	
	@Autowired
	MusicInfoDao musicInfoDao;
	
	@Value("${music.upload.base-path:/}")
	String save2path;

	/**
	 * 按条件模糊查找并分页
	 * @param name
	 * @param extension
	 * @param category
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Pager retrieveMusic(@NotNull String name, @NotNull String extension, @NotNull String category, int pageSize,
			int pageNo) {
		logger.info("start to retrieve music from page: {} per size: {} by name: {}, extension: {}, category: {}", 
				pageNo, pageSize, name, extension, category);
		if( pageNo < 1 ) {
			pageNo = 1;
		}
		
		if( pageSize < 10 ) {
			pageSize = 10;
		}
		
		return musicInfoDao.retrieveMusic(name, extension, category, pageNo, pageSize);
	}

	/**
	 * 上传音乐文件
	 * @param musicDetail
	 * @param filename
	 * @param size
	 * @param data
	 * @return
	 */
	@Transactional
	public CommonResp uploadMusic(@Valid MusicDetail musicDetail, String filename, long size, byte[] data) {
		MusicInfo musicInfo = new MusicInfo();
		BeanUtils.copyProperties(musicDetail, musicInfo);
		
		String extension = Files.getFileExtension(filename);
		musicInfo.setCreateDate(new Date());
		musicInfo.setExtension(extension);
		musicInfo.setSavePath(save2path);
		musicInfo.setSize(size);
		musicInfo.setFullName(musicInfo.getName()+"."+extension);
		
		File dstFile = new File(save2path, musicInfo.getFullName());
		logger.info("music: {} save to： {}", musicInfo, dstFile);
		try {
			Files.write(data, dstFile);
			
			Serializable id = musicInfoDao.save(musicInfo);
			logger.info("upload success, id: {}", id);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			
			return CommonResp.failed(e.getMessage());
		}
		return CommonResp.succeed();
	}

	/**
	 * 批量上传文件
	 * @param description 
	 * @param category 
	 * @param files
	 * @return
	 */
	@Transactional
	public CommonResp batchUploads(@NotNull String category, @NotNull String description, MultipartFile[] files) {
		
		List<Serializable> ids = new ArrayList<>();
		try {
			for(MultipartFile file : files) {
				Serializable id = processFile(category, description, file);
				if( id == null ) {
					logger.error("file upload failed: {}", file);
				}
				
				ids.add(id);
			}
		}catch(Exception e) {
			return CommonResp.failed(e.getMessage());
		}
		
		return CommonResp.succeed(ids);
	}

	private Serializable processFile(@NotNull String category, @NotNull String description, MultipartFile file) throws IOException {
		if( file == null || file.isEmpty() ) {
			return null;
		}
		MusicInfo musicInfo = new MusicInfo();
		String filename = file.getOriginalFilename();
		String name = Files.getNameWithoutExtension(filename);
		String extension = Files.getFileExtension(filename);
		
		musicInfo.setCategory(category);
		musicInfo.setDescription(description);
		musicInfo.setCreateDate(new Date());
		musicInfo.setExtension(extension);
		musicInfo.setFullName(filename);
		musicInfo.setName(name);
		musicInfo.setSavePath(save2path);
		musicInfo.setSize(file.getSize());
		
		File dstFile = new File(save2path, filename);
		Files.write(file.getBytes(), dstFile);
		return musicInfoDao.save(musicInfo);
	}

	/**
	 * 批量更新音乐信息
	 * @param musicDetails
	 * @return
	 */
	@Transactional
	public CommonResp batchUpdates(@Valid List<MusicDetail> musicDetails) {
		musicDetails.forEach(musicDetail -> musicInfoDao.updateMusicInfo(musicDetail));
		return CommonResp.succeed();
	}

	/**
	 * 下载音乐文件
	 * @param id
	 * @param response
	 * @return
	 */
	public CommonResp downMusic(long id, HttpServletResponse response) {
		MusicInfo musicInfo = musicInfoDao.findById(id);
		if ( musicInfo == null ) {
			logger.error("did not found music by id: {}", id);
			return CommonResp.failed("did not found music by id: "+id);
		}
		
		File file = new File(musicInfo.getSavePath(), musicInfo.getFullName());
		String filename = musicInfo.getName()+"."+musicInfo.getExtension();
		if( !file.exists() || !file.isFile() ) {
			logger.error("file not exist by: {}", musicInfo);
			return CommonResp.failed("file not exist by name: +"+musicInfo.getFullName());
		}
		
		response.setHeader("Content-Disposition", "attachment;fileName="+filename);
		response.setHeader("Pragma", "No-cache");  
		response.setHeader("Cache-Control", "No-cache");  
		response.setDateHeader("Expires", 0);
		response.setHeader("Content-Type", "audio/"+musicInfo.getExtension());
		
		byte[] buffer = new byte[1024];
		try(FileInputStream fis = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();){
			int i = bis.read(buffer);
			while( i != -1 ) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
			
			os.flush();
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return CommonResp.failed(e.getMessage());
		}
		return null;
	}

}
