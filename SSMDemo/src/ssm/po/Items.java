package ssm.po;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ssm.controller.validation.ValidationGroup1;
import ssm.controller.validation.ValidationGroup2;

public class Items {
    private Integer id;

    /*
     * - У��������1~30���ַ�֮��
     * - message��У�����ĵ���ʾ��Ϣ�����ǲ���ֱ��д��ֱ��д����Ӳ���룬
     * 		��Ҫ�Ѵ�����Ϣ���õ�CustomValidationMessages.properties�С�
     * 		message������ֵӦ��Ϊ"{�����ļ���key}"
     *  - group���ڱ�ʾУ�������ĸ����顣group���Զ���������
     */
    @Size(min=1,max=30,message="{items.name.length.error}",groups={ValidationGroup1.class})
    private String name;

    private Float price;

    private String pic;

    // �ǿ�У��
    @NotNull(message="{items.createtime.isNull}",groups={ValidationGroup2.class})
    private Date createtime;

    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", price=" + price + ", pic=" + pic + ", createtime=" + createtime + ", detail=" + detail + "]";
	}
    
}