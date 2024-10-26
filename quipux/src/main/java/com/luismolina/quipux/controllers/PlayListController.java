package com.luismolina.quipux.controllers;

import com.luismolina.quipux.dtos.PlayListDto;
import com.luismolina.quipux.services.PlayListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
public class PlayListController {

    private final PlayListService playListService;

    public PlayListController(PlayListService jwtService) {
        this.playListService = jwtService;
    }

    @PostMapping("/lists")
    public ResponseEntity<PlayListDto> save(@RequestBody PlayListDto playListDto) {
        PlayListDto listDto = this.playListService.create(playListDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{listName}")
                .buildAndExpand(listDto.getNombre())
                .toUri();

        return ResponseEntity.created(location).body(listDto);
    }

    @GetMapping("/lists")
    public ResponseEntity<List<PlayListDto>> showAll() {
        List<PlayListDto> playListDtoList = this.playListService.getAllList();
        return ResponseEntity.ok().body(playListDtoList);
    }

    @GetMapping("/lists/{listName}")
    public ResponseEntity<PlayListDto> getByName(@PathVariable("listName") String listName) {
        PlayListDto playListDtoList = this.playListService.getListByName(listName);
        if(playListDtoList == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(playListDtoList);
    }

    @DeleteMapping("/lists/{listName}")
    public ResponseEntity<PlayListDto> delete(@PathVariable("listName") String listName) {
        Integer delete = this.playListService.deleteByName(listName);
        if(delete == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
