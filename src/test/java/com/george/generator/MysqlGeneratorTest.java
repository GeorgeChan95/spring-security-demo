package com.george.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

/**
 * <p>
 *  代码生成器
 * <p/>
 *
 * @Author : George Chan
 * @Since : JDK1.8
 * @Date : 14:47
 */
public class MysqlGeneratorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlGeneratorTest.class);
    public static void main(String[] args) {
        // 数据库全局配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder("jdbc:mysql://192.168.6.203:3306/security?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowPublicKeyRetrieval=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&rewriteBatchedStatements=true", "root", "root")
                .dbQuery(new MySqlQuery()) // 数据库查询,指定数据库为mysql
                .schema("security") // 执行数据库名
                .typeConvert(new MysqlTypeConvertCustom()) // 数据库类型转换器,这里自定义了类型转换器
                .keyWordsHandler(new MySqlKeyWordsHandler()) // 数据库关键字处理器
                .build();

        AutoGenerator generator = new AutoGenerator(dataSourceConfig);

        String projectPath = System.getProperty("user.dir");
        LOGGER.info("mysql代码生成器当前操作目录 ==> {}", projectPath);
        // 代码生成器全局配置
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .author("Geroge Chan") // 设置作者
                .disableOpenDir() // 禁止打开输出目录
                .outputDir(projectPath + "/src/main/java") // 指定输出目录
//                .enableSwagger() // 开启 swagger 模式
                .dateType(DateType.TIME_PACK) // 时间策略
                .commentDate("yyyy-MM-dd HH:mm:ss") // 评论时间格式
                .build();
        generator.global(globalConfig);

        // 代码生成器包配置
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("com.george") // 父包名
                .moduleName(StringPool.EMPTY) // 父包模块名
                .entity("entity") // Entity 包名
                .controller("controller") // Controller 包名
                .service("service") // Service 包名
                .serviceImpl("service.impl") // Service Impl 包名
                .mapper("mapper") // Mapper 包名
                .xml("mapper") // Mapper XML 包名
                .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper/")) // 路径配置信息
                .build();
        generator.packageInfo(packageConfig);

        // 代码生成器，生成策略配置
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .addInclude("sys_menu","sys_role") // 生成的表
                .addTablePrefix("sys_", "b_") // 表前缀
                .entityBuilder() // Entity 策略配置
                .enableLombok() // 开启 lombok 模型
                .enableChainModel() // 	开启链式模型
                .enableActiveRecord() // 开启 ActiveRecord 模型
                .enableTableFieldAnnotation() // 开启生成实体时生成字段注解
                .logicDeleteColumnName("delete_flag")
                .naming(NamingStrategy.underline_to_camel)
                .idType(IdType.AUTO)
                .formatFileName("%s")
                .controllerBuilder() // Controller 策略配置
                .enableHyphenStyle() // 开启驼峰转连字符
                .enableRestStyle() // 开启生成@RestController 控制器
                .formatFileName("%sController") // 	格式化文件名称
                .serviceBuilder() // Service 策略配置
                .formatServiceFileName("%sService") // 格式化 service 接口文件名称
                .formatServiceImplFileName("%sServiceImpl") // 格式化 service 实现类文件名称
                .mapperBuilder() // Mapper 策略配置
                .superClass(BaseMapper.class) // 设置父类entityBuilder
                .enableMapperAnnotation() // 开启 @Mapper 注解
                .enableBaseResultMap() // 启用 BaseResultMap 生成
                .enableBaseColumnList() // 启用 BaseColumnList
                .formatMapperFileName("%sMapper") // 格式化 mapper 文件名称
                .formatXmlFileName("%sMapper") // 格式化 xml 实现类文件名称
                .build();
        generator.strategy(strategyConfig);

        generator.execute();
    }

    static class MysqlTypeConvertCustom extends MySqlTypeConvert {
        @Override
        public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
            String field = fieldType.toLowerCase();
            if (StringUtils.equals(field, "tinyint(1)")) {
                return DbColumnType.INTEGER;
            }
            return super.processTypeConvert(config, fieldType);
        }
    }
}
