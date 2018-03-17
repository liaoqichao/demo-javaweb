package hibernate.demo17.domain;

import java.util.Date;

/**
 * 抽象类
 * 继承结构。父类：文章
 * 子类：主贴，回帖
 */
public abstract class Article {

	private Integer id;
	private String title;
	private String content;
	private Date postTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", postTime=" + postTime + "]";
	}
	
	
}
