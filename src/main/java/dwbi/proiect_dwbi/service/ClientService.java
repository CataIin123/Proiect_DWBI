package dwbi.proiect_dwbi.service;

import dwbi.proiect_dwbi.exception.ResourceAlreadyExistsException;
import dwbi.proiect_dwbi.exception.ResourceNotFoundException;
import dwbi.proiect_dwbi.model.Client;
import dwbi.proiect_dwbi.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findByClientId(int client) {
        return clientRepository.findByClientId(client);
    }

    public Page<Client> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return clientRepository.findAll(pageable);
    }

    public Page<Client> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
        return clientRepository.findAll(pageable);
    }

    public void save(Client client) {
        Client storedClient = clientRepository.findByClientName(client.getClientName());
        if (storedClient != null) {
            throw new ResourceAlreadyExistsException("Client " + client.getClientName() + " already exists");
        }
        clientRepository.save(client);
    }

    @Transactional
    public Client update(Client client, int clientId) {
        Client storedClient = clientRepository.findByClientId(clientId);
        if (storedClient == null) {
            throw new ResourceNotFoundException("Client " + clientId + " not found");
        }
        Client storedClientForName = clientRepository.findByClientName(client.getClientName());
        if (storedClientForName != null && storedClientForName.getClientId() != clientId) {
            throw new ResourceAlreadyExistsException("Client " + client.getClientName() + " already exists");
        }
        return clientRepository.save(client);
    }

    @Transactional
    public void delete(int id) {
        clientRepository.deleteById(id);
    }
}
