package com.picpaysimplificado.domain.user;
import com.picpaysimplificado.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
//Anotações Lombok que servem para que não precisamos criar os construtores padrões da classe
@AllArgsConstructor //Cria o construtor que necessita de todos os parâmetros
@NoArgsConstructor //Cria o construtor que necessita de nenhum dos parâmetros
@EqualsAndHashCode(of="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//JPA gerencia o id acumulativamente começando por 1
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique=true) //anotação para a coluna ser única
    private String document;
    @Column(unique=true)
    private String email;
    private String password;
    //saldo do usuário
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO data){
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.balance = data.balance();
        this.userType = data.userType();
        this.password = data.password();
        this.document = data.document();
        this.email = data.email();
    }
}
