package com.bird.common.entity.sopInfo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bird
 * @date 2022-4-7 15:47
 **/
@Data
public class Failed implements Serializable {
    private static final long serialVersionUID = 5362354L;
    private List<String> referencedSOPClassUID;
    private List<String> referencedSOPInstanceUID;
    private String failureReason;
}
