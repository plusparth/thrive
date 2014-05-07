package com.ThreeCheesePasta.ThriveGame.world;

public class TileHandler {
	//Create, register tiles
	public Tile tileDirt = new Tile();
	public Tile tileGrass = new Tile();
	public Tile tileStone = new Tile();
	
	public void setTileProperties() {
		
		//Set display names
		this.tileDirt.displayName = "Dirt";
		this.tileGrass.displayName = "Grassy Dirt";
		this.tileStone.displayName = "Stone";
		
		//Set tile hardness
		this.tileDirt.hardness = 0.01f;
		this.tileGrass.hardness = tileDirt.hardness;
		this.tileStone.hardness = 0.1f;	
		
		this.tileDirt.id = 1;
		this.tileGrass.id = 2;
		this.tileStone.id = 0;
	}
	
	
	//TODO: What else does this thing do?
	//Pass stuff to inventory handler (TODO)
	//Handle special tiles (crafting table etc)
	
}
