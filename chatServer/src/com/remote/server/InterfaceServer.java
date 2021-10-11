package com.remote.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import com.remote.client.InterfaceClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public interface InterfaceServer extends Remote{

    void broadcastMessage(String message,List<String> list) throws RemoteException;

    void broadcastMessage(ArrayList<Integer> inc,List<String> list,String filename) throws RemoteException;

    Vector<String> getListClientByName(String name) throws RemoteException;

    void addClient(InterfaceClient client) throws RemoteException;

    void blockClient(List<String> clients) throws RemoteException;

    void removeClient(List<String> clients) throws RemoteException;

    void removeClient(String clients) throws RemoteException;

    void reactiveClient(List<String> clients) throws RemoteException;

    boolean checkUsername(String username) throws RemoteException;
}