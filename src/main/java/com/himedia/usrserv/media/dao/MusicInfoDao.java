package com.himedia.usrserv.media.dao;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.himedia.usrserv.basedao.AbstractDao;
import com.himedia.usrserv.basedao.Pager;
import com.himedia.usrserv.media.domain.MusicInfo;
import com.himedia.usrserv.media.pojo.MusicDetail;

@Repository
public class MusicInfoDao extends AbstractDao<MusicInfo> {

	static final Logger logger = LoggerFactory.getLogger(MusicInfoDao.class);

	public Pager retrieveMusic(@NotNull String name, @NotNull String extension, @NotNull String category, int pageNo,
			int pageSize) {
		Criteria criteria = getSession().createCriteria(MusicInfo.class);
		criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE))
		.add(Restrictions.like("extension", extension, MatchMode.ANYWHERE))
		.add(Restrictions.like("category", category, MatchMode.ANYWHERE));
		
		return toPager(criteria, pageNo, pageSize);
	}

	public void updateMusicInfo(MusicDetail musicDetail) {
		if( musicDetail.getId() == null ) {
			return;
		}
		MusicInfo musicInfo = get(MusicInfo.class, musicDetail.getId());
		
		if( musicInfo == null ) {
			return;
		}
		
		musicInfo.setCategory(musicDetail.getCategory());
		musicInfo.setDescription(musicDetail.getDescription());
		if( StringUtils.isNotEmpty(musicDetail.getName()) ) {
			musicInfo.setName(musicDetail.getName());
		}
		
		update(musicInfo);
	}
	
	
}
