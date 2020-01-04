package backEnd.handler.news;

import backEnd.filter.RequestFilter;
import backEnd.model.RecordLogger;
import backEnd.model.Results;
import backEnd.model.ext.FailResults;
import backEnd.model.ext.SusResults;
import backEnd.utils.JSONGet;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private RecordLogger logger;

    @PostMapping("news/add")
    public Results addNews(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        News news = JSONGet.getValue(data, "news", News.class);
        if (newsService.addNews(news)) {
            logger.record(adminId, "News-" + news.getNewsId(), "添加");
            return new SusResults();
        } else return new FailResults("添加消息失败");
    }

    @PostMapping("news/delete")
    public Results removeNews(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        final String newsId = JSONGet.getString(data, "newsId");
        if (newsService.removeNews(newsId)) {
            logger.record(adminId, "News-" + newsId, "删除");
            return new SusResults();
        } else return new FailResults("删除消息失败: " + newsId);
    }

    @PostMapping("news/update")
    public Results updateNews(
            @RequestAttribute(RequestFilter.ADMIN_ID_IN_TOKEN) String adminId,
            @RequestBody String data
    ) {
        News news = JSONGet.getValue(data, "news", News.class);
        if (newsService.updateNews(news)) {
            logger.record(adminId, "News-" + news.getNewsId(), "修改");
            return new SusResults();
        } else return new FailResults("更新消息失败: " + news.getNewsId());
    }

    @PostMapping("news/getList")
    public Results getNews(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "1") int size,
            @RequestBody String data
    ) {
        News news = JSONGet.getValue(data, "news", News.class);
        PageHelper.startPage(page, size);
        List<News> list = newsService.getNewsList(news);
        PageInfo<News> pageInfo = new PageInfo<>(list);
        return new SusResults(pageInfo);
    }
}
