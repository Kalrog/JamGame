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
    public static final String[] CITY_TEXTURES = new String[] {"Assets/Port.png:121:67"};
    public static final String[] PIRATE_TEXTURES = new String[] {"Assets/PirateShip.png:119:75","Assets/PirateShipCannons.png:119:75"};
    public static final String[] STORM_TEXTURES = new String[] {"Assets/Storm.png:155:134"};

    private static Texture[] islandTextures;
    private static Texture[] cityTextures;
    private static Texture[] pirateTextures;
    private static Texture[] stormTextures;

    public static Texture wirl = new Texture("Assets/Wirl.png" ,111 , 54);

    public static Texture wave = new Texture("Assets/Waves.png" ,780 ,29);

    public static void loadTextures()
    {
        islandTextures = load(ISLAND_TEXTURES);
        cityTextures = load(CITY_TEXTURES);
        pirateTextures = load(PIRATE_TEXTURES);
        stormTextures = load(STORM_TEXTURES);
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
