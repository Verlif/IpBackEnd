package backEnd.model.ext;

import backEnd.model.Results;

import java.util.List;

public class SusResults extends Results {

    public SusResults() {
        build();
    }

    public SusResults(String msg) {
        build();
        setMsg(msg);
    }
    public SusResults(Object object) {
        build();
        addValue(object);
    }
    public SusResults(List list) {
        build();
        addValueList(list);
    }

    private void build() {
        setCode("200");
        setMsg("操作成功");
    }
}
