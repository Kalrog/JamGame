package assets;

/**
 * Manages Loading of assets
 * 
 * Created by jonathan on 13.01.17.
 */
public class AssetLoader
{
    //"Assets/Island.png:131:103",
    public static final String[] ISLAND_TEXTURES = new String[] {"Assets/IslandHuts.png:131:103","Assets/IslandTemple.png:131:103","Assets/IslandTrees.png:131:103"};
    public static final String[] CITY_TEXTURES = new String[] {};
    public static final String[] PIRATE_TEXTURES = new String[] {"Assets/PirateShip.png:119:75","Assets/PirateShipCannons.png:119:75"};
    public static final String[] STORM_TEXTURES = new String[] {"Assets/Storm.png::"};

    public static Texture[] islandTextures;
    public static Texture[] cityTextures;
    public static Texture[] pirateTextures;
    public static Texture[] stormTextures;

    public static Texture wave = new Texture("Assets/Waves.png" ,780 ,29);

    public static void loadTextures()
    {
        islandTextures = load(ISLAND_TEXTURES);
        //load(CITY_TEXTURES,cityTextures);
        pirateTextures = load(PIRATE_TEXTURES);
        //load(STORM_TEXTURES,stormTextures);
    }

    private static Texture[] load(String[] texData)
    {
        Texture[] textures = new Texture[texData.length];
        int position = 0;
        for(String tex : texData)
        {
            String[] info = tex.split(":");
            textures[position] = new Texture(info[0],Integer.valueOf(info[1]),Integer.valueOf(info[2]));
            if(info.length == 4)
                textures[position].yShift = Integer.valueOf(info[3]);

            position++;
        }
        return textures;
    }

    public static Texture getRandomIslandTexture()
    {
        return randomTexture(islandTextures);
    }

    public static Texture getRandomCityTexture()
    {
        return randomTexture(cityTextures);
    }

    public static Texture getRandomPirateTexture()
    {
        return randomTexture(pirateTextures);
    }

    public static Texture getRandomStormTexture()
    {
        return randomTexture(stormTextures);
    }

    private static Texture randomTexture(Texture[] textures)
    {
        int random = (int)(Math.random() * textures.length);
        return textures[random].clone();
    }
}
