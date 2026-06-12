package com.oznama.ms_client.cache;

import com.oznama.ms_client.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientsCache {
    /**
     * Lista statica para persistir clientes
     */
    public static final List<ClientEntity> CLIENTS = new ArrayList<>();

    /**
     * Metodo para agregar clientes a la lista
     * @param client entidad
     * @return bandera con afirmacion de la operacion
     */
    public static boolean add(ClientEntity client) {
        try {
            CLIENTS.add(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Metodo para actualizar cliente de la lista por id
     * @param client datos a actualizar
     * @return bandera con afirmacion de la operacion
     */
    public static boolean update(ClientEntity client) {
        try {
            CLIENTS.stream()
                    .filter(c -> c.getId().equals(client.getId()))
                    .findFirst()
                    .ifPresent(c -> {
                        c.setName(client.getName());
                        c.setType(client.getType());
                        c.setEmail(client.getEmail());
                        c.setType(client.getType());
                    });

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Metodo para eliminar un cliente de la lista por id
     * @param id del cliente a eliminar
     * @return bandera con confirmacion de la operacion
     */
    public static boolean delete(String id) {
        try {
            CLIENTS.removeIf(c -> c.getId().equals(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
