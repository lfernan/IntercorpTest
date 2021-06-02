/*
 * Made by Leandro Fernandez - 2021.
 */

package com.intercorp.exercise.service;

import com.intercorp.exercise.dto.response.DeathDateResponse;
import com.intercorp.exercise.dto.response.KpiClientsResponse;
import com.intercorp.exercise.exception.KpiClientExceptionNotFound;
import com.intercorp.exercise.mapper.ClientMapper;
import com.intercorp.exercise.repository.ClientRepository;
import com.intercorp.exercise.util.MockUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    private static final Double DEVIATION = 1.87D;
    private static final Double OFFSET = 0.2D;
    private static final Integer AVERAGE = 3;
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private  ClientMapper clientMapper;

    @InjectMocks
    private ClientService clientService;

    /**
     * This test it's based on specific data to get always the same result
     */
    @Test
    public void testGetKpiClient() {
        when(this.clientRepository.findAll()).thenReturn(MockUtil.getClients());

        final KpiClientsResponse kpiClientsResponse = this.clientService.getKpiClient();

        assertThat(kpiClientsResponse.getAgeAverage()).isEqualTo(AVERAGE);
        assertThat(kpiClientsResponse.getStandardDeviation()).isCloseTo(DEVIATION, offset(OFFSET));
    }

    /**
     * This test should return a future date around 28 years later
     */
    @Test
    public void testGetClientListWithDD(){
        when(this.clientRepository.findAll()).thenReturn(Arrays.asList(MockUtil.getClient(-50)));
        when(this.clientMapper.mapToDeathDateDTO(any())).thenReturn(MockUtil.getDeathDateResponse(-50));
        final List<DeathDateResponse> deathDateResponses = this.clientService.getClientListWithDD();

        assertThat(deathDateResponses.get(0).getDeathDate().isAfter(LocalDate.now())).isTrue();
    }

    /**
     * This test has the propose to test the exception when is not data
     */
    @Test
    public void testKpiClientExceptionNotFound(){
        when(this.clientRepository.findAll()).thenReturn(Lists.emptyList());

        Throwable exception = assertThrows(KpiClientExceptionNotFound.class, () -> {
            this.clientService.getKpiClient();
        });
        assertEquals("No hay datos para realizar el calculo kpi", exception.getMessage());
    }
}
