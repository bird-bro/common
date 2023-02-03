package org.birdbro.common.entity;

import org.birdbro.common.entity.sopInfo.Failed;
import org.birdbro.common.entity.sopInfo.Success;
import org.birdbro.common.entity.sopInfo.Warnning;
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
