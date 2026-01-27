package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.entity.Quarto;
import br.ifs.edu.cads.api.hotel.entity.Reserva;

public record QuartoReservaDto(
        Quarto quarto,
        Reserva reserva
) {
}
