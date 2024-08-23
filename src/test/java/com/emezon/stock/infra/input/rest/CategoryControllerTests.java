package com.emezon.stock.infra.input.rest;
import com.emezon.stock.app.services.CategoryService;
import com.emezon.stock.domain.ports.output.ICategoryRepositoryOutPort;
import com.emezon.stock.infra.input.rest.controllers.PingController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PingController.class)
public class CategoryControllerTests {

}
