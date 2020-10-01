package br.com.pubsub;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntermediaryInterface extends Remote {
    void subscribe(String subscriberId, String publisherId) throws RemoteException, NotBoundException, MalformedURLException;

    void publish(String publisherId) throws RemoteException, NotBoundException, MalformedURLException;

    String getId() throws RemoteException;
}
