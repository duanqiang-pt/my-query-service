package com.zhongsheng.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.zhongsheng.checker.OrderByConditionChecker;
import com.zhongsheng.component.GroupBy;
import com.zhongsheng.component.Limit;
import com.zhongsheng.component.SqlQueryCondition;
import com.zhongsheng.component.cache.GroupByCacheKey;
import com.zhongsheng.component.cache.OrderByCacheKey;
import com.zhongsheng.component.enums.OrderByTypeEnum;
import com.zhongsheng.component.enums.WhereTypeEnum;
import com.zhongsheng.component.orderby.OrderBy;
import com.zhongsheng.component.orderby.OrderByDetail;
import com.zhongsheng.component.where.AndItem;
import com.zhongsheng.component.where.OrItem;
import com.zhongsheng.component.where.Where;
import com.zhongsheng.exception.QueryParamIllgalException;
import com.zhongsheng.exception.SqlQueryException;
import com.zhongsheng.model.UserDo;
import com.zhongsheng.result.QueryHandleResult;
import com.zhongsheng.service.CommonQueryService;
import com.zhongsheng.utils.RandomUtils;

/**
 * 关于查询服务的测试类
 * @author zhongsheng
 * @date 2020年10月11日
 * @version 1.0
 */
public class UserQueryServiceImplTest {
	
	private CommonQueryService userQueryService = new CommonQueryServiceImpl();
	
	
	private List<UserDo> testBigDataList = Lists.newArrayList();
	
	
	private List<UserDo> testLittleDataList = Lists.newArrayList();
	
	private List<UserDo> testTenDataList = Lists.newArrayList();
	
	@Before
	public void setUp(){
	    //准备5条数据
		testLittleDataList = prepareTestData(5);
		testTenDataList = prepareTestData(10);
		testBigDataList = prepareTestData(6000);
	}

    /**
     * 准备数据方法
     */
    private List<UserDo> prepareTestData(int dataCount) {
    	List<UserDo> list = Lists.newArrayList();
        for(int i=1;i<=dataCount;i++){
			UserDo userDo = new UserDo();
			userDo.setId(i);
		    userDo.setAge(i%2==0?25:27);
			userDo.setCellphone("13988886"+i);
			userDo.setName("张三"+RandomUtils.randomInt(20, 30)+"号");
			list.add(userDo);
		}
        return list;
    }
    
    /**
     * 覆盖CacheKey
     */
    @Test
    public void testCache(){
    	GroupByCacheKey<UserDo> key1 = new GroupByCacheKey<>(new GroupBy(), testLittleDataList);
    	GroupByCacheKey<UserDo> key2 = new GroupByCacheKey<>(new GroupBy(), testLittleDataList);
    	GroupByCacheKey<UserDo> key3 = new GroupByCacheKey<>(null, null);
    	GroupByCacheKey<UserDo> key4 = new GroupByCacheKey<>(null, testLittleDataList);
    	
    	key1.equals(key2);
    	key3.equals(key4);
    	
    	key1.hashCode();
    	
    	
    	OrderByCacheKey<UserDo> key5 = new OrderByCacheKey<>(null, null);
    	OrderByCacheKey<UserDo> key6 = new OrderByCacheKey<>(null, null);
    	OrderByCacheKey<UserDo> key7 = new OrderByCacheKey<>(new OrderBy(), null);
    	OrderByCacheKey<UserDo> key8 = new OrderByCacheKey<>(new OrderBy(), testLittleDataList);
    	
    	key5.equals(key6);
    	
    	key5.hashCode();
    	
    	
    	key7.equals(key8);
    	
    	
    	OrderByConditionChecker.checkOrderByParams(new OrderBy());
    	
    	
    	
    }
    
    
    
    /**
     * 覆盖异常类
     */
    @Test
    public void testCreateException(){
    	Throwable thr = new Throwable();
    	
    	 new QueryParamIllgalException();
    	 
    	 new QueryParamIllgalException(thr);
    	 new QueryParamIllgalException("",thr);
    	 new QueryParamIllgalException("",thr,true,false);
    	 
    	 
    	 
    	 new SqlQueryException();
    	 
    	 new SqlQueryException(thr);
    	 new SqlQueryException("",thr);
    	 new SqlQueryException("",thr,true,false);
    	 
    }
    
    /**
     * 覆盖Where组件的代码
     */
    @Test
    public void testWhereComponent(){
    	Where where  = new Where();
    	
    	where.addOr();
    	
    	OrItem orCriteria = new OrItem();
    	where.addOr(orCriteria);
    	
    	OrItem createOrCriteria = where.createOrCriteria();
    	
    	where.clear();
    	
    	createOrCriteria.andBetween("", "", "");
    	
    	
    	createOrCriteria.isValid();
    	
    	createOrCriteria.setAndCriteriaList(Lists.newArrayList());
    	
    	
    	AndItem andItem = new AndItem(WhereTypeEnum.EXSITS, "", "");
    	
    	
    	andItem.setSecondValue("");
    	
    	andItem.setValue("");
    	andItem.getSecondValue();
    	
    	andItem.setWhereType(WhereTypeEnum.GREATER_THAN);
    	andItem.setFieldName("");
    	
    }
    
