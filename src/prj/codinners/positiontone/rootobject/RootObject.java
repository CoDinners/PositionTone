package prj.codinners.positiontone.rootobject;

import java.awt.*;
import java.util.ArrayList;

public class RootObject {
    public static ArrayList<RootObject> OBJECTS = new ArrayList<>();
    public static ArrayList<RootObject> DELETE_OBJECTS = new ArrayList<>();

    public static void add(RootObject object) {
        OBJECTS.add(object);
    }

    public static void update() {
        for (RootObject object : DELETE_OBJECTS) {
            OBJECTS.remove(object);
        }
    }

    public void tick() {}
    public void render(Graphics graphics) {}

    public void remove() {
        DELETE_OBJECTS.add(this);
    }
}
