package br.com.pubsub.network;

import br.com.pubsub.Client;
import br.com.pubsub.Util;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class S3 {
    public static void main(String[] args) throws RemoteException, java.net.UnknownHostException, MalformedURLException, NotBoundException {
        Client S3 = new Client("S3", "Milena", "I4", 1093);

        LocateRegistry.createRegistry(S3.getPort());
        Naming.rebind("S3", S3);
        S3.showStatus();
        S3.showSubscriberMenu("P1");
    }
}
