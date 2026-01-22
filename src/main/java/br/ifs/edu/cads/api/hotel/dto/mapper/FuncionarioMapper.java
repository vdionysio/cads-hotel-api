package br.ifs.edu.cads.api.hotel.dto.mapper;

import br.ifs.edu.cads.api.hotel.dto.FuncionarioDto;
import br.ifs.edu.cads.api.hotel.dto.FuncionarioFormDto;
import br.ifs.edu.cads.api.hotel.entity.Funcionario;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {
    public FuncionarioDto toDto(Funcionario funcionario) {
        return new FuncionarioDto(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getCargo(),
                funcionario.getUsuario().getEmail()
        );
    }

    public Funcionario formToEntity(FuncionarioFormDto funcionarioFormDto) {

        return new Funcionario(
                funcionarioFormDto.nome(),
                funcionarioFormDto.cpf(),
                funcionarioFormDto.cargo()
        );
    }
}
