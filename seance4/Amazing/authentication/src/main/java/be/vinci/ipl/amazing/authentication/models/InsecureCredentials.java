package be.vinci.ipl.amazing.authentication.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InsecureCredentials {
    private String pseudo;
    private String password;

    public Credentials toCredentials(String hashedPassword) {
        return new Credentials(pseudo, hashedPassword);
    }
}
