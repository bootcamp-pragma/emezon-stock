package com.emezon.stock.infra.input.rest;

import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.app.dtos.category.CreateCategoryDTO;
import com.emezon.stock.app.services.CategoryService;
import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.constants.CategoryErrorMessages;
import com.emezon.stock.domain.constants.PaginatedResponseErrorMessages;
import com.emezon.stock.infra.constants.RestApiConstants;
import com.emezon.stock.infra.input.rest.controllers.CategoryController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private CreateCategoryDTO createCategoryDTO;
    private CategoryDTO categoryDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void createCategory_whenCategoryPropertiesAreValid_thenCategoryIsCreated() throws Exception {
        categoryDTO = new CategoryDTO();
        categoryDTO.setId("1234");
        categoryDTO.setName("Smartphones category");
        categoryDTO.setDescription("Smartphones category description");
        when(categoryService.createCategory(any())).thenReturn(categoryDTO);

        createCategoryDTO = new CreateCategoryDTO(
                categoryDTO.getName(),
                categoryDTO.getDescription()
        );
        mockMvc.perform(post(RestApiConstants.API_CATEGORY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCategoryDTO)))
                .andExpect(status().isCreated())
                        .andExpect(result -> {
                            CategoryDTO createdCategory = objectMapper.readValue(result.getResponse().getContentAsString(), CategoryDTO.class);
                            assert createdCategory != null;
                            assert createdCategory.getId().equals(categoryDTO.getId());
                            assert createdCategory.getName().equals(categoryDTO.getName());
                            assert createdCategory.getDescription().equals(categoryDTO.getDescription());
                        });

        verify(categoryService, times(1)).createCategory(any());
    }

    // Bad request test cases
    @Test
    void createCategory_whenCategoryNameIsBlank_thenBadRequest() throws Exception {
        createCategoryDTO = new CreateCategoryDTO(
                "",
                "Smartphones category description"
        );
        mockMvc.perform(post(RestApiConstants.API_CATEGORY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCategoryDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assert result.getResponse().getContentAsString().contains(CategoryErrorMessages.CATEGORY_NAME_REQUIRED);
                });

        verify(categoryService, never()).createCategory(any());
    }

    @Test
    void createCategory_whenCategoryDescriptionIsBlank_thenBadRequest() throws Exception {
        createCategoryDTO = new CreateCategoryDTO(
                "Smartphones category",
                ""
        );
        mockMvc.perform(post(RestApiConstants.API_CATEGORY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCategoryDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assert result.getResponse().getContentAsString().contains(CategoryErrorMessages.CATEGORY_DESCRIPTION_REQUIRED);
                });

        verify(categoryService, never()).createCategory(any());
    }

    @Test
    void createCategory_whenCategoryNameIsTooLong_thenBadRequest() throws Exception {
        createCategoryDTO = new CreateCategoryDTO(
                "asdnbasndfbsamdnfbsadfbasm,dnfbsm,dfbmsa,dfbsakdfbajskdjflsd fasdfas dfjksadf",
                "Smartphones category description"
        );
        mockMvc.perform(post(RestApiConstants.API_CATEGORY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCategoryDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assert result.getResponse().getContentAsString().contains(CategoryErrorMessages.CATEGORY_NAME_TOO_LONG);
                });

        verify(categoryService, never()).createCategory(any());
    }

    @Test
    void createCategory_whenCategoryDescriptionIsTooLong_thenBadRequest() throws Exception {
        createCategoryDTO = new CreateCategoryDTO(
                "Smartphones category",
                "asdnbasndfbsamdnfbsadfbasm,dnfbsm,dfbmsa,dfbsakdfbajskdjflsd fasdfas dfjksadf sadnf asdkfbaskdfasdfasd ajsdbhhjfbfsajdfbsamndfbsandfa sd fasdfasdf"
        );
        mockMvc.perform(post(RestApiConstants.API_CATEGORY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCategoryDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assert result.getResponse().getContentAsString().contains(CategoryErrorMessages.CATEGORY_DESCRIPTION_TOO_LONG);
                });

        verify(categoryService, never()).createCategory(any());
    }

    // Get all categories test cases
    @Test
    void getAllCategories_whenRequestParamsAreValid_thenCategoriesAreReturned() throws Exception {
        PaginatedResponse<CategoryDTO> categories = new PaginatedResponse<>();
        categories.setItems(List.of(
                new CategoryDTO("5678", "Laptops", "Laptops category"),
                new CategoryDTO("1234", "Smartphones", "Smartphones category"),
                new CategoryDTO("91011", "Tablets", "Tablets category")
        ));
        categories.setPage(1);
        categories.setSize(3);
        categories.setTotalItems(categories.getItems().size());
        when(categoryService.getAllCategories(any())).thenReturn(categories);

        mockMvc.perform(get(RestApiConstants.API_CATEGORY + "?page=1&size=3&sort=name,asc"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    PaginatedResponse<CategoryDTO> response = objectMapper.readValue(result.getResponse().getContentAsString(), PaginatedResponse.class);
                    assert response != null;
                    assert response.getItems().size() == categories.getItems().size();
                    assert response.getPage() == categories.getPage();
                    assert response.getSize() == categories.getSize();
                });

        verify(categoryService, times(1)).getAllCategories(any());
    }

    // Bad request test cases
    @Test
    void getAllCategories_whenPageParamIsInvalid_thenBadRequest() throws Exception {
        mockMvc.perform(get(RestApiConstants.API_CATEGORY + "?page=-1&size=3&sort=name,asc"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assert result.getResponse().getContentAsString()
                            .contains(PaginatedResponseErrorMessages.PAGE_NUMBER_INVALID);
                });

        verify(categoryService, never()).getAllCategories(any());
    }

    @Test
    void getAllCategories_whenSizeParamIsInvalid_thenBadRequest() throws Exception {
        mockMvc.perform(get(RestApiConstants.API_CATEGORY + "?page=1&size=0&sort=name,asc"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assert result.getResponse().getContentAsString()
                            .contains(PaginatedResponseErrorMessages.PAGE_SIZE_INVALID);
                });

        verify(categoryService, never()).getAllCategories(any());
    }

    @Test
    void getAllCategories_whenSortParamIsInvalid_thenBadRequest() throws Exception {
        mockMvc.perform(get(RestApiConstants.API_CATEGORY + "?page=1&size=3&sort=invalid-asc"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assert result.getResponse().getContentAsString()
                            .contains(PaginatedResponseErrorMessages.SORT_PARAM_INVALID);
                });

        verify(categoryService, never()).getAllCategories(any());
    }
}
