package com.cs451.checkers;

public class PingNetworkManager extends NormalNetworkManager {
    private static PingNetworkManager instance;

	public PingNetworkManager(){
		super();
	}
	
    public static PingNetworkManager getInstance() {
        if (instance == null) instance = new PingNetworkManager();
        return instance;
    }
}