package ${entityPackage};

<#list beanFieldDataTypes as item>
<#if item == 'Date'>
import java.util.Date;
</#if>
<#if item == 'BigDecimal'>
import java.math.BigDecimal;
</#if>
</#list>
import com.coding17.easycms.soa.base.entity.BasicEntity;

/**
 * 描述：</b>${codeName}<br>
 * @author：系统生成
 * @version:1.0
 */
public class ${className} extends BasicEntity {
	private static final long serialVersionUID = 1L;
	
	<#list columnDatas as item>
	<#if ignoreGenerateColumns?index_of(item.columnName) == -1>
	/**
	 *${item.columnComment}
	 */
	private ${item.dataType} ${item.domainPropertyName};
	</#if>
	</#list>
	
	<#list columnDatas as item>
	<#if ignoreGenerateColumns?index_of(item.columnName) == -1>
	/**
	 *获取${item.columnComment}
	 */
	public ${item.dataType} get${item.domainPropertyName?cap_first}(){
		return this.${item.domainPropertyName};
	}
	
	/**
	 *设置${item.columnComment}
	 */
	public void set${item.domainPropertyName?cap_first}(${item.dataType} ${item.domainPropertyName}){
		this.${item.domainPropertyName} = ${item.domainPropertyName};
	}
	
	</#if>
	</#list>
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
        return ${className}.class.getName();
    }
    
    /**
     * 重写toString()
     */
	@Override
    public String toString() {
    	StringBuffer tos = new StringBuffer("${className}[");
    	<#list columnDatas as item>
    	tos.append("${item.domainPropertyName}=").append(this.${item.domainPropertyName}).append(",");
    	</#list>
    	tos.append("]");
        return tos.toString();
    }
    
}

