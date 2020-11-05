package com.aram.batchservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

/**
 *
 * @author aram
 */
@Data
@Document(collection = "comnarcon_person")
public class ComnarconPerson {

    @Id
    private String id;
    
    private String title;
    
    private String articleContent;
    
    private String imgUrl;
    
}
