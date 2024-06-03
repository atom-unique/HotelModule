package org.kravchenko.hotel.repository;

import org.kravchenko.hotel.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findClientByNameAndSurname(String name, String surname);

}
