package backEnd.handler.admin.mapper;

import backEnd.handler.admin.model.Admin;
import backEnd.handler.admin.model.AdminRight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    AdminRight getAdminRight(String adminId);
    Admin adminLogin(@Param("userName") String userName, @Param("userPassword") String userPassword);
}
