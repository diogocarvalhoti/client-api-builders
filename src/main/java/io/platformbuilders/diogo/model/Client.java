package io.platformbuilders.diogo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@Setter
@Getter
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome")
    private String name;

	@Column(name = "ativo")
	private boolean active;

    public Client() {
        super();
    }

    public Client(long id, String name, boolean active) {
        super();
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public Client(String name) {
        super();
        this.name = name;
    }
}
