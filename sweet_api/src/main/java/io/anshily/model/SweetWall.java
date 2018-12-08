package io.anshily.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="wall_emotions")
public class SweetWall {
    private String tid;
    private String uin;
    private String created;
    private String content;
    private String info;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "SweetWall{" +
                "tid='" + tid + '\'' +
                ", uin='" + uin + '\'' +
                ", created='" + created + '\'' +
                ", content='" + content + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
