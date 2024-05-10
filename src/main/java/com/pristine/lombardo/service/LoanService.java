package com.pristine.lombardo.service;

import com.pristine.lombardo.addition.LoanStatus;
import com.pristine.lombardo.dto.LoanDTO;
import com.pristine.lombardo.entity.Client;
import com.pristine.lombardo.entity.Loan;
import com.pristine.lombardo.entity.Property;
import com.pristine.lombardo.repository.ClientRepository;
import com.pristine.lombardo.repository.LoanRepository;
import com.pristine.lombardo.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PropertyRepository propertyRepository;


    @Transactional
    public Loan createLoan(LoanDTO loanDTO) throws NoSuchElementException {
        Client client = clientRepository.findById(loanDTO.getClientId())
                .orElseThrow(() ->
                        new NoSuchElementException("Client with ID: " + loanDTO.getClientId() + " not found"));
        Property property = propertyRepository.findById(loanDTO.getPropertyId())
                .orElseThrow(() ->
                        new NoSuchElementException("Property with ID: " + loanDTO.getPropertyId() + " not found"));

        Loan loan = new Loan(
                client,
                property,
                Date.from(Instant.now()),
                Date.from(Instant.now().plus(30, ChronoUnit.DAYS)),
                LoanStatus.OPENED);
        return loanRepository.save(loan);
    }

    @Transactional(readOnly = true)
    public Loan findById(Long id) throws NoSuchElementException {
        return loanRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Loan with ID: " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<Loan> findByStatus(LoanStatus status) {
        return loanRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) throws NoSuchElementException {
        if (!loanRepository.existsById(id)) {
            throw new NoSuchElementException("Loan with ID: " + id + " not found");
        }
        loanRepository.deleteById(id);
    }
}
