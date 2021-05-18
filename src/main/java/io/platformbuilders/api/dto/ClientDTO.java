package io.platformbuilders.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientDTO extends ModelDTO {

    private Long id;
    private String name;
    private Boolean active;

    public ClientDTO() {
        super();
    }

    public ClientDTO(long id, String name, Boolean active) {
        super();
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public ClientDTO(String name) {
        super();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