    /**
     * 测试limit入参异常
     */
    @Test
    public void testException(){
    	SqlQueryCondition sqlQueryCondition = new SqlQueryCondition();
    	
    	Limit limitCondition = new Limit();
    	limitCondition.setOffset(-5);
    	
    	sqlQueryCondition.setLimitObj(limitCondition);
    	
    	QueryHandleResult<UserDo> result = userQueryService.query(testTenDataList, sqlQueryCondition);
    	
    	
    	Assert.assertNull("返回结果不对！",result.getListResult());
    	
    }
    
    
	
	/**
	 * 对Limit条件的测试
	 */
	@Test
	public void testLimit(){
		SqlQueryCondition sqlQueryCondition = new SqlQueryCondition();
		
		Limit limitCondition = new Limit();
		limitCondition.setOffset(2);
		limitCondition.setSize(5);
		
		sqlQueryCondition.setLimitObj(limitCondition);
		
		QueryHandleResult<UserDo> result = userQueryService.query(testTenDataList, sqlQueryCondition);
        
        List<UserDo> listResult = result.getListResult();
        
        System.out.println(listResult);
        Assert.assertTrue("返回结果不对！",listResult.size()==5);
        System.out.println("返回结果的大小是："+listResult.size());
	}
	
	/**
	 * 对OrderBy条件的测试
	 */
	@Test
	public void testOrderBy(){
	    
	    SqlQueryCondition sqlQueryCondition = new SqlQueryCondition();
	    
	    OrderBy orderby = new OrderBy();
	    List<OrderByDetail> orderByDetailList = new ArrayList<>();
	    
	    OrderByDetail orderByDetail1 = new OrderByDetail();
	    orderByDetail1.setFieldName("age");
	    orderByDetail1.setOrderByType(OrderByTypeEnum.DESC);
	    
	    OrderByDetail orderByDetail2 = new OrderByDetail();
        orderByDetail2.setFieldName("name");
        orderByDetail2.setOrderByType(OrderByTypeEnum.ASC);
	    
	    
        orderByDetailList.add(orderByDetail1);
	    orderByDetailList.add(orderByDetail2);
	    orderby.setOrderByDetailList(orderByDetailList);
	    
	    sqlQueryCondition.setOrderByObj(orderby);
	    //这里先调用一次测试缓存是否生效
	    userQueryService.query(testBigDataList, sqlQueryCondition);
	    QueryHandleResult<UserDo> result = userQueryService.query(testTenDataList, sqlQueryCondition);
        
        List<UserDo> listResult = result.getListResult();
        
        System.out.println(listResult);
        Assert.assertTrue("返回结果不对！",listResult.size()==10);
        System.out.println("返回结果的大小是："+listResult.size());
	    
	}
	
	
	
	/**
	 * 对GroupBy条件的测试
	 */
	@Test
	public void testGroupBy(){
	    
	    SqlQueryCondition sqlQueryCondition = new SqlQueryCondition();
	   
	    GroupBy groupBy = new GroupBy();
	    groupBy.setGroupByField("age");
	    
	    sqlQueryCondition.setGroupByObj(groupBy);
	    
	    //这里先调用一次测试缓存是否生效
	    userQueryService.query(testBigDataList, sqlQueryCondition);
	    QueryHandleResult<UserDo> result = userQueryService.query(testTenDataList, sqlQueryCondition);
	    
	    Map<Object, List<UserDo>> ressultMap = result.getHasGroupByRessultMap();
        
	    System.out.println(ressultMap);
	    Assert.assertTrue("返回结果不对！",ressultMap.size()==2);
        System.out.println("返回结果的大小是："+ressultMap.size());
	    
	}
	

	/**
	 * 对Where条件的测试
	 */
	@Test
	public void testWhereCondition() {
		
		SqlQueryCondition sqlQueryCondition = new SqlQueryCondition();
		
		Where where  = new Where();
		List<OrItem> orCriteriaList = new ArrayList<>();
		//添加第一个“or”条件
		OrItem orItem1 = new OrItem();
		orItem1.andEqualTo("id", 5L);
		orItem1.andNotEqualTo("name", "张三2");
		orItem1.andIn("id", Lists.newArrayList(3L,5L,6L));
		orCriteriaList.add(orItem1);
		
		//添加第二个“or”条件
		OrItem orItem2 = new OrItem();
		orItem2.andIn("age", Lists.newArrayList(0,5,7));
		orCriteriaList.add(orItem2);
		
		
		where.setOrCriteriaList(orCriteriaList);
		sqlQueryCondition.setWhereObj(where);
		
		 //这里先调用一次测试缓存是否生效
		userQueryService.query(testBigDataList, sqlQueryCondition);
		QueryHandleResult<UserDo> result = userQueryService.query(testBigDataList, sqlQueryCondition);
		
		List<UserDo> listResult = result.getListResult();
		
		Assert.assertTrue("返回结果不对！",listResult.size()==1);
		
		System.out.println("Where返回结果的大小是："+listResult.size());
		
	}

}
