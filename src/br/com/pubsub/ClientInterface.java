package br.com.pubsub;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    void notify(String publisherId) throws RemoteException, MalformedURLException, NotBoundException;

    String getName() throws RemoteException;
}