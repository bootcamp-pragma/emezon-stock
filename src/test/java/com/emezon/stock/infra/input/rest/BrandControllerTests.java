package com.emezon.stock.infra.input.rest;

import com.emezon.stock.app.dtos.brand.BrandDTO;
import com.emezon.stock.app.dtos.brand.CreateBrandDTO;
import com.emezon.stock.app.services.BrandService;
import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.constants.BrandErrorMessages;
import com.emezon.stock.domain.constants.PaginatedResponseErrorMessages;
import com.emezon.stock.infra.constants.RestApiConstants;
import com.emezon.stock.infra.input.rest.controllers.BrandController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BrandController.class)
class BrandControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    private CreateBrandDTO createBrandDTO;
    private BrandDTO brandDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void createBrand_whenBrandPropertiesAreValid_shouldReturnCreated() throws Exception {
        brandDTO = new BrandDTO("123", "Nike", "Just do it");
        when(brandService.createBrand(any())).thenReturn(brandDTO);

        createBrandDTO = new CreateBrandDTO(
                brandDTO.getName(),
                brandDTO.getDescription());

        mockMvc.perform(post(RestApiConstants.API_BRAND)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createBrandDTO)))
                .andExpect(status().isCreated());

        verify(brandService, times(1)).createBrand(any());
    }

    @Test
    void createBrand_whenBrandNameIsBlank_shouldReturnBadRequest() throws Exception {
        createBrandDTO = new CreateBrandDTO(
                "",
                "Just do it");

        mockMvc.perform(post(RestApiConstants.API_BRAND)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createBrandDTO)))
                .andExpect(status().isBadRequest())
                        .andExpect(result -> {
                            String response = result.getResponse().getContentAsString();
                            assert(response.contains(BrandErrorMessages.BRAND_NAME_REQUIRED));
                        });

        verify(brandService, never()).createBrand(any());
    }

    @Test
    void createBrand_whenBrandDescriptionIsBlank_shouldReturnBadRequest() throws Exception {
        createBrandDTO = new CreateBrandDTO(
                "Nike",
                "");

        mockMvc.perform(post(RestApiConstants.API_BRAND)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createBrandDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    assert(response.contains(BrandErrorMessages.BRAND_DESCRIPTION_REQUIRED));
                });

        verify(brandService, never()).createBrand(any());
    }

    @Test
    void createBrand_whenBrandNameIsTooLong_shouldReturnBadRequest() throws Exception {
        createBrandDTO = new CreateBrandDTO(
                "alksdjnasdf sadfaskldfjnaskdfas dfasdkfas dfas dfasdf asdfasdfasfdasd faas",
                "Just do it");

        mockMvc.perform(post(RestApiConstants.API_BRAND)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createBrandDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    assert (response.contains(BrandErrorMessages.BRAND_NAME_TOO_LONG));
                });

        verify(brandService, never()).createBrand(any());
    }

    @Test
    void createBrand_whenBrandDescriptionIsTooLong_shouldReturnBadRequest() throws Exception {
        createBrandDTO = new CreateBrandDTO(
                "Nike",
                "alksdjnasdf sadfaskldfjnaskdfas dfasdkfas dfas dfasdf asdfasdfasfdasd akdsjsadhfa sdfkashdfkjasdhkf" +
                        "kjasdkjhaksjdhfkasjdhf dasfskdfmaskdfjhskdjf asdkfjasdkjfhs faas");

        mockMvc.perform(post(RestApiConstants.API_BRAND)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createBrandDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    assert (response.contains(BrandErrorMessages.BRAND_DESCRIPTION_TOO_LONG));
                });

        verify(brandService, never()).createBrand(any());
    }

    @Test
    void getAllBrands_whenRequestParamsAreValid_thenBrandsAreReturned() throws Exception {
        PaginatedResponse<BrandDTO> brands = new PaginatedResponse<>();
        brands.setItems(List.of(
                new BrandDTO("124", "Adidas", "Impossible is nothing"),
                new BrandDTO("123", "Nike", "Just do it"),
                new BrandDTO("125", "Puma", "Forever faster")
        ));
        when(brandService.getAllBrands(any())).thenReturn(brands);
        brands.setPage(1);
        brands.setSize(3);
        brands.setTotalItems(brands.getItems().size());

        mockMvc.perform(get(RestApiConstants.API_BRAND + "?page=1&size=3&sort=name,asc")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    PaginatedResponse<BrandDTO> response = objectMapper.readValue(result.getResponse().getContentAsString(), PaginatedResponse.class);
                    assert(response.getItems().size() == brands.getItems().size());
                    assert(response.getPage() == brands.getPage());
                    assert(response.getSize() == brands.getSize());
                    assert(response.getTotalItems() == brands.getTotalItems());
                });

        verify(brandService, times(1)).getAllBrands(any());
    }

    @Test
    void getAllBrands_whenPageParamIsInvalid_thenBadRequestIsReturned() throws Exception {
        mockMvc.perform(get(RestApiConstants.API_BRAND + "?page=-1&size=3&sort=name,asc")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    assert(response.contains(PaginatedResponseErrorMessages.PAGE_NUMBER_INVALID));
                });
        verify(brandService, never()).getAllBrands(any());
    }

    @Test
    void getAllBrands_whenSizeParamIsInvalid_thenBadRequestIsReturned() throws Exception {
        mockMvc.perform(get(RestApiConstants.API_BRAND + "?page=1&size=0&sort=name,asc")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    assert(response.contains(PaginatedResponseErrorMessages.PAGE_SIZE_INVALID));
                });
        verify(brandService, never()).getAllBrands(any());
    }

    @Test
    void getAllBrands_whenSortParamIsInvalid_thenBadRequestIsReturned() throws Exception {
        mockMvc.perform(get(RestApiConstants.API_BRAND + "?page=1&size=3&sort=invalid,")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    assert(response.contains(PaginatedResponseErrorMessages.SORT_PARAM_INVALID));
                });
        verify(brandService, never()).getAllBrands(any());
    }

}
