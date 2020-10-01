package br.com.pubsub.network;

import br.com.pubsub.Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class P2 {
    public static void main(String[] args) throws RemoteException, java.net.UnknownHostException, MalformedURLException, NotBoundException {
        Client P2 = new Client("P2", "Política", "I3", 1091);

        LocateRegistry.createRegistry(P2.getPort());
        Naming.rebind("P2", P2);
        P2.showStatus();
        P2.showPublisherMenu();
    }
}
