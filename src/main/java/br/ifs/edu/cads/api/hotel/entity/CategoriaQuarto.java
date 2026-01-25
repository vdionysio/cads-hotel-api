package br.ifs.edu.cads.api.hotel.entity;

import br.ifs.edu.cads.api.hotel.enums.PosicaoQuarto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "categoria_quarto")
public class CategoriaQuarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id;

    @Column(name = "nome_categoria", nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao_categoria", nullable = false)
    private String descricao;

    @Column(name = "max_hospedes", nullable = false)
    private Integer maxHospedes;

    @Column(name = "valor_diaria", nullable = false)
    private BigDecimal valorDiaria;

    @Column(name = "posicao_quarto")
    private PosicaoQuarto posicaoQuarto;

    @OneToMany(mappedBy = "categoria")
    private List<Quarto> quartos;

    @OneToMany
    private List<Comodidade> comodidades;
}
