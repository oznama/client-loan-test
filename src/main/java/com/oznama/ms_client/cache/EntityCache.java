package com.oznama.ms_client.cache;

import com.oznama.ms_client.constants.LoanStatus;
import com.oznama.ms_client.model.ClientEntity;
import com.oznama.ms_client.model.LoanEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EntityCache {
    /**
     * Lista statica para persistir clientes
     */
    private static final List<ClientEntity> CLIENTS = new ArrayList<>();

    /**
     * Lista estatica para prestamos de clientes
     */
    private static final List<LoanEntity> LOANS = new ArrayList<>();

    /**
     * Metodo estatico para devolver la lista de clientes
     * @return Lista de clientes
     */
    public static List<ClientEntity> getClients() {
        return CLIENTS;
    }

    /**
     * Metodo estatico para devolver la lista de prestamos activos
     * @return Lista de prestamos
     */
    public static List<LoanEntity> getLoans() {
        return LOANS.stream().filter(l -> l.getStatus().equals(LoanStatus.PENDING)).toList();
    }

    /**
     * Metodo para agregar entidad a la lista
     * @param entity entidad
     * @return bandera con afirmacion de la operacion
     */
    public static boolean add(Object entity) {
        try {
            if (entity instanceof ClientEntity client) {
                log.debug("Adding entity {} - {}", client.getId(), client.getName());
                CLIENTS.add(client);
                log.debug("Client {} - {} added successful", client.getId(), client.getName());
            } else if (entity instanceof LoanEntity loan ) {
                log.debug("Adding loanEntity {} - {} - {}", loan.getId(), loan.getClientId(), loan.getAmount());
                LOANS.add(loan);
                log.debug("Loan Client {} - {} added successful", loan.getId(), loan.getClientId());
            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("Error adding entity", e);
            return false;
        }
    }

    /**
     * Metodo para actualizar entidad de la lista por id
     * @param entity datos a actualizar
     * @return bandera con afirmacion de la operacion
     */
    public static boolean update(Object entity) {
        try {
            if (entity instanceof ClientEntity client) {
                log.debug("Updating client {} - {}", client.getId(), client.getName());
                CLIENTS.stream()
                        .filter(c -> c.getId().equals(client.getId()))
                        .findFirst()
                        .ifPresent(c -> {
                            c.setName(client.getName());
                            c.setAge(client.getAge());
                            c.setEmail(client.getEmail());
                            c.setType(client.getType());
                        });
                log.debug("Client {} - {} updated successful", client.getId(), client.getName());
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("Error updating entity", e);
            return false;
        }
    }

    /**
     * Metodo para actualizar el estatus de un prestamo por id
     * @param loanId del prestamo a actualizar
     * @param loanStatus estatus nuevo
     * @return bandera con afirmacion de la operacion
     */
    public static boolean updateLoanStatus(String loanId, LoanStatus loanStatus) {
        log.debug("Updating loan status for {} - {}", loanId, loanStatus);
        try {
            LOANS.stream()
                    .filter(l -> l.getId().equals(loanId))
                    .findFirst()
                    .ifPresent(l -> l.setStatus(loanStatus));
            log.debug("Loan Status for {} - {}", loanId, loanStatus);
            return true;
        } catch (Exception e) {
            log.error("Error updating loan status", e);
            return false;
        }
    }

    /**
     * Metodo para eliminar un cliente de la lista por id
     * @param entity a eliminar
     * @return bandera con confirmacion de la operacion
     */
    public static boolean delete(Object entity) {
        try {
            if (entity instanceof ClientEntity client) {
                log.debug("Deleting client with {}", client.getId());
                CLIENTS.removeIf(c -> c.getId().equals(client.getId()));
                log.debug("Client with id {} deleted successful", client.getId());
            } else if (entity instanceof LoanEntity loan) {
                log.debug("Deleting loan with {}", loan.getId());
                LOANS.removeIf(l -> l.getId().equals(loan.getId()));
                log.debug("Loan Client with id {} deleted successful", loan.getId());
            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("Error deleting entity", e);
            return false;
        }
    }
}
