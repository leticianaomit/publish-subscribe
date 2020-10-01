package br.com.pubsub.network;

import br.com.pubsub.Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class P1 {
    public static void main(String[] args) throws RemoteException, java.net.UnknownHostException, MalformedURLException, NotBoundException {
        Client P1 = new Client("P1", "Moda", "I1", 1092);

        LocateRegistry.createRegistry(P1.getPort());
        Naming.rebind("P1", P1);
        P1.showStatus();
        P1.showPublisherMenu();
    }
}
