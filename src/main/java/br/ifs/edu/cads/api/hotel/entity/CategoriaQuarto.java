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

    @Enumerated(EnumType.STRING)
    @Column(name = "posicao_quarto")
    private PosicaoQuarto posicaoQuarto;

    @OneToMany(mappedBy = "categoria")
    private List<Quarto> quartos;

    @OneToMany
    private List<Comodidade> comodidades;

    public CategoriaQuarto(String nome, String descricao, Integer maxHospedes, BigDecimal valorDiaria, PosicaoQuarto posicaoQuarto) {
        this.nome = nome;
        this.descricao = descricao;
        this.maxHospedes = maxHospedes;
        this.valorDiaria = valorDiaria;
        this.posicaoQuarto = posicaoQuarto;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getMaxHospedes() {
        return maxHospedes;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public PosicaoQuarto getPosicaoQuarto() {
        return posicaoQuarto;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public List<Comodidade> getComodidades() {
        return comodidades;
    }

    public void setComodidades(List<Comodidade> comodidades) {
        this.comodidades = comodidades;
    }
}
