package br.ifs.edu.cads.api.hotel.entity;

import br.ifs.edu.cads.api.hotel.enums.StatusQuarto;
import jakarta.persistence.*;

@Entity
@Table(name = "quarto")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quarto")
    private Integer id;

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

    public Integer getId() {
        return id;
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
}
