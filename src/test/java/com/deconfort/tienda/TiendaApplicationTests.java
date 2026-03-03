package com.deconfort.tienda;

import com.deconfort.tienda.security.port.UsuarioAuthPort;
import com.deconfort.tienda.usuarios.model.Rol;
import com.deconfort.tienda.usuarios.model.entity.Usuario;
import com.deconfort.tienda.usuarios.port.UsuarioDataPort;
import com.deconfort.tienda.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
@WithMockUser(username = "admin", roles = {"ADMIN"})
class TiendaApplicationTests {

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
				.andExpect(status().isOk());
	}
}