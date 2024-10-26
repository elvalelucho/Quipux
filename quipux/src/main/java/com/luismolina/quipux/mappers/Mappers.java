package com.luismolina.quipux.mappers;

import com.luismolina.quipux.database.entity.PlayList;
import com.luismolina.quipux.dtos.PlayListDto;

public interface Mappers {
    PlayList fromPlayListDto(PlayListDto playListDto);
    PlayListDto fromPlayList(PlayList playList);
}
