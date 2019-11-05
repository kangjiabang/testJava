package snakeyaml.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Address {
    private String lines;
    private String city;
    private String state;
    private Integer postal;
}