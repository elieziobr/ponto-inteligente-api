package com.curso.pontointeligente.api.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Eliezio.Soares
 */
class PasswordUtilsTest {

    private static final String SENHA = "123456";
    private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();

    @Test
    void testSenhaNula() throws Exception {
        Assertions.assertNull(PasswordUtils.gerarBCrypt(null));
    }

    @Test
    public void testGerarHashSenha() throws Exception {
        String hash = PasswordUtils.gerarBCrypt(SENHA);
        Assertions.assertTrue(bCryptEncoder.matches(SENHA, hash));
    }
}
