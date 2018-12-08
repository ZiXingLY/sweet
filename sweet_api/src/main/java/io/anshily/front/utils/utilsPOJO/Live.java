package io.anshily.front.utils.utilsPOJO;

public class Live {
    Integer id;
    String content;
    Image[] images;
    Integer up_counts;
    Integer down_counts;
    String content_prefix;
    String sort;
    String created_at;
    String zan_status;
    String readings;

    public String getReadings() {
        return readings;
    }

    public void setReadings(String readings) {
        this.readings = readings;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getZan_status() {
        return zan_status;
    }

    public void setZan_status(String zan_status) {
        this.zan_status = zan_status;
    }

    public String getContent_prefix() {
        return content_prefix;
    }

    public void setContent_prefix(String content_prefix) {
        this.content_prefix = content_prefix;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Integer getUp_counts() {
        return up_counts;
    }

    public void setUp_counts(Integer up_counts) {
        this.up_counts = up_counts;
    }

    public Integer getDown_counts() {
        return down_counts;
    }

    public void setDown_counts(Integer down_counts) {
        this.down_counts = down_counts;
    }
}
