package com.spring.login;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spring.login.controllers.ContactRestController;
import com.spring.login.models.Contact;
import com.spring.login.repository.ContactRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactRestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ContactRestController contactController;

    @Mock
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    @Test
    @WithMockUser(roles = "USER") // Simulate the user role
    public void testGetMessagesByStatus() throws Exception {
        // Arrange
        String status = "Open";
        Contact contact1 = new Contact();
        contact1.setEmail("test1@email.com");
        Contact contact2 = new Contact();
        contact2.setEmail("test2@email.com");
        List<Contact> contacts = Arrays.asList(contact1, contact2);

        when(contactRepository.findByStatus(status)).thenReturn(contacts);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/contact/getMessagesByStatus")
                .param("status", status)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].email").value("test1@email.com"));
  
    }
}
