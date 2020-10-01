package br.com.pubsub;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Util {
    public static IntermediaryInterface getIntermediaryById(String id) throws RemoteException, NotBoundException, MalformedURLException {
        return (IntermediaryInterface) Naming.lookup(id);
    }

    public static ClientInterface getClientById(String id) throws RemoteException, NotBoundException, MalformedURLException {
        return (ClientInterface) Naming.lookup(id);
    }
}
