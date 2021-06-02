/*
 * Made by Leandro Fernandez - 2021.
 */

package com.intercorp.exercise.repository;

import com.intercorp.exercise.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
