package com.luismolina.quipux.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
public class PlayListDto {
    private String nombre;
    private String descripcion;
    private List<SongDto> canciones;

    public PlayListDto(){}

    public PlayListDto(String nombre, String descripcion, List<SongDto> canciones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.canciones = canciones;
    }
}
