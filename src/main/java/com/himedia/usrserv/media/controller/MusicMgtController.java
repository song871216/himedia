package com.himedia.usrserv.media.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.himedia.usrserv.common.CommonHelper;
import com.himedia.usrserv.common.CommonResp;
import com.himedia.usrserv.media.pojo.MusicDetail;
import com.himedia.usrserv.media.service.MusicService;

@RestController
@RequestMapping(value="/music")
public class MusicMgtController {
	
	static final Logger logger = LoggerFactory.getLogger(MusicMgtController.class);
	
	@Autowired
	MusicService musicService;
	
	/**
	 * 查询当前音乐列表
	 * @param name
	 * @param extension
	 * @param category
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public Object getMusicList(@RequestParam("name") @NotNull String name, 
			@RequestParam("extension") @NotNull String extension,
			@RequestParam("category") @NotNull String category,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("pageNo") int pageNo) {
		try {
			return CommonResp.succeed(musicService.retrieveMusic(name, extension, category, pageSize, pageNo));
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			
			return CommonResp.failed(e.getMessage());
		}
	}
	
	/**
	 * 上传单个音乐
	 * @param musicDetails
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public Object uploadMusic(@RequestBody @Valid MusicDetail musicDetail,
			@RequestParam("file") MultipartFile file) {
		try {
			if( file == null || file.isEmpty() ) {
				return CommonResp.failed("no file uploaded");
			}
			return musicService.uploadMusic(musicDetail, file.getOriginalFilename(), file.getSize(), file.getBytes());
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return CommonResp.failed(e.getMessage());
		}
	}
	
	/**
	 * 批量上传
	 * @param files
	 * @return
	 */
	@RequestMapping(value="/batch_upload", method=RequestMethod.POST)
	public Object batchUploadMusic(@RequestParam("category") @NotNull String category, @RequestParam("description") String description, 
			@RequestParam("file") MultipartFile[] files) {
		try {
			if( CommonHelper.isNullOrEmpty(files) ) {
				return CommonResp.failed("not file uploaded.");
			}
			
			return musicService.batchUploads(category, description, files);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return CommonResp.failed(e.getMessage());
		}
	}
	
	/**
	 * 批量更新
	 * @param musicDetails
	 * @return
	 */
	@RequestMapping(value="/batch_update", method=RequestMethod.POST)
	public Object batchUpdate(@RequestBody @Valid List<MusicDetail> musicDetails) {
		try {
			return musicService.batchUpdates(musicDetails);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return CommonResp.failed(e.getMessage());
		}
	}
	
	/**
	 * 下载
	 * @param id
	 * @return
	 */
	@RequestMapping(value="download", method=RequestMethod.GET)
	public Object downloadMusic(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") long id) {
		try {
			return musicService.downMusic(id, response);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return CommonResp.failed(e.getMessage());
		}
	}
	
}
