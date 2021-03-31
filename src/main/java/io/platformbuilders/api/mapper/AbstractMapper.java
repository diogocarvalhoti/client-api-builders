package io.platformbuilders.api.mapper;

import io.platformbuilders.api.dto.ModelDTO;
import io.platformbuilders.api.model.ModelBase;

public abstract class AbstractMapper<E extends ModelBase<?>, D extends ModelDTO> {

    private Class<? extends ModelDTO> dto;
    private Class<? extends ModelBase<?>> entity;

    public AbstractMapper(Class<? extends ModelBase<?>> entity, Class<? extends ModelDTO> dto) {
        super();
        this.dto = dto;
        this.entity = entity;
    }

    public abstract D entityToDTO(E entity);

    public abstract E dtoToEntity(D dto);

    public E full(D dto) {
        return (E) Converter.getInstance().converter(dto, entity);
    }

    public D full(E entity) {
        return (D) Converter.getInstance().converter(entity, dto);
    }


}

