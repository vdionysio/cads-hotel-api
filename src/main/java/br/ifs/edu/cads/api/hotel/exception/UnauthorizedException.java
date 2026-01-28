package br.ifs.edu.cads.api.hotel.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Usuário não autorizado.");
    }
}
