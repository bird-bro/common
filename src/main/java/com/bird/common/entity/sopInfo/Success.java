package com.bird.common.entity.sopInfo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bird
 * @date 2022-4-7 15:46
 **/
@Data
public class Success implements Serializable  {
    private static final long serialVersionUID = 5352355L;
    private List<String> referencedSOPClassUID;
    private List<String> referencedSOPInstanceUID;
    private List<String> retrieveURL;
    private String originalAttributesSequence;
}
