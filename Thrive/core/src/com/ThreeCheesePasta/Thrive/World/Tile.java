package com.ThreeCheesePasta.thrive.world;

public class Tile {
    public String displayName = "";
    public String pathToTexture = "";
    public String toolToBreakWith = "";

    public float hardness = 0.0f;

    public Boolean gravityAffects = false;
    public Boolean isMultiblock = false;
    public Boolean isEthereal = false;
    public Boolean canSupportTiles = true;

    public int id = 0;
}
