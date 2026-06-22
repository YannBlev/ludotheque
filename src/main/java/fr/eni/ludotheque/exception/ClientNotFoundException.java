package fr.eni.ludotheque.exception;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ClientNotFoundException extends RuntimeException {

    @NonNull private Long idClient;

    @Override
    public String getMessage() {
        return "Client " + idClient + " n'existe pas";
    }
}
