package io.zipcoder.Handlers;

import io.zipcoder.Player;

public abstract class Handler {
	
	protected Player player;

	public Handler(Player player){
		this.player = player;
	}

	public abstract Player getPlayer();
	
	
	
	

}
