package br.com.pubsub;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Intermediary extends UnicastRemoteObject implements IntermediaryInterface {
    private String id;
    private Map<String, Set<String>> inscriptions;
    private Map<String, Set<String>> routing;
    private Set<String> neighbors;
    private Integer port;

    public Intermediary(String id, Integer port) throws UnknownHostException, RemoteException {
        super();
        this.id = id;
        this.port = port;
        this.inscriptions = new HashMap<>();
        this.routing = new HashMap<>();
        this.neighbors = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public Integer getPort() {
        return port;
    }

    public void addNeighbor(String neighborId) {
        this.neighbors.add(neighborId);
    }

    public void addInscription(String publisherId, String subscriberId) {
        if (!this.inscriptions.containsKey(publisherId))
            this.inscriptions.put(publisherId, new HashSet<>());
        this.inscriptions.get(publisherId).add(subscriberId);
    }

    public void addRouting(String publisherId, String subscriberId) {
        if (!this.routing.containsKey(publisherId))
            this.routing.put(publisherId, new HashSet<>());
        this.routing.get(publisherId).add(subscriberId);
    }

    private boolean isIntermediary(String id) {
        return id.contains("I");
    }

    public void subscribe(String subscriberId, String publisherId) throws RemoteException, NotBoundException, MalformedURLException {
        if (isIntermediary(subscriberId))
            this.addRouting(publisherId, subscriberId);
        else
            this.addInscription(publisherId, subscriberId);

        for (String neighborId : this.neighbors) {
            if (!isIntermediary(neighborId) || neighborId.equals(publisherId) || neighborId.equals(subscriberId))
                continue;
            IntermediaryInterface neighbor = Util.getIntermediaryById(neighborId);
            neighbor.subscribe(this.id, publisherId);
        }

        System.out.println(String.format("%s", "******************************"));
        this.showInscriptionsTable();
        this.showRoutingTable();
        System.out.println(String.format("%s", "******************************"));
    }

    public void publish(String publisherId) throws RemoteException, NotBoundException, MalformedURLException {
        Set<String> inscriptions = this.inscriptions.getOrDefault(publisherId, new HashSet<>());
        if (inscriptions.isEmpty()) {
            Set<String> routing = this.routing.getOrDefault(publisherId, new HashSet<>());
            for (String router : routing) {
                if (!isIntermediary(router)) continue;
                IntermediaryInterface intermediary = Util.getIntermediaryById(router);
                intermediary.publish(publisherId);
            }
        } else {
            for (String inscription : inscriptions) {
                ClientInterface subscriber = Util.getClientById(inscription);
                subscriber.notify(publisherId);
            }
        }
    }

    public void showTable(Map<String, Set<String>> list) {
        System.out.println(String.format("%2s %21s %2s", "", "------------------------", ""));
        System.out.println(String.format("%2s %10s %1s %10s %2s", "|", "Assunto", "|", "Assinante", "|"));
        for (Map.Entry<String, Set<String>> inscription : list.entrySet()) {
            for (String subscriber : inscription.getValue()) {
                System.out.println(String.format("%2s %10s %1s %10s %2s", "|", inscription.getKey(), "|", subscriber, "|"));
            }
        }
        System.out.println(String.format("%2s %21s %2s", "", "------------------------", ""));
    }

    public void showInscriptionsTable() {
        System.out.println(String.format("%s", "Inscrições"));
        this.showTable(this.inscriptions);
    }

    public void showRoutingTable() {
        System.out.println(String.format("%s", "Roteamento"));
        this.showTable(this.routing);
    }

    public void showStatus() throws RemoteException {
        System.out.println("Intermediário " + this.getId() + " executando na porta: " + this.getPort());
    }
}
