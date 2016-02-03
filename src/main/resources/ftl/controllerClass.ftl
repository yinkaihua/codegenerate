package ${controllerPackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coding17.easycms.web.base.BaseController;
import ${servicePackage}.${className}Service;
import ${voPackage}.${voName};

 /**
 * 描述：</b>${codeName}<br>
 * @author：系统生成
 * @version:1.0
 */
@RequestMapping("/")
@Controller
public class ${voName}Controller extends BaseController<${voName}> {
	
	@Autowired
	private ${className}Service ${lowerName}Service;
	
}
