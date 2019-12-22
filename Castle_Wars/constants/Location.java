package Castle_Wars.constants;

import org.osbot.rs07.api.map.Area;

public enum Location {

    CASTLE_WARS_LOBBY(new Area(2435, 3080, 2447, 3098), 0),
    ZAMORAK_WAITING_LOBBY(new Area(2409, 9513, 2433, 9537), 0),
    SARADOMIN_WAITING_LOBBY(new Area(2366, 9481, 2433, 9537), 0),
    SARADOMIN_GAME_LOBBY(new Area(2422, 9515, 2432, 3061), 1),
    ZAMORAK_GAME_LOBBY(new Area(2367, 3126, 2377, 3135), 1),
    SARADOMIN_IDLE_AREA(new Area(2425, 3073, 2431, 3078), 2),
    ZAMORAK_IDLE_AREA(new Area(2368, 3129, 2374, 3134), 2);

    private Area area;

    Location(Area area, int plane) {
        this.area = area;
        this.area.setPlane(plane);
    }
    public Area getArea() {
        return area;
    }
}
