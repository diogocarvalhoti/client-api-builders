package io.platformbuilders.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@Setter
@Getter
public class Client extends ModelBase<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "ativo")
	private boolean active;

    public Client() {
        super();
    }

    public Client(Long id, String name, boolean active) {
        super();
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public Client(String name, Boolean active) {
        super();
        this.name = name;
        this.active = active;
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
