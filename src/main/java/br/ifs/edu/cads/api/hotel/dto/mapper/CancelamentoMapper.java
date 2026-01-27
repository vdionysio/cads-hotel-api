package br.ifs.edu.cads.api.hotel.dto.mapper;

import br.ifs.edu.cads.api.hotel.dto.CancelamentoDto;
import br.ifs.edu.cads.api.hotel.dto.CancelamentoFormDto;
import br.ifs.edu.cads.api.hotel.entity.Cancelamento;
import org.springframework.stereotype.Component;

@Component
public class CancelamentoMapper {
    public Cancelamento formToEntity(CancelamentoFormDto cancelamentoFormDto) {
        return new Cancelamento(
                cancelamentoFormDto.motivo()
        );
    }

    public CancelamentoDto toDto(Cancelamento cancelamento) {
        return new CancelamentoDto(
                cancelamento.getId(),
                cancelamento.getMotivo(),
                cancelamento.getDataCancelamento(),
                cancelamento.getValorMulta(),
                cancelamento.getReserva().getId()
        );
    }
}
