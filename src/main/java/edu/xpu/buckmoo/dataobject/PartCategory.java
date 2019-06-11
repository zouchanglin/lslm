package edu.xpu.buckmoo.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


/**
 * @author tim
 * @version 1.0
 * @className CategoryInfo
 * @description 兼职分类描述信息
 * @date 2019-06-11 18:02
 */
@Data
@Entity
@DynamicUpdate
public class PartCategory {

    /**
     * 分类的Id
     * 主键由数据库生成, 采用数据库自增长: GenerationType.IDENTITY
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类类型
     */
    private Integer categoryType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public PartCategory() {
    }
    public PartCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
