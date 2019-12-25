package backEnd.handler.admin.mapper;

import backEnd.handler.admin.model.AdminRight;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    AdminRight getAdminRight(String adminId);
}
