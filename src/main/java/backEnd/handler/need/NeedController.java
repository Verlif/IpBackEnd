package backEnd.handler.need;

import backEnd.filter.RequestFilter;
import backEnd.model.Results;
import backEnd.model.ext.FailResults;
import backEnd.model.ext.SusResults;
import backEnd.utils.JSONGet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NeedController {
    @Autowired
    private NeedService needService;

    @GetMapping("need/add")
    public Results addNeed(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Need need = JSONGet.getValue(data, "need", Need.class);
        if (needService.addNeed(need)) {
            return new SusResults();
        } else return new FailResults("添加需求失败");
    }

    @PostMapping("need/delete")
    public Results deleteNeed(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        String needId = JSONGet.getString(data, "needId");
        if (needService.removeNeed(needId)) {
            return new SusResults();
        } else return new FailResults("删除失败");
    }

    @PostMapping("need/update")
    public Results updateNeed(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Need need = JSONGet.getValue(data, "need", Need.class);
        if (needService.updateNeed(need)) {
            return new SusResults();
        } else return new FailResults("更新需求失败");
    }

    @GetMapping("need/get")
    public Results getNeed(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestParam String needId
    ) {
        Need need = needService.getNeed(needId);
        if (need != null) {
            return new SusResults(need);
        } else return new FailResults("未能查询到此数据");
    }

    @PostMapping("need/getList")
    public Results getNeedList(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "1") int size,
            @RequestBody String data
    ) {
        Need need = JSONGet.getValue(data, "need", Need.class);
        PageHelper.startPage(page, size);
        List<Need> list = needService.getNeedList(need);
        PageInfo<Need> pageInfo = new PageInfo<>(list);
        return new SusResults(pageInfo);
    }
}
