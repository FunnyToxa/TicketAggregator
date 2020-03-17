package RESTService.jsonClasses.Yandex.Response;

public class Thread {
    private String uid;
    private String title;
    private String number;
    private String vahicle;
    private Carrier carrier;

    public Carrier getCarrier() {
        return carrier;
    }

    public String getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getNumber() {
        return number;
    }

    public String getVahicle() {
        return vahicle;
    }
}
