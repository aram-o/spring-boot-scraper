package com.aram.batchservice.mapper;

import com.aram.batchservice.dto.ComnarconPersonDTO;
import com.aram.batchservice.dto.ComnarconPersonSummaryDTO;
import com.aram.batchservice.model.ComnarconPerson;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author aram
 */
@Component
public class PersonMapper {
    
    private final DozerBeanMapper mapper;
    
    public PersonMapper() {
        List<String> mappingFiles = new ArrayList();
        mappingFiles.add("dozerJdk8Converters.xml");

        this.mapper = new DozerBeanMapper();
        this.mapper.setMappingFiles(mappingFiles);
    }
    
    /**
     * Map ComnarconPerson to ComnarconPersonSummaryDTO.
     * @param comnarconPerson
     * @return
     */
    public ComnarconPersonSummaryDTO toSummary(ComnarconPerson comnarconPerson) {
        ComnarconPersonSummaryDTO dto = this.mapper.map(comnarconPerson, ComnarconPersonSummaryDTO.class);
        return dto;
    }
    
    /**
     * Map ComnarconPersonDTO to ComnarconPerson.
     * @param comnarconPersonDTO
     * @return
     */
    public ComnarconPerson toComnarconPerson(ComnarconPersonDTO comnarconPersonDTO) {
        ComnarconPerson model = this.mapper.map(comnarconPersonDTO, ComnarconPerson.class);
        return model;
    }
    
}
