package backEnd.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * 用于从服务器与客户端之间的数据传输
 */
public class Results extends JSONObject implements Serializable {

    private JSONObject data = new JSONObject();
    {
        put("data", data);
        put("code", "200");
        put("msg", "");
    }

    protected Results() {}

    public Results(String jsonString) {
        JSONObject json = JSON.parseObject(jsonString);
        data = json.getJSONObject("data");
        put("data", data);
        put("code", json.getInteger("code"));
        put("msg", json.getString("msg"));
    }

    public String getCode() {
        return getString("code");
    }

    public void setCode(String code) {
        put("code", code);
    }

    public String getMsg() {
        return getString("msg");
    }

    public void setMsg(String msg) {
        put("msg", msg);
    }

    /**
     * 获取data的JSONObject对象
     * @return  data对象
     */
    public JSONObject getData() {
        return getJSONObject("data");
    }

    /**
     * 获取data中的指定类的数据
     * @param cl    需要获取的数据类
     * @return  数据对象
     */
    public <T> T getData(Class<T> cl) {
        return data.getObject(cl.getSimpleName(), cl);
    }

    public void addValueList(List list) {
        String keyName = "list";
        if (list != null && list.size() > 0) {
            keyName = list.get(0).getClass().getSimpleName() + List.class.getSimpleName();
            data.put(keyName, list);
        } else data.put(keyName, new JSONArray());
    }

    public void addValue(Object object) {
        data.put(object.getClass().getSimpleName(), object);
    }

    protected void addValue(String key, Object value) {
        data.put(key, value);
    }
}