package backEnd.handler.news;

import backEnd.handler.need.Need;
import backEnd.utils.UUIDUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;

    public boolean addNews(News news) {
        news.setCreateTime(new Timestamp(new Date().getTime()));
        news.setUpdateTime(news.getCreateTime());
        return newsMapper.insert(news) > 0;
    }

    public boolean removeNews(String newsId) {
        UpdateWrapper<News> wrapper = new UpdateWrapper<>();
        wrapper.eq("news_id", newsId);
        return newsMapper.delete(wrapper) > 0;
    }

    public boolean updateNews(News news) {
        news.setUpdateTime(new Timestamp(new Date().getTime()));
        UpdateWrapper<News> wrapper = new UpdateWrapper<>();
        wrapper.eq("news_id", news.getNewsId());
        return newsMapper.update(news, wrapper) > 0;
    }

    public List<News> getNewsList(News news) {
        QueryWrapper<News> wrapper = getWrapper(news);
        return newsMapper.selectList(wrapper);
    }

    private QueryWrapper<News> getWrapper(News news) {
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        Map<String, String> map = new HashMap<>();
        map.put("is_read", String.valueOf(news.getIsRead()));
        if (news.getFromId() != null && !news.getFromId().equals("")) {
            map.put("from_id", news.getFromId());
        }
        if (news.getToId() != null && !news.getToId().equals("")) {
            map.put("to_id", news.getToId());
        }
        return wrapper.allEq(map);
    }
}
