package com.oznama.ms_client.cache;

import com.oznama.ms_client.ClientEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ClientsCache {
    /**
     * Lista statica para persistir clientes
     */
    private static final List<ClientEntity> CLIENTS = new ArrayList<>();

    public static List<ClientEntity> getClients() {
        return CLIENTS;
    }

    /**
     * Metodo para agregar clientes a la lista
     * @param client entidad
     * @return bandera con afirmacion de la operacion
     */
    public static boolean add(ClientEntity client) {
        log.debug("Adding client {} - {}", client.getId(), client.getName());
        try {
            CLIENTS.add(client);
            log.debug("Client {} - {} added successful", client.getId(), client.getName());
            return true;
        } catch (Exception e) {
            String msgError = String.format("Error adding client %s - %s", client.getId(), client.getName());
            log.error(msgError, e);
            return false;
        }
    }

    /**
     * Metodo para actualizar cliente de la lista por id
     * @param client datos a actualizar
     * @return bandera con afirmacion de la operacion
     */
    public static boolean update(ClientEntity client) {
        log.debug("Updating client {} - {}", client.getId(), client.getName());
        try {
            CLIENTS.stream()
                    .filter(c -> c.getId().equals(client.getId()))
                    .findFirst()
                    .ifPresent(c -> {
                        log.debug("Updating client {} to {}", c, client);
                        c.setName(client.getName());
                        c.setType(client.getType());
                        c.setEmail(client.getEmail());
                        c.setType(client.getType());
                    });
            log.debug("Client {} - {} updated successful", client.getId(), client.getName());
            return true;
        } catch (Exception e) {
            String msgError = String.format("Error to update client %s - %s", client.getId(), client.getName());
            log.error(msgError, e);
            return false;
        }
    }

    /**
     * Metodo para eliminar un cliente de la lista por id
     * @param id del cliente a eliminar
     * @return bandera con confirmacion de la operacion
     */
    public static boolean delete(String id) {
        log.debug("Deleting client with {}", id);
        try {
            CLIENTS.removeIf(c -> c.getId().equals(id));
            log.debug("Client with id {} deleted successful", id);
            return true;
        } catch (Exception e) {
            String msgError = String.format("Error to delete client with id %s", id);
            log.error(msgError, e);
            return false;
        }
    }
}
