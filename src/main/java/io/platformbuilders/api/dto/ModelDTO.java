package io.platformbuilders.api.dto;

import io.platformbuilders.api.mapper.Converter;
import io.platformbuilders.api.model.ModelBase;

import java.io.Serializable;

public abstract class ModelDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public <E extends ModelBase<?>> E toEntity(Class<E> clazz) {
        return Converter.getInstance().converter(this, clazz);
    }

}

