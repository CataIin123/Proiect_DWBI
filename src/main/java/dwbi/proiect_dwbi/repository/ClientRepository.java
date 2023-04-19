package dwbi.proiect_dwbi.repository;

import dwbi.proiect_dwbi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByClientName(String clientName);

    Client findByClientId(int clientId);
}
