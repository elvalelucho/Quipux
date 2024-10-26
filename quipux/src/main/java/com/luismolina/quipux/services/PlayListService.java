package com.luismolina.quipux.services;

import com.luismolina.quipux.database.entity.PlayList;
import com.luismolina.quipux.database.repos.PlayListRepo;
import com.luismolina.quipux.dtos.PlayListDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PlayListService {
    private final PlayListRepo playListRepo;
    private final ModelMapper modelMapper = new ModelMapper();

    public PlayListService(PlayListRepo playListRepo) {
        this.playListRepo = playListRepo;
    }

    public PlayListDto create(PlayListDto newPlayList) {
        try{
            PlayList playList = PlayList.builder().name(newPlayList.getNombre()).description(newPlayList.getDescripcion()).build();
            this.playListRepo.save(playList);
        } catch (Exception ex){
            return null;
        }
        return newPlayList;
    }

    public List<PlayListDto> getAllList() {
        try{
            return this.playListRepo.findAll().stream()
                    .map(playList -> PlayListDto.builder().nombre(playList.getName()).descripcion(playList.getDescription()).build())
                    .toList();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public PlayListDto getListByName(String listName) {
        try{
            return this.playListRepo.findByName(listName).map(playList -> PlayListDto.builder().nombre(playList.getName()).descripcion(playList.getDescription()).build()).get();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public Integer deleteByName(String listName) {
        try{
            return this.playListRepo.deleteByName(listName);
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

}
