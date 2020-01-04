package backEnd.handler.admin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Admin {
    private String userId;
    private String userName;
    private String userPassword;
    private String belong;
}
