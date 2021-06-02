/*
 * Made by Leandro Fernandez - 2021.
 */

package com.intercorp.exercise.service;

import com.intercorp.exercise.dto.request.ClientRequest;
import com.intercorp.exercise.dto.response.ClientResponse;
import com.intercorp.exercise.dto.response.DeathDateResponse;
import com.intercorp.exercise.dto.response.KpiClientsResponse;
import com.intercorp.exercise.exception.KpiClientExceptionNotFound;
import com.intercorp.exercise.mapper.ClientMapper;
import com.intercorp.exercise.model.Client;
import com.intercorp.exercise.repository.ClientRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final Integer ESTIMATED_YEAR_OF_LIFE = 78;
    private static final String ERR_MSG = "No hay datos para realizar el calculo kpi";

    @Autowired
    public ClientService(final ClientRepository clientRepository, final ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    /**
     * Method to create a new Client from a ClientDTO
     *
     * @param clientRequest
     * @return ClientResponse
     */
    public ClientResponse createClient(final ClientRequest clientRequest) {
        log.info(String.format("Creating new Client with name = %s , lastname = %s, dateOfBirth = %s", clientRequest.getName(), clientRequest.getLastname(),
                clientRequest.getDateOfBirth().toString()));
        final Client client = this.clientRepository.save(clientMapper.mapToClientEntity(clientRequest));
        return this.clientMapper.mapToClientDTO(client);
    }

    /**
     * Method to calculate average and standard deviation from age of clients
     *
     * @return KpiClientsResponse
     */
    @SneakyThrows
    public KpiClientsResponse getKpiClient() {
        log.info("Calculate Kpi");
        final List<Client> clientList = this.clientRepository.findAll();
        if(clientList.isEmpty()){
            throw new KpiClientExceptionNotFound(ERR_MSG);
        }
        final List<Integer> ages = clientList.stream()
                .map(Client::getDateOfBirth)
                .map(this::getYearFromBirthday)
                .collect(Collectors.toList());

        final Integer average = ages.stream()
                .mapToInt(Integer::intValue)
                .sum() / ages.size();

        final Double variance = ages.stream()
                .map(y -> Math.pow(y - average, 2))
                .mapToDouble(Double::doubleValue)
                .sum();

        final KpiClientsResponse kpiClientsResponse = new KpiClientsResponse();
        kpiClientsResponse.setAgeAverage(average);
        kpiClientsResponse.setStandardDeviation(Math.sqrt((variance / clientList.size())));
        log.info(String.format("Kpi Client -> %s", kpiClientsResponse.toString()));
        return kpiClientsResponse;

    }

    /**
     * Method to get all clients with death date
     *
     * @return List<DeathDateResponse>
     */
    public List<DeathDateResponse> getClientListWithDD() {
        log.info("Get clients with estimated death date");
        return this.clientRepository.findAll().stream()
                .map(this.clientMapper::mapToDeathDateDTO)
                .peek(dd -> dd.setDeathDate(getDeathDate(dd.getDateOfBirth())))
                .collect(Collectors.toList());
    }

    /**
     * Method to get years from date of birthday
     *
     * @param birthday
     * @return years
     */
    private int getYearFromBirthday(final LocalDate birthday) {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    /**
     * Method to calculate a possible dead date
     *
     * @param birthDate
     * @return
     */
    private LocalDate getDeathDate(final LocalDate birthDate) {
        int clientYearsOld = getYearFromBirthday(birthDate);
        int year = 0, bound = 0, origin = 0;
        if (clientYearsOld < ESTIMATED_YEAR_OF_LIFE) {
            origin = ESTIMATED_YEAR_OF_LIFE - clientYearsOld;
        } else {
            origin = ThreadLocalRandom.current().nextInt((ESTIMATED_YEAR_OF_LIFE - clientYearsOld),(ESTIMATED_YEAR_OF_LIFE - clientYearsOld) + 1);
        }
        bound = origin + 1;
        int month = ThreadLocalRandom.current().nextInt(1, 12);
        int day = ThreadLocalRandom.current().nextInt(1, month == 2 ? 28 : 30);
        year = ThreadLocalRandom.current().nextInt(LocalDate.now().plusYears(origin).getYear(), LocalDate.now().plusYears(bound).getYear());
        return LocalDate.of(year, month, day);
    }
}
