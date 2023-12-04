package com.picpaysimplificado.repositories;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dtos.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // indicação que é um arquivo de testes para o JPA
@ActiveProfiles("test")// indicação que vai usar o arquivo com test no nome
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager; //vem do JPA

    @Test
    @DisplayName("Should get User successfully from DB")// descrição para o teste unitário
    void findUserByDocumentCaseTrue() {// primeiro caso: achou o usuário criado no banco de dados
        String document= "9999999901";
        UserDTO data = new UserDTO("Lucas", "Teste", document, new BigDecimal(10), "test@gmail.com", "44444", UserType.COMMON);
        this.createUser(data);

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isEmpty()).isTrue();
    }


    @Test
    @DisplayName("Should not get User from DB when user not exists")
    void findUserByDocumentCaseEmpty() {//segundo caso: não achou o usuário criado no banco de dados
        String document = "99999999901";

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);// persiste no banco
        return newUser;
    }
}