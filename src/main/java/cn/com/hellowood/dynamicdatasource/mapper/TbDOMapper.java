package cn.com.hellowood.dynamicdatasource.mapper;

import cn.com.hellowood.dynamicdatasource.configuration.TargetDataSource;
import cn.com.hellowood.dynamicdatasource.modal.TbDO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TbDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbDO record);

    int insertSelective(TbDO record);

    TbDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbDO record);

    int updateByPrimaryKey(TbDO record);

    List<TbDO> listAll();
}