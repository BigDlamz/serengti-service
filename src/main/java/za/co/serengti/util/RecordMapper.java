package za.co.serengti.util;

import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RecordMapper {

    @Inject
    ModelMapper modelMapper;

    public <S, T> T convert(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
