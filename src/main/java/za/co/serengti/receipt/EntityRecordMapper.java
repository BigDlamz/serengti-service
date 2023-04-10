package za.co.serengti.receipt;

import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EntityRecordMapper {

    private final ModelMapper modelMapper;

    public EntityRecordMapper() {
        modelMapper = new ModelMapper();
    }

    public <S, T> T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
