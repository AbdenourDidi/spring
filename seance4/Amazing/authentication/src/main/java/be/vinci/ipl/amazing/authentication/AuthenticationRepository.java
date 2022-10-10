package be.vinci.ipl.amazing.authentication;

import be.vinci.ipl.amazing.authentication.models.Credentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends CrudRepository<Credentials, String> {
}
