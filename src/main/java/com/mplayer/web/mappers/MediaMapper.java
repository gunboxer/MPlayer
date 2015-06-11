package com.mplayer.web.mappers;

import com.mplayer.web.domain.Media;
import com.mplayer.web.domain.SelectLimitation;
import java.util.List;

public interface MediaMapper {
    public void insertMedia(Media media);
    public List getMediaList(SelectLimitation selectLimitation);
    public int getMediaCount(SelectLimitation selectLimitation);
    public Media getMediaDetails(String id);
    public void deleteMedia(String id);
    public int isMediaUsed(String songData);
    public void updateMedia(Media media);
    public Long getMaxMediaSize();
}
