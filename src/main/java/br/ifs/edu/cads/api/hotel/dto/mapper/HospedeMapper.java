package br.ifs.edu.cads.api.hotel.dto.mapper;

import br.ifs.edu.cads.api.hotel.dto.HospedeDto;
import br.ifs.edu.cads.api.hotel.dto.HospedeFormDto;
import br.ifs.edu.cads.api.hotel.entity.Hospede;
import org.springframework.stereotype.Component;

@Component
public class HospedeMapper {
    public HospedeDto toDto(Hospede hospede) {
        return new HospedeDto(
                hospede.getId(),
                hospede.getNome(),
                hospede.getCpf(),
                hospede.getDataNascimento(),
                hospede.getTelefone(),
                hospede.getCidade().getNome(),
                hospede.getCidade().getEstado().getUf()
        );
    }

    public Hospede formToEntity(HospedeFormDto hospedeFormDto) {
        return new Hospede(
                hospedeFormDto.nome(),
                hospedeFormDto.cpf(),
                hospedeFormDto.dataNascimento(),
                hospedeFormDto.telefone()
        );
    }
}
