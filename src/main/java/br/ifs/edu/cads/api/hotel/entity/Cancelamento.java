package br.ifs.edu.cads.api.hotel.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cancelamento")
public class Cancelamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "motivo", nullable = false)
    private String motivo;

    @Column(name = "data_cancelamento", nullable = false)
    private LocalDateTime dataCancelamento;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    public Cancelamento() {

    }

    public Cancelamento(String motivo) {
        this.motivo = motivo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
