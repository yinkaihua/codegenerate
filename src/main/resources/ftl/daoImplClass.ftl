package ${daoPackage}.impl;

import org.springframework.stereotype.Repository;

import com.coding17.easycms.soa.base.dao.impl.SuperDAO;
import ${daoPackage}.${className}DAO;
import ${entityPackage}.${className};

/**
 * 描述：</b>${codeName}<br>
 * @author：系统生成
 * @version:1.0
 */
@Repository
public class ${className}DAOImpl extends SuperDAO<${className}> implements ${className}DAO {

	@Override
	protected String getStatementPrefix() {
		return ${className}.class.getName();
	}

}