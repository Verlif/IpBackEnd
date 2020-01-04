package backEnd.handler.admin.model;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class AdminRight {

    private String adminId;
    private String adminGroupId;
    private String adminRightUrl;
    private String belong;

    public List<String> getRightUrl() {
        return Arrays.asList(adminRightUrl.replace("[", "").replace("]", "").replace(" ", "").split(","));
    }

}
