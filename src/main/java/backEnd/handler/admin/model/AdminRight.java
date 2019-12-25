package backEnd.handler.admin.model;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@Builder
public class AdminRight {
    private String adminId;
    private String adminGroupId;
    private List<String> adminRightUrl;

    public void setAdminRightUrl(String adminRightUrl) {
        this.adminRightUrl = Collections.singletonList(adminRightUrl);
    }

    public String getAdminRightUrl() {
        return Arrays.toString(adminRightUrl.toArray());
    }
}
