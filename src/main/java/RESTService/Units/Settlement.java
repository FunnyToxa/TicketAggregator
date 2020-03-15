package RESTService.Units;

import java.util.Objects;

/**
 * Класс населенный пункт
 */
public class Settlement {
    private String name;
    private String yandexCode;

    public Settlement(String name, String yandexCode) {
        this.name = name;
        this.yandexCode = yandexCode;
    }

    public String getName() {
        return name;
    }

    public String getYandexCode() {
        return yandexCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settlement that = (Settlement) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
