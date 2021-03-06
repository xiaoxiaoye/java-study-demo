package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.model.Coffee;
import com.example.mybatisdemo.model.CoffeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

public interface CoffeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coffee
     *
     * @mbg.generated Tue May 12 23:29:28 CST 2020
     */
    long countByExample(CoffeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coffee
     *
     * @mbg.generated Tue May 12 23:29:28 CST 2020
     */
    int deleteByExample(CoffeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coffee
     *
     * @mbg.generated Tue May 12 23:29:28 CST 2020
     */
    @Delete({
        "delete from t_coffee",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coffee
     *
     * @mbg.generated Tue May 12 23:29:28 CST 2020
     */
    @Insert({
        "insert into t_coffee (name, price, ",
        "create_time, update_time)",
        "values (#{name,jdbcType=VARCHAR}, #{price,jdbcType=BIGINT,typeHandler=com.example.mybatisdemo.handler.MoneyTypeHandler}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="CALL IDENTITY()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Coffee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coffee
     *
     * @mbg.generated Tue May 12 23:29:28 CST 2020
     */
    int insertSelective(Coffee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coffee
     *
     * @mbg.generated Tue May 12 23:29:28 CST 2020
     */
    List<Coffee> selectByExampleWithRowbounds(CoffeeExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coffee
     *
     * @mbg.generated Tue May 12 23:29:28 CST 2020
     */
    List<Coffee> selectByExample(CoffeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coffee
     *
     * @mbg.generated Tue May 12 23:29:28 CST 2020
     */
    @Select({
        "select",
        "id, name, price, create_time, update_time",
        "from t_coffee",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.example.mybatisdemo.mapper.CoffeeMapper.BaseResultMap")
    Coffee selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coffee
     *
     * @mbg.generated Tue May 12 23:29:28 CST 2020
     */
    int updateByExampleSelective(@Param("record") Coffee record, @Param("example") CoffeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coffee
     *
     * @mbg.generated Tue May 12 23:29:28 CST 2020
     */
    int updateByExample(@Param("record") Coffee record, @Param("example") CoffeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coffee
     *
     * @mbg.generated Tue May 12 23:29:28 CST 2020
     */
    int updateByPrimaryKeySelective(Coffee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coffee
     *
     * @mbg.generated Tue May 12 23:29:28 CST 2020
     */
    @Update({
        "update t_coffee",
        "set name = #{name,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=BIGINT,typeHandler=com.example.mybatisdemo.handler.MoneyTypeHandler},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Coffee record);
}