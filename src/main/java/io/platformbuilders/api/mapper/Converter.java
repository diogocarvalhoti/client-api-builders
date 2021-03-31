package io.platformbuilders.api.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    private static final ModelMapper MAPPER = new ModelMapper();
    private static final Converter INSTANCE = new Converter();

    private Converter() {
        super();
    }

    public static Converter getInstance() {
        return INSTANCE;
    }

    public <S, T> T converter(S source, Class<T> target) {
        return MAPPER.map(source, target);
    }

    public <S, T> T converter(S source, Class<T> target, PropertyMap<S, T> propertyMap) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);
        return modelMapper.map(source, target);
    }

}
