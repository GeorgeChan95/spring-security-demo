package com.george.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author Geroge Chan
 * @since 2024-05-28 09:57:49
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 路由地址
     */
    @TableField("`path`")
    private String path;

    /**
     * 组件路径
     */
    @TableField("`component`")
    private String component;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @TableField("`visible`")
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    @TableField("`status`")
    private String status;

    /**
     * 权限标识
     */
    @TableField("perms")
    private String perms;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    @TableField("create_by")
    private Long createBy;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_by")
    private Long updateBy;

    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 是否删除（0未删除 1已删除）
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
