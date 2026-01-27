package br.ifs.edu.cads.api.hotel.dto.mapper;

import br.ifs.edu.cads.api.hotel.dto.QuartoDto;
import br.ifs.edu.cads.api.hotel.dto.QuartoFormDto;
import br.ifs.edu.cads.api.hotel.entity.Quarto;
import br.ifs.edu.cads.api.hotel.enums.StatusQuarto;
import org.springframework.stereotype.Component;

@Component
public class QuartoMapper {

    public QuartoDto toDto(Quarto quarto) {
        return new QuartoDto(
                quarto.getId(),
                quarto.getNumQuarto(),
                quarto.getNumBloco(),
                quarto.getNumAndar(),
                quarto.getCategoria().getId(),
                quarto.getStatusQuarto()
        );
    }

    public Quarto formToEntity(QuartoFormDto quartoFormDto) {
        return new Quarto(
                quartoFormDto.numQuarto(),
                quartoFormDto.numBloco(),
                quartoFormDto.numAndar(),
                StatusQuarto.DISPONIVEL
        );
    }
}
