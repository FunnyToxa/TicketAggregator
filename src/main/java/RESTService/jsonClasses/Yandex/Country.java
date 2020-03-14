package RESTService.jsonClasses.Yandex;

import java.util.List;

public class Country {
    private String title;
    private List<Region> regions;

    public String getTitle() {
        return title;
    }
    public List<Region> getRegions() {
        return regions;
    }
}
