package RESTService.jsonClasses.Yandex.Destination;

import com.google.gson.annotations.SerializedName;

public class Code {
    @SerializedName("yandex_code")
    private String yandexCode;

    public String getYandexCode() {
        return yandexCode;
    }
}
