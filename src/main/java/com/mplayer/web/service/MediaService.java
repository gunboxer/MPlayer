package com.mplayer.web.service;

import com.mplayer.web.domain.Media;
import com.mplayer.web.domain.SelectLimitation;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mplayer.web.mappers.MediaMapper;
import java.io.FileNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("mediaService")
@Transactional
public class MediaService
{
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(MediaService.class);
    
    public void insertMedia(Media media) {
        MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
        logger.info("insertMedia called.");
        mediaMapper.insertMedia(media);
    }
    
    public List getMediaList(SelectLimitation selectLimitation) throws FileNotFoundException {
        MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
        List list = mediaMapper.getMediaList(selectLimitation);
        logger.info("getMediaList called. returned list of " + list.size());
        return list;
    }
    
    public int getMediaCount(SelectLimitation selectLimitation) throws FileNotFoundException {
        MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
        int count = mediaMapper.getMediaCount(selectLimitation);
        logger.info("getMediaCount called. returned " + count);
        return count;
    }
    
    public Media getMediaDetails(String id) throws FileNotFoundException {
        MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
        logger.info("getMediaDetails called for Media ID of " + id);
        return mediaMapper.getMediaDetails(id);
    }
    
    public void deleteMedia(String id) {
        MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
        logger.info("deleteMedia called.");
        mediaMapper.deleteMedia(id);
    }
    
    public int isMediaUsed(String songData) {
        MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
        logger.info("isMediaUsed called for file name " + songData);
        return mediaMapper.isMediaUsed(songData);
    }
    
    public void updateMedia(Media media) {
        MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
        logger.info("updateMedia called for Media ID of " + media.getId());
        mediaMapper.updateMedia(media);
    }
    
    public Long getMaxMediaSize() {
        MediaMapper mediaMapper = sqlSession.getMapper(MediaMapper.class);
        Long size = mediaMapper.getMaxMediaSize();
        logger.info("getMaxMediaSize called. returned value is " + size);
        return size;
    }
}