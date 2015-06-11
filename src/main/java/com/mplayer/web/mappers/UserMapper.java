package com.mplayer.web.mappers;

import com.mplayer.web.domain.User;

public interface UserMapper {
    public User getUserByName(String userName);
}

