package jin.chen.pojo.vo;

import java.util.Date;

public class Reports {
    //主键
    private String id;
    //被处理人name
    private String dealUserName;
    //处理视频id
    private String dealVideoId;
    //标题
    private String title;
    //内容
    private String content;
    //视频状态
    private String status;
    //举报时间时间
    private Date ts;
    //视频描述
    private String videoDesc;
    //视频地址
    private String videoPath;
    //举报人name
    private String submitUserName;

    public void setDealUserName(String dealUserName) {
        this.dealUserName = dealUserName;
    }

    public String getDealUserName() {
        return dealUserName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDealVideoId(String dealVideoId) {
        this.dealVideoId = dealVideoId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setTs(Date ts) {
        this.ts = ts;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public void setSubmitUserName(String submitUserName) {
        this.submitUserName = submitUserName;
    }

    public String getId() {
        return id;
    }

    public String getDealVideoId() {
        return dealVideoId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }


    public Date getTs() {
        return ts;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public String getSubmitUserName() {
        return submitUserName;
    }
}
