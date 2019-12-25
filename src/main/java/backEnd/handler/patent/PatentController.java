package backEnd.handler.patent;

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
public class PatentController {
    @Autowired
    private PatentService patentService;

    @PostMapping("patent/add")
    public Results addPatent(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Patent patent = JSONGet.getValue(data, "patent", Patent.class);
        if (patentService.addPatent(patent)) {
            return new SusResults();
        } else return new FailResults("添加专利失败");
    }

    @PostMapping("patent/delete")
    public Results removePatent(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        final String patentId = JSONGet.getString(data, "patentId");
        if (patentService.removePatent(patentId)) {
            return new SusResults();
        } else return new FailResults("删除专利失败: " + patentId);
    }

    @PostMapping("patent/update")
    public Results updatePatent(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Patent patent = JSONGet.getValue(data, "patent", Patent.class);
        if (patentService.updatePatent(patent)) {
            return new SusResults();
        } else return new FailResults("更新专利失败: " + patent.getPatentId());
    }

    @GetMapping("patent/get")
    public Results getPatent(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestParam String patentId
    ) {
        Patent patent = patentService.getPatentById(patentId);
        if (patent != null) {
            return new SusResults(patent);
        } else return new FailResults("未能查到该专利: " + patentId);
    }

    @PostMapping("patent/getList")
    public Results getPatentList(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "1") int size,
            @RequestBody String data
    ) {
        Patent patent = JSONGet.getValue(data, "patent", Patent.class);
        PageHelper.startPage(page, size);
        List<Patent> list = patentService.getPatentList(patent);
        PageInfo<Patent> pageInfo = new PageInfo<>(list);
        return new SusResults(pageInfo);
    }
}
