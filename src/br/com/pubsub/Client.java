package br.com.pubsub;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements ClientInterface {
    private String id;
    private String name;
    private Integer port;
    private String intermediaryId;

    public Client(String id, String name, String intermediaryId, Integer port) throws RemoteException {
        super();
        this.id = id;
        this.name = name;
        this.port = port;
        this.intermediaryId = intermediaryId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPort() {
        return port;
    }

    public void registerSubscriber(String publisherId, String publisherName) throws RemoteException, NotBoundException, MalformedURLException, UnknownHostException {
        IntermediaryInterface intermediary = Util.getIntermediaryById(this.intermediaryId);
        System.out.println(this.name + " está assinando " + publisherName);
        intermediary.subscribe(this.id, publisherId);
    }

    public void sendNotification() throws RemoteException, NotBoundException, MalformedURLException, UnknownHostException {
        IntermediaryInterface intermediary = Util.getIntermediaryById(this.intermediaryId);
        System.out.println(this.name + " enviando notificação");
        intermediary.publish(this.id);
    }

    @Override
    public void notify(String publisherId) throws RemoteException, MalformedURLException, NotBoundException {
        ClientInterface publisher = Util.getClientById(publisherId);
        System.out.println(String.format("%s recebeu notificação de %s", this.name, publisher.getName()));
    }

    public void showStatus() throws RemoteException {
        System.out.println("Executando " + this.getName() + " (" + this.getId() + ")");
    }

    public void showPublisherMenu() throws RemoteException, UnknownHostException, NotBoundException, MalformedURLException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Digite 1 para enviar uma notificação : ");
            if (sc.nextInt() == 1)
                this.sendNotification();
        }
    }

    public void showSubscriberMenu(String publisherId) throws RemoteException, UnknownHostException, NotBoundException, MalformedURLException {
        ClientInterface publisher = Util.getClientById(publisherId);
        Scanner sc = new Scanner(System.in);
        Boolean aux;
        do {
            System.out.print("Digite 1 para assinar " + publisher.getName() + ": ");
            aux = (sc.nextInt() != 1);
        } while (aux);
        this.registerSubscriber(publisherId, publisher.getName());
    }
}
