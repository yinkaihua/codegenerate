package ${entityPackage};

<#list beanFieldDataTypes as item>
<#if item == 'Date'>
import java.util.Date;
</#if>
<#if item == 'BigDecimal'>
import java.math.BigDecimal;
</#if>
</#list>
import com.sankai.user.common.entity.BasicEntity;
import com.sankai.user.service.${className}Service;
import com.sankai.user.common.util.SpringUtils;

/**
 * 描述：</b>${codeName}<br>
 * @author：系统生成
 * @since：${nowDate}
 * @version:1.0
 */
public class ${className}Entity extends BasicEntity {
	private static final long serialVersionUID = 1L;
	${feilds}
	
	/**
	 * 获取主键字段
	 */
	@Override
    public String primaryKey() {
    	if(<#list columnKeyDatas as item><#if item_index==0>${item.domainPropertyName}==null<#else> || ${item.domainPropertyName}==null</#if></#list>){
    		throw new IllegalArgumentException("${codeName}主键为空!");
    	}
    	return <#list columnKeyDatas as item><#if item_index==0>${item.domainPropertyName}.toString()<#else> + ":" + ${item.domainPropertyName}.toString()</#if></#list>;
    }
    
	/**
	 * 获取实体类名称
	 */
	@Override
    public String className() {
        return ${className}Entity.class.getName();
    }
	
	/**
	 * 获取service数据操作类型
	 */
	@Override
    public ${className}Service service() {
        return (${className}Service)SpringUtils.getBean("${lowerName}ServiceImpl");
    }
	
}

