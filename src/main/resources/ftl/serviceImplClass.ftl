package ${serviceImpPackage};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ${daoPackage}.${className}DAO;
import ${servicePackage}.${className}Service;
import ${entityPackage}.${className};

 /**
 * 描述：</b>${codeName}<br>
 * @author：系统生成
 * @version:1.0
 */
@Repository
public class ${className}ServiceImpl implements ${className}Service {
	
	@Autowired
	public ${className}DAO ${lowerName}Dao;

	@SuppressWarnings("unused")
	private final static Logger log= LoggerFactory.getLogger(${className}ServiceImpl.class);
	
}
