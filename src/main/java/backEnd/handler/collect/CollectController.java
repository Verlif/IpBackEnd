package backEnd.handler.collect;

import backEnd.filter.RequestFilter;
import backEnd.model.RecordLogger;
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
public class CollectController {
    @Autowired
    private CollectService collectService;
    @Autowired
    private RecordLogger logger;

    @PostMapping("collect/add")
    public Results addCollect(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Collect collect = JSONGet.getValue(data, "collect", Collect.class);
        if (collectService.addCollect(collect)) {
            logger.record(adminId, "Collect-" + collect.getCollectId(), "添加");
            return new SusResults();
        } else return new FailResults("添加收藏失败");
    }

    @PostMapping("collect/delete")
    public Results removeCollect(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        String collectId = JSONGet.getString(data, "collectId");
        if (collectService.deleteCollect(collectId)) {
            logger.record(adminId, "Collect-" + collectId, "删除");
            return new SusResults();
        } else return new FailResults("删除收藏失败");
    }

    @PostMapping("collect/update")
    public Results updateCollect(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Collect collect = JSONGet.getValue(data, "collect", Collect.class);
        if (collectService.updateCollect(collect)) {
            logger.record(adminId, "Collect-" + collect.getCollectId(), "修改");
            return new SusResults();
        } else return new FailResults("更新收藏失败");
    }

    @PostMapping(value = "collect/getList")
    public Results getCollectList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "1") int size,
            @RequestBody String data
    ) {
        Collect collect = JSONGet.getValue(data, "collect", Collect.class);
        PageHelper.startPage(page, size);
        List<Collect> list = collectService.getCollectList(collect);
        PageInfo<Collect> pageInfo = new PageInfo<>(list);
        return new SusResults(pageInfo);
    }

    @GetMapping("collect/get")
    public Results getCollect(
            @RequestParam String collectId
    ) {
        Collect collect = collectService.getCollect(collectId);
        if (collect != null) {
            return new SusResults(collect);
        } else return new FailResults("查询失败");
    }
}
