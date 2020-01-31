package RedSpiderEggs.constants;

import org.osbot.rs07.api.map.Area;

public enum Location {

    SPIDER_EGG_LOCATION(new Area(3113, 9946, 3134, 9963), 0),
    EDGEVILLE_LOCATION(new Area(3083, 3487, 3101, 3501), 0);


    private Area area;

    Location(Area area, int plane) {
        this.area = area;
        this.area.setPlane(plane);
    }
    public Area getArea() {
        return area;
    }
}
