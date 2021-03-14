package fr.auth.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import fr.auth.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data 
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor @AllArgsConstructor 
@Entity
public class User extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lastName;
    private String firstName;
    @Email(message = "address email not valid")
    private String email;
    @Size(min = 6, message = "password must be contains min 6" )
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
