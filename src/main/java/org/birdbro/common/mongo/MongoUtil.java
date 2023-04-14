package org.birdbro.common.mongo;

import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Mongodb操作工具类
 * 临床试验
 *
 * @author birdbro
 * @date 13:15 2023-04-13
 **/

@Slf4j
@Component
public class MongoUtil {

    public static MongoUtil mongoUtil;

    @Resource
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {
        mongoUtil = this;
        mongoUtil.mongoTemplate = this.mongoTemplate;
    }


    /**
     * 保存对象List到指定集合中
     * 也可以在实体类上使用@Document(collection=“集合名称”)指定集合名称，未指定则默认实体类的类名为集合名称
     */
    public static <T> List<T> saveAll(String collName, List<T> entiys) {
        List<T> result = new ArrayList<>(entiys.size());
        for (T entiy : entiys) {
            result.add(saveData(collName, entiy));
        }
        return result;
    }

    /**
     * 保存单个对象到指定集合中
     *
     * @param collName 集合名称
     * @param entiy    实体名称
     */
    public static <T> T saveOne(String collName, T entiy) {
        return saveData(collName, entiy);
    }

    /**
     * 根据id倒序查询 集合中的数据
     *
     * @param entiy     数据实体
     * @param collName  集合名称
     * @param direction 倒序/正序 Direction.DESC/ASC
     */
    public static <T> List<T> findSortById(Class<T> entiy, String collName, Sort.Direction direction) {
        Query query = new Query().with(Sort.by(direction, "id")).skip(0).limit(10);
        return mongoUtil.mongoTemplate.find(query, entiy, collName);
    }

    /**
     * 查询返回指定字段
     *
     * @param fields   需要返回的指定字段  eg: fields.add("runTime");
     * @param clazz    数据实体类class
     * @param collName 集合名称
     * @param map      Map<查询条件key,查询条件value>  eg: map.put("describe", "查询用户信息");
     * @param returnId 返回字段的时候id默认为返回，不返回id则field设置
     */
    public static <T> List<T> findDesignField(List<String> fields, Map<String, Object> map, Class<T> clazz, String collName, boolean returnId) {
        Criteria criteria = new Criteria();
        for (String key : map.keySet()) {
            criteria = Criteria.where(key).is(map.get(key));
        }
        Query query = new Query(criteria);
        for (String field : fields) {
            query.fields().include(field);
        }
        if (!returnId) {
            query.fields().exclude("id");
        }
        return mongoUtil.mongoTemplate.find(query, clazz, collName);
    }

    /**
     * 查询指定集合中的所有数据
     *
     * @param entiy    数据实体类
     * @param collName 集合名称
     */
    public static <T> List<T> findAll(Class<T> entiy, String collName) {
        return mongoUtil.mongoTemplate.findAll(entiy, collName);
    }

