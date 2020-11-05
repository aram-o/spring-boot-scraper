package com.aram.batchservice.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author aram
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComnarconPersonSummaryDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String id;
    
    private String title;
    
    private String imgUrl;
}