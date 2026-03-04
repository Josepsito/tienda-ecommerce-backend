package com.deconfort.tienda;

import com.deconfort.tienda.usuarios.model.Rol;
import com.deconfort.tienda.usuarios.model.entity.Usuario;
import com.deconfort.tienda.usuarios.port.UsuarioDataPort;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.aop.auto=false")
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class TiendaApplicationTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioDataPort usuarioDataPort;

	@BeforeEach
	void setup() {
		Usuario usuario = new Usuario();
		usuario.setEmail("joseit@gmail.com");
		usuario.setPassword(passwordEncoder.encode("123456"));
		usuario.setNombres("Jose");
		usuario.setApellidos("Castillo");
		usuario.setRoles(List.of(Rol.ADMIN));
		usuarioDataPort.saveUsuario(usuario);
	}

	@Test
	void deberiaListarUsuarios() throws Exception {

		mockMvc.perform(get("/usuarios"))
				.andExpect(status().isOk());
	}

	@Test
	void deberiaLoguearUsuario() throws Exception {

		String body = """
                {
                  "email": "joseit@gmail.com",
                  "password": "123456"
                }
                """;

		mockMvc.perform(post("/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andDo(print())
				.andExpect(status().isOk());
	}
}