    /**
     * 模糊查询 根据 key 可以到 collName 中进行模糊查询 并排序
     *
     * @param param     匹配的参数名称
     * @param key       模糊搜索关键字
     * @param collName  集合名称
     * @param sortField 排序字段
     * @param direction Direction.desc /asc 倒序/正序
     **/
    public static <T> List<T> findLikeByParam(String param, String key, String collName, Class<T> entiy, String sortField, Sort.Direction direction) {
        Pattern pattern = Pattern.compile("^.*" + key + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = new Query(Criteria.where(param).regex(pattern)).with(Sort.by(direction, sortField));
        return mongoUtil.mongoTemplate.find(query, entiy, collName);
    }

    /**
     * 向指定集合设置索引
     *
     * @param collName  集合名称
     * @param indexName 索引名称
     * @param map       map.put("添加索引的字段",Direction.ASC/DESC)
     */
    public static String createIndex(String collName, String indexName, Map<String, Sort.Direction> map) {
        for (String key : map.keySet()) {
            Assert.notNull(key, "添加索引的字段不能为null");
        }
        Index index = new Index().named(indexName);
        for (String key : map.keySet()) {
            index.on(key, map.get(key));
        }
        return mongoUtil.mongoTemplate.indexOps(collName).ensureIndex(index);
    }

    /**
     * 获取指定集合中的索引信息
     *
     * @param collName 集合名称
     */
    public static List<IndexInfo> getIndexInfo(String collName) {
        return mongoUtil.mongoTemplate.indexOps(collName).getIndexInfo();
    }


    /**
     * 根据索引名称删除索引
     *
     * @param indexName 索引名称
     * @param collName  集合名称
     */
    public static void removeIndexByName(String collName, String indexName) {
        mongoUtil.mongoTemplate.indexOps(collName).dropIndex(indexName);
    }


    /**
     * 删除指定集合中得所有索引
     *
     * @param collName 集合名称
     */
    public static void removeIndexByName(String collName) {
        mongoUtil.mongoTemplate.indexOps(collName).dropAllIndexes();
    }

    /**
     * 根据指定key 和value到指定collName集合中删除数据
     */
    public static long removeAllByParam(String key, String value, String collName) {
        Criteria criteria = Criteria.where(key).is(value);
        Query query = Query.query(criteria);
        return mongoUtil.mongoTemplate.remove(query, collName).getDeletedCount();
    }

    /**
     * 根据指定条件查询 并排序
     *
     * @param obj      数据对象
     * @param map      Map<"查询条件key"，查询条件值> map
     * @param collName 集合名称
     */
    public static <T> List<T> findSortByParam(Class<T> obj, String collName, Map<String, Object> map, String sortField, Sort.Direction direction) {
        for (String key : map.keySet()) {
            Assert.notNull(key, "查询条件key不能为null");
        }
        Criteria criteria = null;
        criteria = getCriteria(criteria, map);
        if (criteria == null) {
            return new ArrayList<>();
        }
        Query query = Query.query(criteria).with(Sort.by(direction, sortField));
        return mongoUtil.mongoTemplate.find(query, obj, collName);
    }

    /**
     * 范围查询
     * 查询大于等于begin  小于等于end范围内条件匹配得数据并排序
     *
     * @param obj           数据对象
     * @param collName      集合名称
     * @param map           Map<"查询条件key"，查询条件值> map
     * @param sortField     排序字段
     * @param direction     排序方式  Direction.asc   / Direction.desc
     * @param rangeCriteria 示例： lt小于  lte 小于等于  gt大于  gte大于等于 eq等于 ne不等于
     *                      <p>
     *                      Criteria rangeCriteria=Criteria.where("createDate").gte(begin).lte(end));
     *                      <p>
     *                      createDate:数据库中的时间字段，gegin:起始时间  end:结束时间
     */
    public static <T> List<T> findRangeByParam(Class<T> obj, String collName, Map<String, Object> map, String sortField, Sort.Direction direction, Criteria rangeCriteria) {
        for (String key : map.keySet()) {
            Assert.notNull(key, "查询条件key不能为null");
        }
        Criteria criteria = null;
        criteria = getCriteria(criteria, map);
        if (criteria == null) {
            return new ArrayList<>();
        }
        Query query = new Query().addCriteria(rangeCriteria).addCriteria(criteria).with(Sort.by(direction, sortField));
        return mongoUtil.mongoTemplate.find(query, obj, collName);
    }

    /**
     * 根据指定key value到指定集合中查询匹配得数量
     */
    public static long count(String collName, String key, String value) {
        Query query = Query.query(Criteria.where(key).is(value));
        return mongoUtil.mongoTemplate.count(query, collName);
    }

    /**
     * 在指定范围内查询匹配条件的数量
     *
     * @param clazz         数据实体类
     * @param collName      集合名称
     * @param map           查询条件map
     * @param rangeCriteria 范围条件  Criteria rangeCriteria= Criteria.where("数据库字段").gt/gte（起始范围）.lt/lte（结束范围）
     */
    public static Long countRangeCondition(Class<?> clazz, String collName, Criteria rangeCriteria, Map<String, Object> map) {
        Criteria criteria = null;
        for (String key : map.keySet()) {
            Assert.notNull(key, "查询条件key不能为null");
        }
        for (String key : map.keySet()) {
            criteria = Criteria.where(key).is(map.get(key));
        }
        Query query = new Query();
        if (criteria != null) {
            query.addCriteria(criteria);
        }
        query.addCriteria(rangeCriteria);
        return mongoUtil.mongoTemplate.count(query, clazz, collName);
    }

    /**
     * 指定集合 根据条件查询出符合的第一条数据
     *
     * @param clazz    数据对象
     * @param map      条件map  Map<条件key,条件value> map
     * @param collName 集合名
     */
    public static <T> T findSortFirst(Class<T> clazz, Map<String, Object> map, String collName, String field, Sort.Direction direction) {
        for (String key : map.keySet()) {
            Assert.notNull(key, "查询条件key不能为null");
        }
        Criteria criteria = null;
        criteria = getCriteria(criteria, map);
        Query query = Query.query(criteria).with(Sort.by(direction, field));
        return mongoUtil.mongoTemplate.findOne(query, clazz, collName);
    }

    /**
     * 指定集合 修改数据，且修改所找到的所有数据
     *
     * @param accordingKey   修改条件 key
     * @param accordingValue 修改条件 value
     * @param map            Map<修改内容 key数组,修改内容 value数组>
     * @param collName       集合名
     * @param type           修改操作类型  1:修改第一条数据  0：修改所有匹配得数据
     */
    public static UpdateResult updateMulti(String accordingKey, Object accordingValue, Map<String, Object> map, String collName, Integer type) {
        for (String key : map.keySet()) {
            Assert.notNull(key, "查询条件key不能为null");
        }
        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();

        for (String key : map.keySet()) {
            update.set(key, map.get(key));
        }
        if (type == 1) {
            return mongoUtil.mongoTemplate.updateFirst(query, update, collName);
        } else {
            return mongoUtil.mongoTemplate.updateMulti(query, update, collName);
        }
    }


    /**
     * 对某字段做sum求和
     *
     * @param clazz    数据实体类
     * @param map      Map<查询条件key,查询条件value> map
     * @param collName 集合名称
     * @param sumField 求和字段
     * @return Criteria rangeCriteria = Criteria.where(字段).gt(起始范围).lt(结束范围)
     */
    public static <T> List<T> findSum(Class<T> clazz, Map<String, Object> map, String collName, String sumField, Criteria criteria) {
        for (String key : map.keySet()) {
            Assert.notNull(key, "查询条件key不能为null");
        }
        MatchOperation match = null;
        for (String key : map.keySet()) {
            criteria = Criteria.where(key).is(map.get(key));
        }
        if (criteria != null) {
            match = Aggregation.match(criteria);
        }
        GroupOperation count = Aggregation.group().sum(sumField).as(sumField);
        return mongoUtil.mongoTemplate.aggregate(Aggregation.newAggregation(match, count), collName, clazz).getMappedResults();
    }

    /**
     * 分页查询
     *
     * @param clazz     数据实体类
     * @param collName  集合名称
     * @param map       Map<"查询条件key"，查询条件值> map 若 keys/values 为null,则查询集合中所有数据
     * @param pageNo    当前页
     * @param pageSize  当前页数据条数
     * @param direction Direction.Desc/ASC 排序方式
     * @param sortField 排序字段
     */
    public static <T> PageEntity<T> findSortPageCondition(Class<T> clazz, String collName, Map<String, Object> map, int pageNo, int pageSize, Sort.Direction direction, String sortField) {

        Criteria criteria = getCriteria(new Criteria(), map);

        long count;

        if (criteria == null) {
            count = mongoUtil.mongoTemplate.count(new Query(), clazz, collName);
        } else {
            count = mongoUtil.mongoTemplate.count(new Query(criteria), clazz, collName);
        }
        int pages = (int) Math.ceil((double) count / (double) pageSize);
        if (pageNo <= 0 || pageNo > pages) {
            pageNo = 1;
        }
        int skip = pageSize * (pageNo - 1);
        Query query = new Query().skip(skip).limit(pageSize);
        query.with(Sort.by(direction, sortField));
        if (criteria != null) {
            query.addCriteria(criteria);
        }
        List<T> list = mongoUtil.mongoTemplate.find(query, clazz, collName);
        PageEntity<T> page = new PageEntity<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(count);
        page.setPages(pages);
        page.setList(list);
        return page;
    }

    private static <T> T saveData(String collName, T entiy) {
        return mongoUtil.mongoTemplate.save(entiy, collName);
    }

    private static Criteria getCriteria(Criteria criteria, Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        int i = 0;
        for (String key : map.keySet()) {
            if (i == 0) {
                criteria = Criteria.where(key).is(map.get(key));
                i++;
            } else {
                criteria.and(key).is(map.get(key));
            }
        }
        return criteria;
    }


}
