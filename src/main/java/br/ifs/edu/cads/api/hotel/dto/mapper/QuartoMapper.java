package br.ifs.edu.cads.api.hotel.dto.mapper;

import br.ifs.edu.cads.api.hotel.dto.QuartoDto;
import br.ifs.edu.cads.api.hotel.entity.Quarto;
import org.springframework.stereotype.Component;

@Component
public class QuartoMapper {

    public QuartoDto toDto(Quarto quarto) {
        return new QuartoDto(
                quarto.getId(),
                quarto.getNumBloco(),
                quarto.getNumAndar(),
                quarto.getCategoria().getId(),
                quarto.getStatusQuarto()
        );
    }
}
