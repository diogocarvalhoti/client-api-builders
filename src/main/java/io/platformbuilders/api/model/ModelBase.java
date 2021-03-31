package io.platformbuilders.api.model;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class ModelBase<K extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract K getId();

}

