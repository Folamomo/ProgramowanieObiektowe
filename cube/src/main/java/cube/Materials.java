package cube;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;

import java.util.*;

public class Materials {
    private Map<ColorRGBA, Material> data = new HashMap<>();
    private AssetManager assetManager;
    public Materials(AssetManager manager){
        assetManager = manager;
        ArrayList<ColorRGBA> colors = new ArrayList<>();
        colors.add(ColorRGBA.Black);
        colors.add(ColorRGBA.White);
        colors.add(ColorRGBA.Yellow);
        colors.add(ColorRGBA.Blue);
        colors.add(ColorRGBA.Green);
        colors.add(ColorRGBA.Red);
        colors.add(ColorRGBA.Orange);
        colors.add(ColorRGBA.Magenta);
        addAll(colors);
    }
    public Materials(AssetManager manager, List<ColorRGBA> colors){
        assetManager = manager;
        addAll(colors);
    }
    public void addAll(List<ColorRGBA> colors) {
        for (ColorRGBA color : colors) add(color);
    }
    public void add(ColorRGBA color){
        Material material = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        material.setColor("Color", color);
        data.put(new ColorRGBA(color), material);
    }
    Material getMaterial (ColorRGBA color){
        return data.get(color);
    }
}
