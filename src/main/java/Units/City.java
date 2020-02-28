package Units;

import javax.persistence.*;

/**
 * Класс городов
 */
@Entity
@Table(name = "Cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cityId;
    private String cityName;
    private String country;
    private String region;
    private int coordX;
    private int coordY;
}
