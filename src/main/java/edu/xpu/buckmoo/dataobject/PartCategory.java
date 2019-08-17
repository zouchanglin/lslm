package edu.xpu.buckmoo.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author tim
 * @version 1.2
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
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;
}