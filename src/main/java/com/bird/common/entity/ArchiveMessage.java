package com.bird.common.entity;

import com.bird.common.entity.sopInfo.Failed;
import com.bird.common.entity.sopInfo.Success;
import com.bird.common.entity.sopInfo.Warnning;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bird
 * @date 2022-4-7 15:44
 **/
@Data
public class ArchiveMessage implements Serializable {

    private static final long serialVersionUID = 5352354L;
    private List<Success> successList;
    private List<Warnning> warnningList;
    private List<Failed> failedList;
    private List<String> retrieveURL;
    private String otherFailed;

}
