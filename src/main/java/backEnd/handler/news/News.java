package backEnd.handler.news;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@TableName("t_news")
public class News extends model.JSONBuilder {

    @TableField("news_id")
    @TableId
    private long newsId;      // 消息Id
    @TableField("from_id")
    private String fromId;      // 消息创建者uuid
    @TableField("to_id")
    private String toId;        // 消息接收者uuid
    @TableField("reply_id")
    private String replyId;     // 所回复的消息id
    private String content;     // 消息内容
    @TableField("is_read")
    private int isRead;      // 消息是否已读
    @TableField("create_time")
    private Timestamp createTime;    // 消息发送时间
    @TableField("update_time")
    private Timestamp updateTime;    // 消息修改时间
    @TableField("is_del")
    private int isDel;          // 消息是否被删除

    public boolean isRead() {
        return isRead != 0;
    }

    public void read(boolean isRead) {
        this.isRead = isRead ? 1 : 0;
    }

    public boolean isDel() {
        return isDel != 0;
    }

    public void del(boolean isDel) {
        this.isDel = isDel ? 1 : 0;
    }
}
