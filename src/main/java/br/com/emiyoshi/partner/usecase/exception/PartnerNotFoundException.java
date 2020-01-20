package br.com.emiyoshi.partner.usecase.exception;

import javax.persistence.EntityNotFoundException;

public class PartnerNotFoundException extends EntityNotFoundException {
    public PartnerNotFoundException() {
    }

    public PartnerNotFoundException(String message) {
        super(message);
    }
}
