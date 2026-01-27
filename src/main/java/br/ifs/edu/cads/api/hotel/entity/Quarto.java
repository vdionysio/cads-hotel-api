package br.ifs.edu.cads.api.hotel.entity;

import br.ifs.edu.cads.api.hotel.enums.StatusQuarto;
import jakarta.persistence.*;

@Entity
@Table(
    name = "quarto",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_quarto_ap_bloco_andar",
            columnNames = {"num_apartamento", "num_bloco", "num_andar"}
        )
    }
)
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quarto")
    private Integer id;

    @Column(name = "num_apartamento", nullable = false)
    private Integer numQuarto;

    @Column(name = "num_bloco", nullable = false)
    private Integer numBloco;

    @Column(name = "num_andar", nullable = false)
    private Integer numAndar;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private CategoriaQuarto categoria;

    @Column(name = "status_quarto", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusQuarto statusQuarto;

    public Quarto() {
    }

    public Quarto(Integer numQuarto, Integer numBloco, Integer numAndar, StatusQuarto statusQuarto) {
        this.numQuarto = numQuarto;
        this.numBloco = numBloco;
        this.numAndar = numAndar;
        this.statusQuarto = statusQuarto;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumQuarto() {
        return numQuarto;
    }

    public Integer getNumBloco() {
        return numBloco;
    }

    public Integer getNumAndar() {
        return numAndar;
    }

    public CategoriaQuarto getCategoria() {
        return categoria;
    }

    public StatusQuarto getStatusQuarto() {
        return statusQuarto;
    }

    public void setCategoria(CategoriaQuarto categoria) {
        this.categoria = categoria;
    }
}
