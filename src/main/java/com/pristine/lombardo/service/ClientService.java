package com.pristine.lombardo.service;

import com.pristine.lombardo.dto.ClientDTO;
import com.pristine.lombardo.entity.Client;
import com.pristine.lombardo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Transactional
    public Client createClient(ClientDTO clientDTO) {
        Client client = new Client(
                clientDTO.getName(),
                clientDTO.getTel(),
                0F,
                null);

        return clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public Client findById(Long id) throws NoSuchElementException {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client with ID: " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<Client> findByTel(String tel) {
        return clientRepository.findByTel(tel);
    }

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) throws NoSuchElementException {
        if (!clientRepository.existsById(id)) {
            throw new NoSuchElementException("Client with ID: " + id + " not found");
        }
        clientRepository.deleteById(id);
    }
}
