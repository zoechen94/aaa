package cn.com.hellowood.dynamicdatasource.service;

import cn.com.hellowood.dynamicdatasource.configuration.DynamicDataSourceContextHolder;
import cn.com.hellowood.dynamicdatasource.configuration.TargetDataSource;
import cn.com.hellowood.dynamicdatasource.mapper.ProductDao;
import cn.com.hellowood.dynamicdatasource.mapper.RoleDOMapper;
import cn.com.hellowood.dynamicdatasource.mapper.TbDOMapper;
import cn.com.hellowood.dynamicdatasource.modal.Product;
import cn.com.hellowood.dynamicdatasource.modal.RoleDO;
import cn.com.hellowood.dynamicdatasource.modal.TbDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Product service for handler logic of product operation
 *
 * @author HelloWood
 * @date 2017-07-11 11:58
 * @Email hellowoodes@gmail.com
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private RoleDOMapper roleDOMapper;

    @Autowired
    private TbDOMapper tbDOMapper;

    /**
     * Get product by id
     * If not found product will throw Exception
     *
     * @param productId
     * @return
     * @throws Exception
     */
    public Product select(long productId) throws Exception {
        Product product = productDao.select(productId);
        if (product == null) {
            throw new Exception("Product:" + productId + " not found");
        }
        return product;
    }

    /**
     * Update product by id
     * If update failed will throw Exception
     *
     * @param productId
     * @param newProduct
     * @return
     * @throws Exception
     */
    public Product update(long productId, Product newProduct) throws Exception {

        if (productDao.update(newProduct) <= 0) {
            throw new Exception("Update product:" + productId + "failed");
        }
        return newProduct;
    }

    /**
     * Add product to DB
     *
     * @param newProduct
     * @return
     * @throws Exception
     */
    public boolean add(Product newProduct) throws Exception {
        Integer num = productDao.insert(newProduct);
        if (num <= 0) {
            throw new Exception("Add product failed");
        }
        return true;
    }

    /**
     * Delete product from DB
     *
     * @param productId
     * @return
     * @throws Exception
     */
    public boolean delete(long productId) throws Exception {
        Integer num = productDao.delete(productId);
        if (num <= 0) {
            throw new Exception("Delete product:" + productId + "failed");
        }
        return true;
    }

    /**
     * Query all product
     *
     * @return
     */
    public List<Product> selectAll() {
        return productDao.selectAll();
    }

    public List<RoleDO>  listAllRoles(){
        return roleDOMapper.listAll();
    }

    public List<TbDO> selectTbAll(){
        return tbDOMapper.listAll();
    }

    public Map<String,Object> map(){
        Map<String,Object> map=new HashMap<>();
        DynamicDataSourceContextHolder.setDataSourceKey("third");
        map.put("third",tbDOMapper.listAll());
        DynamicDataSourceContextHolder.setDataSourceKey("master");
        map.put("master",productDao.selectAll());
        DynamicDataSourceContextHolder.setDataSourceKey("slave");
        map.put("slave",roleDOMapper.listAll());
        return map;
    }

    @TargetDataSource("third")
    public Map<String,Object> map2(){
        Map<String,Object> map=new HashMap<>();
        map.put("third",tbDOMapper.listAll());
        map.put("role",roleDOMapper.listAll());
        map.put("master",productDao.selectAll());
//        map=getSlave(getSlave(map));
        return map;
    }

    @TargetDataSource("slave")
    public Map<String, Object> getSlave(Map<String, Object> map) {
        map.put("slave",roleDOMapper.listAll());
        return map;
    }

    @TargetDataSource("master")
    public Map<String, Object> getMaster(Map<String, Object> map) {
        map.put("master",productDao.selectAll());
        return map;
    }
}
