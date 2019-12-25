package backEnd.handler.reference;

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
public class ReferenceController {
    @Autowired
    private ReferenceService referenceService;

    @PostMapping("reference/add")
    public Results addReference(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Reference reference = JSONGet.getValue(data, "reference", Reference.class);
        if (referenceService.addReference(reference)) {
            return new SusResults();
        } else return new FailResults("添加参数失败");
    }

    @PostMapping("reference/delete")
    public Results removeReference(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        final String referenceId = JSONGet.getString(data, "referenceId");
        if (referenceService.removeReference(referenceId)) {
            return new SusResults();
        } else return new FailResults("删除参数失败: " + referenceId);
    }

    @PostMapping("reference/update")
    public Results updateReference(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        Reference reference = JSONGet.getValue(data, "reference", Reference.class);
        if (referenceService.updateReference(reference)) {
            return new SusResults();
        } else return new FailResults("更行参数失败: " + reference.getRefId());
    }

    public Results getReferenceList(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "1") int size,
            @RequestBody String data
    ) {
        Reference reference = JSONGet.getValue(data, "reference", Reference.class);
        PageHelper.startPage(page, size);
        List<Reference> list = referenceService.getReferenceList(reference);
        PageInfo<Reference> pageInfo = new PageInfo<>(list);
        return new SusResults(pageInfo);
    }
}
